package validators.annotation;

import validators.BaseValidator;
import validators.Validator;
import validators.annotation.parser.FieldValueParser;
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
    private Class<S> sClass;

    public AnnotatedFieldValidator(Field field, Validator<S> validator, FieldValueParser<T, S> fieldValueParser, Class<S> sClass) {
        this.field = field;
        this.validator = validator;
        this.fieldValueParser = fieldValueParser;
        this.sClass = sClass;
    }

    @Override
    public boolean validate(T t, ValidationResults returnResults) {
        try {
            S s = fieldValueParser.parseValue(field, t, sClass);
            return validator.validate(s, returnResults);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
