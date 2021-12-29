package validators.builders;

import validators.Validator;

import java.lang.reflect.Field;

public interface ValidatorBuilder<T> {
    Validator<T> build();
    void processAnnotatedField(Field field);
}
