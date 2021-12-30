package validators.builders;

import annotations.Min;
import annotations.ValidatedBy;
import util.ClassUtils;
import util.comparator.Comparator;
import validators.Validator;
import validators.builtin.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class NumericValidatorBuilder<T> extends BaseValidatorBuilder<T> {
    private Comparator<T, T> comparator;

    private final Class<T> TClass;

    protected NumericValidatorBuilder(Comparator<T, T> comparator, Class<T> TClass) {
        this.comparator = comparator;
        this.TClass = TClass;
    }

    public NumericValidatorBuilder<T> min(T value, boolean included, boolean exitWhenFailed) {
        addValidatorToChain(new MinValidator<T, T>(exitWhenFailed, comparator, value, included));
        return this;
    }

    public NumericValidatorBuilder<T> max(T value, boolean included, boolean exitWhenFailed) {
        addValidatorToChain(new MaxValidator<>(exitWhenFailed, comparator, value, included));
        return this;
    }

    public NumericValidatorBuilder<T> notNull(boolean exitWhenFailed) {
        addNotNullValidator(exitWhenFailed);
        return this;
    }

    public NumericValidatorBuilder<T> notEmpty() {
        addValidatorToChain(new NotEmptyValidator<>(true));
        return this;
    }

    public NumericValidatorBuilder<T> equal(T value){
        addValidatorToChain(new EqualValidator<T, T>(true, comparator, value));
        return this;
    }

    public Class<T> getTClass() {
        return TClass;
    }

    public <V extends Validator<T>> NumericValidatorBuilder<T> validatedBy(Class<V> validatorClass) {
        validatedBy(validatorClass, null);
        return this;
    }

    public <V extends Validator<T>> NumericValidatorBuilder<T> validatedBy(Class<V> validatorClass, Object[] arguments) {
        addCustomValidator(validatorClass, arguments);
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
                    min((T) ClassUtils.parse(minAnnotation.value(), Integer.class), true, true);
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
