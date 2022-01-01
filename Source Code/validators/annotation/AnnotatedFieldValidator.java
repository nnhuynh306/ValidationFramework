package validators.annotation;

import validators.BaseValidator;
import validators.Validator;

import util.parser.FieldValueParser;

import util.parser.FieldValueParserImpl;
import validators.builders.ChainValidatorBuilder;
import validators.builders.ChainValidatorBuilderFactory;

import validators.result.ValidationResults;

import java.lang.reflect.Field;

/**
 * Basically a validator for annotated field of class T
 * @param <T>Class T
 * @param <S>T's annotated field type
 */
public class AnnotatedFieldValidator<T, S> extends BaseValidator<T> {

    private FieldValueParser<T, S>  fieldValueParser;
    private Field field;
    private Validator<S> validator;
    private ChainValidatorBuilder<S> chainValidatorBuilder;
    private Class<S> sClass;

    private String name;

    public AnnotatedFieldValidator(Field field, Class<S> sClass) {
        this.fieldValueParser = new FieldValueParserImpl<>();
        this.field = field;
        this.sClass = sClass;
        this.chainValidatorBuilder = ChainValidatorBuilderFactory.getChainValidatorBuilderBy(sClass);
        this.setName(field.getName());
    }

    @Override
    public boolean validate(T t, ValidationResults returnResults) {
        boolean result;
        String message = "";
        try {
            S s = fieldValueParser.parseValue(field, t, sClass);
            if (hasNext()) {
                result = getValidator().validate(s, returnResults) && nextValidator.validate(t, returnResults);
            } else {
                result = getValidator().validate(s, returnResults);
            }
            message = name + " is invalid\n";
        } catch (NullPointerException e) {
            e.printStackTrace();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            message = "Catch Exception " + e.toString();
        }

        setFailedMessage(message);
        addResult(result, returnResults);

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
