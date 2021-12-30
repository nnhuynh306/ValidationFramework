package validators.builders;


import annotations.Max;
import annotations.NotNull;
import util.comparator.StringComparator;
import util.comparator.StringIntComparator;
import validators.builtin.*;

import annotations.Min;
import annotations.ValidatedBy;
import util.ClassUtils;
import validators.Validator;
import validators.builtin.MinValidator;
import validators.builtin.RegexValidator;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public class StringValidatorBuilder extends BaseValidatorBuilder<String> {

    public StringValidatorBuilder minLength(int min) {
        addValidatorToChain(new MinValidator<>(true, new StringIntComparator(), min));
        return this;
    }

    public StringValidatorBuilder minLength(int min, boolean included, String message) {
        MinValidator<String, Integer> minValidator;
        addValidatorToChain(minValidator = new MinValidator<>(true, new StringIntComparator(), min, included));
        minValidator.setFailedMessage(message);
        return this;
    }

    public StringValidatorBuilder minLength(int min, boolean included) {
        addValidatorToChain(new MinValidator<>(true, new StringIntComparator(), min, included));
        return this;
    }

    public StringValidatorBuilder minLength(int min, String message) {
        MinValidator<String, Integer> minValidator;
        addValidatorToChain(minValidator = new MinValidator<>(true, new StringIntComparator(), min));
        minValidator.setFailedMessage(message);
        return this;
    }


    public StringValidatorBuilder notEmpty() {
        addValidatorToChain(new NotEmptyValidator<>(true));
        return this;
    }


    public <V extends Validator<String>>StringValidatorBuilder validatedBy(Class<V> validatorClass) {
        validatedBy(validatorClass, null);
        return this;
    }

    public <V extends Validator<String>>StringValidatorBuilder validatedBy(Class<V> validatorClass, Object[] arguments) {
        addCustomValidator(validatorClass, arguments);
        return this;
    }

    public StringValidatorBuilder notEmpty(int size) {

        return this;
    }

    public StringValidatorBuilder maxLength(int max) {
        addValidatorToChain(new MaxValidator<>(true,new StringIntComparator(),max));
        return this;
    }

    public StringValidatorBuilder maxLength(int max, boolean include) {
        addValidatorToChain(new MaxValidator<>(true,new StringIntComparator(),max, include));
        return this;
    }

    public StringValidatorBuilder maxLength(int max, String message) {
        MaxValidator<String, Integer> maxValidator;
        addValidatorToChain(maxValidator = new MaxValidator<>(true, new StringIntComparator(), max));
        maxValidator.setFailedMessage(message);
        return this;
    }

    public StringValidatorBuilder notNull() {
        addValidatorToChain(new NotNullValidator<>(true));
        return this;
    }

    public StringValidatorBuilder equal(String value){
        addValidatorToChain(new EqualValidator<>(true, new StringComparator(), value));
        return this;
    }

    public StringValidatorBuilder regex(String regexStr) {
        addValidatorToChain(new RegexValidator<>(true, regexStr));
        return this;

    }

    public StringValidatorBuilder name(String name) {

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

            } else if (annotationClass == Min.class) {

                Min minAnnotation = (Min) annotation;
                String message = minAnnotation.message();
                if (message.isEmpty()) {
                    minLength((Integer) ClassUtils.parse(minAnnotation.value(), Integer.class));
                } else {
                    minLength((Integer) ClassUtils.parse(minAnnotation.value(), Integer.class), message);
                }

            } else if (annotationClass == Max.class) {

                Max maxAnnotation = (Max) annotation;
                String message = maxAnnotation.message();
                if (message.isEmpty()) {
                    maxLength((Integer) ClassUtils.parse(maxAnnotation.value(), Integer.class));
                } else {
                    maxLength((Integer) ClassUtils.parse(maxAnnotation.value(), Integer.class), message);
                }

            } else {

                //Custom annotation check
                ValidatedBy validatedBy = annotationClass.getAnnotation(ValidatedBy.class);
                Class<? extends Validator<String>> validatorClass = (Class<? extends Validator<String>>) validatedBy.validatorClass();
                validatedBy(validatorClass);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
