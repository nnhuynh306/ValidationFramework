package validators.annotation;

import util.parser.FieldValueParser;
import util.parser.FieldValueParserImpl;
import validators.BaseValidator;
import validators.Validator;
import validators.builders.ChainValidatorBuilder;
import validators.builders.ChainValidatorBuilderFactory;
import validators.result.ValidationResults;

import java.lang.reflect.Field;

/**
 * Basically a validator for annotated field of class T
 *
 * @param <T>Class T
 * @param <S>T's   annotated field type
 */
public class AnnotatedFieldValidator<T, S> extends BaseValidator<T> {

    private final FieldValueParser<T, S> fieldValueParser;
    private final Field field;
    private Validator<S> validator;
    private final ChainValidatorBuilder<S> chainValidatorBuilder;
    private final Class<S> sClass;

    public AnnotatedFieldValidator(Field field, Class<S> sClass) {
        this.fieldValueParser = new FieldValueParserImpl<>();
        this.field = field;
        this.sClass = sClass;
        this.chainValidatorBuilder = ChainValidatorBuilderFactory.getChainValidatorBuilderFor(sClass);
        this.setName(field.getName());
    }

    @Override
    public boolean validate(T t, ValidationResults returnResults) {
        boolean result;
        try {
            S s = fieldValueParser.parseValue(field, t, sClass);
            result = getValidator().validate(s, returnResults);
            if (hasNext()) {
                result = result && nextValidator.validate(t, returnResults);
            }
        } catch (NullPointerException e) {
            if (hasNext()) {
                result = nextValidator.validate(t, returnResults);
            } else {
                result = true;
            }
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            result = true;
        }

        return result;
    }

    private Validator<S> getValidator() {
        if (validator == null) {
            chainValidatorBuilder.processAnnotatedField(field);
            validator = chainValidatorBuilder.build();
        }
        return validator;
    }
}
