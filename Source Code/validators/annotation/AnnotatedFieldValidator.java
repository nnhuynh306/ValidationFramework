package validators.annotation;

import validators.annotation.parser.FieldValueParser;
import validators.BaseValidator;
import validators.Validator;
import validators.annotation.parser.FieldValueParserImpl;
import validators.builders.ValidatorBuilder;
import validators.builders.ValidatorBuilderFactory;
import validators.result.ValidationResults;

import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 * Basically a validator for annotated field of class T
 * @param <T>Class T
 * @param <S>T's annotated field type
 */
public class AnnotatedFieldValidator<T, S> extends BaseValidator<T> {

    private FieldValueParser<T, S>  fieldValueParser;
    private Field field;
    private Validator<S> validator;
    private ValidatorBuilder<S> validatorBuilder;
    private Class<S> sClass;

    public AnnotatedFieldValidator(Field field, Class<S> sClass) {
        this.fieldValueParser = new FieldValueParserImpl<>();
        this.field = field;
        this.sClass = sClass;
        this.validatorBuilder = ValidatorBuilderFactory.getValidatorBuilderBy(sClass);
    }

    @Override
    public boolean validate(T t, ValidationResults returnResults) {
        try {
            S s = fieldValueParser.parseValue(field, t, sClass);
            if (hasNext()) {
                return getValidator().validate(s, returnResults) && nextValidator.validate(t, returnResults);
            } else {
                return getValidator().validate(s, returnResults);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Validator<S> getValidator() {
        if (validator == null) {
            validatorBuilder.processAnnotatedField(field);
            validator = validatorBuilder.build();
        }
        return validator;
    }


}
