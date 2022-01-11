package validators.builders;

import annotations.*;
import util.ClassUtils;
import util.comparator.Comparator;
import validators.Validator;
import validators.builtin.EqualValidator;
import validators.builtin.MaxValidator;
import validators.builtin.MinValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class NumericChainValidatorBuilder<T> extends BaseChainValidatorBuilder<T> {
    private final Class<T> TClass;
    private final Comparator<T, T> comparator;

    protected NumericChainValidatorBuilder(Comparator<T, T> comparator, Class<T> TClass) {
        this.comparator = comparator;
        this.TClass = TClass;
    }

    public NumericChainValidatorBuilder<T> min(T value, boolean included) {
        addValidatorToChain(new MinValidator<T, T>(comparator, value, included));
        return this;
    }

    public NumericChainValidatorBuilder<T> min(T value) {
        return min(value, true);
    }

    public NumericChainValidatorBuilder<T> max(T value, boolean included) {
        addValidatorToChain(new MaxValidator<>(comparator, value, included));
        return this;
    }

    public NumericChainValidatorBuilder<T> max(T value) {
        return max(value, true);
    }

    public NumericChainValidatorBuilder<T> notNull() {
        addNotNullValidator();
        return this;
    }

    public NumericChainValidatorBuilder<T> equal(T value) {
        addValidatorToChain(new EqualValidator<T, T>(comparator, value));
        return this;
    }

    public NumericChainValidatorBuilder<T> validatedBy(Validator<T> validator) {
        addCustomValidator(validator);
        return this;
    }

    public Class<T> getTClass() {
        return TClass;
    }

    public <V extends Validator<T>> NumericChainValidatorBuilder<T> validatedBy(Class<V> validatorClass) {
        validatedBy(validatorClass, null);
        return this;
    }

    public <V extends Validator<T>> NumericChainValidatorBuilder<T> validatedBy(Class<V> validatorClass, Object[] arguments) {
        addCustomValidator(validatorClass, arguments);
        return this;
    }

    public NumericChainValidatorBuilder<T> name(String name) {
        addNameForLastValidator(name);
        return this;
    }

    public NumericChainValidatorBuilder<T> withMessage(String message) {
        setFailedMessageForLastValidator(message);
        return this;
    }

    @Override
    public void processAnnotatedField(Field field) {
        for (Annotation annotation : field.getAnnotations()) {
            processAnnotation(annotation, field.getName(), annotation.annotationType());
        }
    }

    private void processAnnotation(Annotation annotation, String name, Class<?> annotationClass) {
        try {
            if (annotationClass == NotNull.class) {
                notNull();
            } else if (annotationClass == Min.class) {
                Min minAnnotation = (Min) annotation;
                min((T) ClassUtils.parse(minAnnotation.value(), annotationClass), minAnnotation.included()).withMessage(minAnnotation.message());
            } else if (annotationClass == Max.class) {
                Max maxAnnotation = (Max) annotation;
                max((T) ClassUtils.parse(maxAnnotation.value(), annotationClass), maxAnnotation.included()).withMessage(maxAnnotation.message());
            } else if (annotationClass == Equal.class) {
                Equal equalAnnotation = (Equal) annotation;
                equal((T) ClassUtils.parse(equalAnnotation.value(), annotationClass)).withMessage(equalAnnotation.message());
            } else {
                //Custom annotation check
                ValidatedBy validatedBy = annotationClass.getAnnotation(ValidatedBy.class);
                Class<? extends Validator<T>> validatorClass = (Class<? extends Validator<T>>) validatedBy.validatorClass();
                validatedBy(validatorClass);
            }
            name(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
