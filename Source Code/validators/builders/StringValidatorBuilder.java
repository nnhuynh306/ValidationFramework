package validators.builders;


import util.comparator.StringComparator;
import util.comparator.StringIntComparator;
import validators.builtin.*;

import annotations.Min;
import annotations.ValidatedBy;
import util.ClassUtils;
import util.comparator.StringIntComparator;
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


    public StringValidatorBuilder notEmpty() {
        addValidatorToChain(new NotEmptyValidator<>(true));


    public <V extends Validator<String>>StringValidatorBuilder validatedBy(Class<V> validatorClass) {
        validatedBy(validatorClass, null);
        return this;
    }

    public <V extends Validator<String>>StringValidatorBuilder validatedBy(Class<V> validatorClass, Object[] arguments) {
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
            if (annotationClass == Min.class) {
                minLength((Integer) ClassUtils.parse(((Min) annotation).value(), Integer.class));
            } else {
                ValidatedBy validatedBy = annotationClass.getAnnotation(ValidatedBy.class);
                Class<? extends Validator<String>> validatorClass = (Class<? extends Validator<String>>) validatedBy.validatorClass();
                validatedBy(validatorClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StringValidatorBuilder notEmpty(int size) {

        return this;
    }

    public StringValidatorBuilder maxLength(int max, boolean include) {
        addValidatorToChain(new MaxValidator<>(true,new StringIntComparator(),max, include));
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
}
