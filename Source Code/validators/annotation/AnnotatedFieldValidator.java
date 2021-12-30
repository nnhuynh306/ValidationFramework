package validators.annotation;

import validators.BaseValidator;
import validators.Validator;

import validators.annotation.parser.FieldValueParser;

import validators.annotation.parser.FieldValueParserImpl;
import validators.builders.ValidatorBuilder;
import validators.builders.ValidatorBuilderFactory;

import validators.result.ValidationResult;
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
    private ValidatorBuilder<S> validatorBuilder;
    private Class<S> sClass;

    private String name;

    public AnnotatedFieldValidator(Field field, Class<S> sClass) {
        this.fieldValueParser = new FieldValueParserImpl<>();
        this.field = field;
        this.sClass = sClass;
        this.validatorBuilder = ValidatorBuilderFactory.getValidatorBuilderBy(sClass);
        this.name = field.getName();
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

        if (returnResults != null) {
            returnResults.add(createResult(result, message));
        }

        return result;
    }

    private Validator<S> getValidator() {
        if (validator == null) {
            validatorBuilder.processAnnotatedField(field);
            validator = validatorBuilder.build();
        }
        return validator;
    }

    private ValidationResult createResult(boolean result, String message) {
        return new ValidationResult(result,
                name,
                result?"": message);
    }
}
