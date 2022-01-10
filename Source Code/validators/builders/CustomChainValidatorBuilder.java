package validators.builders;

import annotations.Email;
import annotations.Nested;
import annotations.NotNull;
import annotations.ValidatedBy;
import validators.Validator;
import validators.annotation.AnnotationValidator;
import validators.builtin.NotNullValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CustomChainValidatorBuilder<T> extends BaseChainValidatorBuilder<T> {

    protected CustomChainValidatorBuilder() {
    }

    public CustomChainValidatorBuilder<T> notNull() {
        addValidatorToChain(new NotNullValidator<>());
        return this;
    }

    public <V extends Validator<T>> CustomChainValidatorBuilder<T> validatedBy(Class<V> validatorClass) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        return validatedBy(validatorClass, null);
    }

    public <V extends Validator<T>> CustomChainValidatorBuilder<T> validatedBy(Class<V> validatorClass, Object[] arguments) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        addCustomValidator(validatorClass, arguments);
        return this;
    }

    public void nested() {
        addValidatorToChain(new AnnotationValidator<>());
    }

    public void email() {
        addValidatorToChain(new AnnotationValidator<>());
    }

    public CustomChainValidatorBuilder<T> name(String name) {
        addNameForLastValidator(name);
        return this;
    }

    public CustomChainValidatorBuilder<T> validatedBy(Validator<T> validator) {
        addCustomValidator(validator);
        return this;
    }

    @Override
    public void processAnnotatedField(Field field) {
        for (Annotation annotation : field.getAnnotations()) {
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
            } else if (annotationClass == Email.class) {
                email();
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
