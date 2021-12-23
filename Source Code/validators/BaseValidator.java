package validators;

import validators.result.ValidationResults;

public abstract class BaseValidator<T> implements Validator<T> {

    protected Validator<T> nextValidator;

    @Override
    public abstract boolean validate(T t, ValidationResults returnResults);

    @Override
    public void setNext(Validator<T> validator) {
        this.nextValidator = validator;
    }

    @Override
    public boolean hasNext() {
        return nextValidator != null;
    }
}
