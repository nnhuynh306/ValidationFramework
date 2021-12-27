package validators.builtin;

import validators.BaseValidator;
import validators.Validator;
import validators.result.ValidationResult;
import validators.result.ValidationResults;

public abstract class BuiltInValidator<T> extends BaseValidator<T> {

    private final boolean exitWhenFailed;
    protected String failedMessage;
    protected String name;

    public BuiltInValidator(boolean exitWhenFailed) {
        createDefaultFailedMessage();
        this.exitWhenFailed = exitWhenFailed;
    }

    @Override
    public final boolean validate(T t, ValidationResults returnResults) {
        boolean isValid = isValid(t);

        if (!hasNext()) return isValid;

        if (!isValid && exitWhenFailed) return false;

        return nextValidator.validate(t, returnResults);
    }

    protected abstract void createDefaultFailedMessage();

    public abstract boolean isValid(T t);

    public void setFailedMessage(String failedMessage) {
        this.failedMessage = failedMessage;
    }

    private ValidationResult createResult(boolean isValid) {
        return new ValidationResult(isValid ? ValidationResult.RESULT.OK : ValidationResult.RESULT.FAILED,
                name,
                isValid ? failedMessage : "");
    }


}
