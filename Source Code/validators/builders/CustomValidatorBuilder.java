package validators.builders;

import annotations.Min;
import annotations.NotNull;
import annotations.ValidatedBy;
import util.ClassUtils;
import validators.Validator;
import validators.builtin.NotNullValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CustomValidatorBuilder<T> extends BaseValidatorBuilder<T> {

    public CustomValidatorBuilder() {
    }

    public CustomValidatorBuilder<T> notNull() {
        addValidatorToChain(new NotNullValidator<>(false));
        return this;
    }

    public <V extends Validator<T>> CustomValidatorBuilder<T> validatedBy(Class<V> validatorClass) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        return validatedBy(validatorClass, null);
    }

    public <V extends Validator<T>> CustomValidatorBuilder<T> validatedBy(Class<V> validatorClass, Object[] arguments) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        addCustomValidator(validatorClass, arguments);
        return this;
    }

    @Override
    public void processAnnotatedField(Field field) {
        for (Annotation annotation: field.getAnnotations()) {
            processAnnotation(annotation);
        }
    }

    private void processAnnotation(Annotation annotation) {
        try {
            Class<?> annotationClass = annotation.annotationType();
            if (annotationClass == NotNull.class) {
                notNull();
            } else {
                ValidatedBy validatedBy = annotationClass.getAnnotation(ValidatedBy.class);
                Class<? extends Validator<T>> validatorClass = (Class<? extends Validator<T>>) validatedBy.validatorClass();
                validatedBy(validatorClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
