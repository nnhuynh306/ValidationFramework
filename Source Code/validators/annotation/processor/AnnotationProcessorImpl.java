package validators.annotation.processor;

import annotations.Min;
import validators.builders.NumericValidatorBuilder;
import validators.builders.StringValidatorBuilder;

import java.lang.reflect.Field;

public class AnnotationProcessorImpl implements AnnotationProcessor {
    @Override
    public void process(Field field, StringValidatorBuilder stringValidatorBuilder) {
        try {
            stringValidatorBuilder.minLength(Integer.parseInt(field.getAnnotation(Min.class).value()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> void process(Field field, NumericValidatorBuilder<T> numericValidatorBuilder, Class<T> TClass) {
    }
}
