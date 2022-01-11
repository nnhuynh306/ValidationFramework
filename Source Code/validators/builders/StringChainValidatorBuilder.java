package validators.builders;


import annotations.*;
import util.comparator.StringComparator;
import util.comparator.StringIntComparator;
import validators.builtin.*;

import util.ClassUtils;
import validators.Validator;
import validators.builtin.MinValidator;
import validators.builtin.RegexValidator;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public class StringChainValidatorBuilder extends BaseChainValidatorBuilder<String> {

    public StringChainValidatorBuilder() {

    }

    public StringChainValidatorBuilder minLength(int min, boolean included) {
        MinValidator<String, Integer> minValidator;
        addValidatorToChain(minValidator = new MinValidator<>(new StringIntComparator(), min, included));
        return this;
    }

    public StringChainValidatorBuilder minLength(int min) {
        return minLength(min, true);
    }

    public StringChainValidatorBuilder notEmpty() {
        addValidatorToChain(new NotEmptyValidator<>());
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
        NotEmptyValidator<String> notEmptyValidator;
        addValidatorToChain(notEmptyValidator = new NotEmptyValidator<>());
        return this;
    }

    public StringChainValidatorBuilder maxLength(int max) {
        maxLength(max, false);
        return this;
    }

    public StringChainValidatorBuilder maxLength(int max, boolean include) {
        MaxValidator<String, Integer> maxValidator;
        addValidatorToChain(maxValidator = new MaxValidator<>(new StringIntComparator(), max, include));
        return this;
    }

    public StringChainValidatorBuilder notNull() {
        addNotNullValidator();
        return this;
    }

    public StringChainValidatorBuilder equal(String value) {
        addValidatorToChain(new EqualValidator<>(new StringComparator(), value));
        return this;
    }

    public StringChainValidatorBuilder regex(String regexStr) {
        addValidatorToChain(new RegexValidator<>(regexStr));
        return this;

    }

    public StringChainValidatorBuilder name(String name) {
        addNameForLastValidator(name);
        return this;

    }

    public StringChainValidatorBuilder validatedBy(Validator<String> validator) {
        addCustomValidator(validator);
        return this;
    }

    public StringChainValidatorBuilder email() {
        return regex("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
    }

    @Override
    public void processAnnotatedField(Field field) {
        for (Annotation annotation : field.getAnnotations()) {
            processAnnotation(annotation, field.getName(), annotation.annotationType());
        }
    }

    public StringChainValidatorBuilder withMessage(String message) {
        setFailedMessageForLastValidator(message);
        return this;
    }

    private void processAnnotation(Annotation annotation, String name, Class<?> annotationClass) {
        try {
            if (annotationClass == NotNull.class) {
                notNull();
            } else if (annotationClass == NotEmpty.class) {
                NotEmpty notEmptyAnnotation = (NotEmpty) annotation;
                notEmpty().withMessage(notEmptyAnnotation.message());
            } else if (annotationClass == Min.class) {
                Min minAnnotation = (Min) annotation;
                minLength((Integer) ClassUtils.parse(minAnnotation.value(), Integer.class), minAnnotation.included()).withMessage(minAnnotation.message());
            } else if (annotationClass == Max.class) {
                Max maxAnnotation = (Max) annotation;
                maxLength((Integer) ClassUtils.parse(maxAnnotation.value(), Integer.class), maxAnnotation.included()).withMessage(maxAnnotation.message());
            } else if (annotationClass == Regex.class) {
                Regex regexAnnotation = (Regex) annotation;
                regex(regexAnnotation.value()).withMessage(regexAnnotation.message());
            } else if (annotationClass == Equal.class) {
                Equal equalAnnotation = (Equal) annotation;
                equal(equalAnnotation.value()).withMessage(equalAnnotation.message());
            } else if (annotationClass == EqualLength.class) {
                EqualLength equalLengthAnnotation = (EqualLength) annotation;
                withMessage(equalLengthAnnotation.message());
            } else if (annotationClass == Email.class) {
                Email emailAnnotation = (Email) annotation;
                email();
                withMessage(emailAnnotation.message());
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
