package validators;

import validators.result.ValidationResult;
import validators.result.ValidationResults;

public abstract class BaseValidator<T> implements Validator<T> {

    protected Validator<T> nextValidator;

    private String name = "";

    private String failedMessage = "";

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFailedMessage(String failedMessage) {
        this.failedMessage = failedMessage;
    }

    protected void addResult(boolean isValid, ValidationResults results) {
        if (results != null) {
            results.add(ValidationResult.create(isValid,
                    getName(), isValid ? "" : failedMessage));
        }
    }
}
