package validators.builders;

import util.ChainValidatorLinker;
import validators.Validator;
import validators.builtin.NotNullValidator;

import java.lang.reflect.Constructor;

public abstract class BaseValidatorBuilder<T> implements ValidatorBuilder<T> {
    private ChainValidatorLinker<T> chainValidatorLinker = new ChainValidatorLinker<>();

    protected void addValidatorToChain(Validator<T> validator) {
        chainValidatorLinker.add(validator);
    }

    @Override
    public Validator<T> build() {
        return chainValidatorLinker.getFirstValidator();
    }

    protected void addNotNullValidator(boolean exitWhenFailed) {
        addValidatorToChain(new NotNullValidator<T>(exitWhenFailed));
    }

    public void addNameForLastValidator(String name) {
        try {
            chainValidatorLinker.getLastValidator();
        } catch (NullPointerException ignored) {

        }
    }

    protected <V extends Validator<T>> void addCustomValidator(Class<V> validatorClass, Object[] arguments) {
       try {
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
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
