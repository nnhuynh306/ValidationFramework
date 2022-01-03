package validators.builders;

import annotations.Min;
import annotations.ValidatedBy;
import util.ClassUtils;
import util.comparator.Comparator;
import validators.Validator;
import validators.builtin.EqualValidator;
import validators.builtin.MaxValidator;
import validators.builtin.MinValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class NumericChainValidatorBuilder<T> extends BaseChainValidatorBuilder<T> {
    private Comparator<T, T> comparator;

    private final Class<T> TClass;

    protected NumericChainValidatorBuilder(Comparator<T, T> comparator, Class<T> TClass) {
        this.comparator = comparator;
        this.TClass = TClass;
    }

    public NumericChainValidatorBuilder<T> min(T value, boolean included) {
        addValidatorToChain(new MinValidator<T, T>( comparator, value, included));
        return this;
    }

    public NumericChainValidatorBuilder<T> max(T value, boolean included) {
        addValidatorToChain(new MaxValidator<>( comparator, value, included));
        return this;
    }

    public NumericChainValidatorBuilder<T> notNull(boolean exitWhenFailed) {
        addNotNullValidator();
        return this;
    }

    public NumericChainValidatorBuilder<T> equal(T value){
        addValidatorToChain(new EqualValidator<T, T>( comparator, value));
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
        for (Annotation annotation: field.getAnnotations()) {
            processAnnotation(annotation, field.getType());
        }
    }

    private void processAnnotation(Annotation annotation, Class<?> tClass) {
        try {
            Class<?> annotationClass = annotation.annotationType();

            if (annotationClass == Min.class) {
                Min minAnnotation = (Min) annotation;
                String message = minAnnotation.message();
                if (message.isEmpty()) {
                    min((T) ClassUtils.parse(minAnnotation.value(), Integer.class), true);
                } else {

                }
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
