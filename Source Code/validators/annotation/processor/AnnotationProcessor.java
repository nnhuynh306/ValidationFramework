package validators.annotation.processor;

import validators.builders.NumericValidatorBuilder;
import validators.builders.StringValidatorBuilder;

import java.lang.reflect.Field;

public interface AnnotationProcessor {
    void process(Field field, StringValidatorBuilder stringValidatorBuilder);
    <T> void process(Field field, NumericValidatorBuilder<T> numericValidatorBuilder, Class<T> TClass);
}
