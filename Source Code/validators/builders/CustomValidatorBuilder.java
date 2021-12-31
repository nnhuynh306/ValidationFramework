package validators.builders;

import annotations.Nested;
import annotations.NotNull;
import annotations.ValidatedBy;
import validators.Validator;
import validators.annotation.AnnotationValidator;
import validators.builtin.NotNullValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CustomValidatorBuilder<T> extends BaseValidatorBuilder<T> {

    protected CustomValidatorBuilder() {
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

    public void nested() {
        addValidatorToChain(new AnnotationValidator<>());
    }

    public CustomValidatorBuilder<T> name(String name) {
        addNameForLastValidator(name);
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
            } else if (annotationClass == Nested.class) {
                nested();
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
