package util;

import validators.Validator;

public class ChainValidatorLinker<T>  {
    private Validator<T> firstValidator;
    private Validator<T> lastValidator;

    public ChainValidatorLinker() {

    }

    public void add(Validator<T> validator) {
        if (firstValidator == null) {
            firstValidator = validator;
            lastValidator = validator;
        } else {
            lastValidator.setNext(validator);
            lastValidator = validator;
        }
    }

    public Validator<T> getFirstValidator() {
        return firstValidator;
    }
}
