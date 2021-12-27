package validators.builders;

import annotations.Min;
import annotations.ValidatedBy;
import util.ClassUtils;
import util.comparator.StringIntComparator;
import validators.Validator;
import validators.builtin.MinValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public class StringValidatorBuilder extends BaseValidatorBuilder<String> {

    public StringValidatorBuilder minLength(int value) {
        addValidatorToChain(new MinValidator<String, Integer>(true, new StringIntComparator(), value));
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
}
