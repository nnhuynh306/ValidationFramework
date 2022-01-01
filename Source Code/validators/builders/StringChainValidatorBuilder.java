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


public class StringChainValidatorBuilder extends BaseChainValidatorBuilder<String> {

    protected StringChainValidatorBuilder() {

    }

    public StringChainValidatorBuilder minLength(int min, boolean included, String message) {
        MinValidator<String, Integer> minValidator;
        addValidatorToChain(minValidator = new MinValidator<>(true, new StringIntComparator(), min, included));
        if (message != null && !message.isEmpty()) {
            minValidator.setFailedMessage(message);
        }
        return this;
    }

    public StringChainValidatorBuilder minLength(int min, boolean included) {
        minLength(min, included, null);
        return this;
    }

    public StringChainValidatorBuilder notEmpty() {
        addValidatorToChain(new NotEmptyValidator<>(true));
        return this;
    }


    public <V extends Validator<String>> StringChainValidatorBuilder validatedBy(Class<V> validatorClass) {
        validatedBy(validatorClass, null);
        return this;
    }

    public <V extends Validator<String>> StringChainValidatorBuilder validatedBy(Class<V> validatorClass, Object[] arguments) {
        addCustomValidator(validatorClass, arguments);
        return this;
    }

    public StringChainValidatorBuilder notEmpty(int size) {

        return this;
    }

    public StringChainValidatorBuilder maxLength(int max, boolean include) {
        maxLength(max, include, null);
        return this;
    }

    public StringChainValidatorBuilder maxLength(int max, boolean include, String message) {
        MaxValidator<String, Integer> maxValidator;
        addValidatorToChain(maxValidator = new MaxValidator<>(true, new StringIntComparator(), max, include));
        if (message != null && !message.isEmpty()) {
            maxValidator.setFailedMessage(message);
        }
        return this;
    }

    public StringChainValidatorBuilder notNull() {
        addValidatorToChain(new NotNullValidator<>(true));
        return this;
    }

    public StringChainValidatorBuilder equal(String value){
        addValidatorToChain(new EqualValidator<>(true, new StringComparator(), value));
        return this;
    }

    public StringChainValidatorBuilder regex(String regexStr) {
        addValidatorToChain(new RegexValidator<>(true, regexStr));
        return this;

    }

    public StringChainValidatorBuilder name(String name) {
        addNameForLastValidator(name);
        return this;

    }

    public StringChainValidatorBuilder withMessage(String message) {
        setFailedMessageForLastValidator(message);
        return this;
    }

    @Override
    public void processAnnotatedField(Field field) {
        for (Annotation annotation: field.getAnnotations()) {
            processAnnotation(annotation, field.getName());
        }
    }

    private void processAnnotation(Annotation annotation, String name) {
        try {
            Class<?> annotationClass = annotation.annotationType();

            if (annotationClass == NotNull.class) {

                notNull();

            } else if (annotationClass == Min.class) {

                Min minAnnotation = (Min) annotation;
                minLength((Integer) ClassUtils.parse(minAnnotation.value(), Integer.class), minAnnotation.included(), minAnnotation.message());

            } else if (annotationClass == Max.class) {

                Max maxAnnotation = (Max) annotation;
                maxLength((Integer) ClassUtils.parse(maxAnnotation.value(), Integer.class), maxAnnotation.included(), maxAnnotation.message());

            } else {

                //Custom annotation check
                ValidatedBy validatedBy = annotationClass.getAnnotation(ValidatedBy.class);
                Class<? extends Validator<String>> validatorClass = (Class<? extends Validator<String>>) validatedBy.validatorClass();
                validatedBy(validatorClass);

            }

            name(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
