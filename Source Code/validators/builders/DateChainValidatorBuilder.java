package validators.builders;

import annotations.*;
import util.ClassUtils;
import util.comparator.DateComparator;
import validators.Validator;
import validators.builtin.EqualValidator;
import validators.builtin.MinValidator;
import validators.builtin.NotEmptyValidator;
import validators.builtin.NotNullValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

public class DateChainValidatorBuilder extends BaseChainValidatorBuilder<Date> {

    protected DateChainValidatorBuilder() {

    }

    public DateChainValidatorBuilder minDate(Date min, boolean include) {
        addValidatorToChain(new MinValidator<>(new DateComparator(),min,include));
        return this;
    }

    public DateChainValidatorBuilder minDate(Date min) {
        return minDate(min,true);
    }

    public DateChainValidatorBuilder maxDate(Date max, boolean include) {
        addValidatorToChain(new MinValidator<>(new DateComparator(),max,include));
        return this;
    }

    public DateChainValidatorBuilder maxDate(Date max) {
        return minDate(max,true);
    }

    public DateChainValidatorBuilder notEmpty() {
        addValidatorToChain(new NotEmptyValidator<>());
        return this;
    }

    public DateChainValidatorBuilder notNull() {
        addNotNullValidator();
        return this;
    }

    public DateChainValidatorBuilder equal(Date value){
        addValidatorToChain(new EqualValidator<>(new DateComparator(),value));
        return this;
    }

    public DateChainValidatorBuilder name(String name) {
        addNameForLastValidator(name);
        return this;
    }

    public DateChainValidatorBuilder withMessage(String message) {
        setFailedMessageForLastValidator(message);
        return this;
    }

    public <V extends Validator<Date>> DateChainValidatorBuilder validatedBy(Class<V> validatorClass) {
        validatedBy(validatorClass, null);
        return this;
    }

    public <V extends Validator<Date>> DateChainValidatorBuilder validatedBy(Class<V> validatorClass, Object[] arguments) {
        addCustomValidator(validatorClass, arguments);
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
                NotNull notNullAnnotation = (NotNull) annotation;
                notEmpty().withMessage(notNullAnnotation.message());
            } else if(annotationClass == NotEmpty.class){
                NotEmpty notEmptyAnnotation = (NotEmpty) annotation;
                notEmpty().withMessage(notEmptyAnnotation.message());
            } else if (annotationClass == Min.class) {
                Min minAnnotation = (Min) annotation;
                minDate((Date) ClassUtils.parse(minAnnotation.value(),Date.class), minAnnotation.included()).withMessage(minAnnotation.message());
            } else if (annotationClass == Max.class) {
                Max maxAnnotation = (Max) annotation;
                maxDate((Date) ClassUtils.parse(maxAnnotation.value(),Date.class), maxAnnotation.included()).withMessage(maxAnnotation.message());
            } else if (annotationClass == Equal.class){
                Equal equalAnnotation = (Equal) annotation;
                equal((Date) ClassUtils.parse(equalAnnotation.value(),Date.class)).withMessage(equalAnnotation.message());
            }
            else {
                //Custom annotation check
                ValidatedBy validatedBy = annotationClass.getAnnotation(ValidatedBy.class);
                Class<? extends Validator<Date>> validatorClass = (Class<? extends Validator<Date>>) validatedBy.validatorClass();
                validatedBy(validatorClass);
            }

            name(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
