package validators.builders;

import validators.Validator;
import validators.builtin.NotNullValidator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CustomValidatorBuilder<T> extends BaseValidatorBuilder<T> {

    public CustomValidatorBuilder() {
    }

    public CustomValidatorBuilder<T> notNull() {
        addValidatorToChain(new NotNullValidator<>(false));
        return this;
    }

    public <V extends Validator<T>> CustomValidatorBuilder<T> validatedBy(Class<V> validatorClass) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        return validatedBy(validatorClass, null);
    }

    public <V extends Validator<T>> CustomValidatorBuilder<T> validatedBy(Class<V> validatorClass, Object[] arguments) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<V> validatorClassConstructor;
        Validator<T> validator;
        if (arguments != null && arguments.length > 0) {
            Class<?>[] argClasses = new Class[arguments.length];

            for (int i = 0; i < argClasses.length; i++) {
                argClasses[i] = arguments[i].getClass();
            }

            validatorClassConstructor = validatorClass.getConstructor(argClasses);
            validator = validatorClassConstructor.newInstance(arguments);
        } else {
            validatorClassConstructor = validatorClass.getConstructor();
            validator = validatorClassConstructor.newInstance();
        }
        addValidatorToChain(validator);
        return this;
    }
}
