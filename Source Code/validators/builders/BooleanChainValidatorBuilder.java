package validators.builders;

import annotations.*;
import util.ClassUtils;
import validators.Validator;
import validators.builtin.AssertFalseValidator;
import validators.builtin.AssertTrueValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BooleanChainValidatorBuilder extends BaseChainValidatorBuilder<Boolean> {
    protected BooleanChainValidatorBuilder() {

    }

    public BooleanChainValidatorBuilder assetTrue() {
        addValidatorToChain(new AssertTrueValidator<>());
        return this;
    }

    public BooleanChainValidatorBuilder assetFalse() {
        addValidatorToChain(new AssertFalseValidator<>());
        return this;
    }

    public BooleanChainValidatorBuilder name(String name) {
        addNameForLastValidator(name);
        return this;
    }

    public <V extends Validator<Boolean>> BooleanChainValidatorBuilder validatedBy(Class<V> validatorClass) {
        validatedBy(validatorClass, null);
        return this;
    }

    public <V extends Validator<Boolean>> BooleanChainValidatorBuilder validatedBy(Class<V> validatorClass, Object[] arguments) {
        addCustomValidator(validatorClass, arguments);
        return this;
    }

    public BooleanChainValidatorBuilder validatedBy(Validator<Boolean> validator) {
        addCustomValidator(validator);
        return this;
    }

    public BooleanChainValidatorBuilder withMessage(String message) {
        setFailedMessageForLastValidator(message);
        return this;
    }

    @Override
    public void processAnnotatedField(Field field) {
        for (Annotation annotation : field.getAnnotations()) {
            processAnnotation(annotation, field.getName(), field.getType());
        }
    }

    private void processAnnotation(Annotation annotation, String name, Class<?> annotationClass) {
        try {
            if (annotationClass == AssertTrue.class) {
                assetTrue().withMessage(((AssertTrue) annotation).message());
            } else if (annotationClass == AssertFalse.class) {
                assetFalse().withMessage(((AssertFalse) annotation).message());
            } else {
                // Custom annotation check
                ValidatedBy validatedBy = annotationClass.getAnnotation(ValidatedBy.class);
                Class<? extends Validator<Boolean>> validatorClass = (Class<? extends Validator<Boolean>>) validatedBy.validatorClass();
                validatedBy(validatorClass);
            }
            name(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
