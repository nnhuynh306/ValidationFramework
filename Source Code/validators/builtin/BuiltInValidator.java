package validators.builtin;

import annotations.NotNull;
import validators.BaseValidator;
import validators.result.ValidationResult;
import validators.result.ValidationResults;

public abstract class BuiltInValidator<T> extends BaseValidator<T> {

    public static boolean DEFAULT_EXIT_WHEN_FAILED = true;
    private final boolean exitWhenFailed;

    private boolean customMessage = false;

    public BuiltInValidator(boolean exitWhenFailed) {
        this.exitWhenFailed = exitWhenFailed;
    }

    public BuiltInValidator() {
        this.exitWhenFailed = DEFAULT_EXIT_WHEN_FAILED;
    }

    @Override
    public final boolean validate(T t, ValidationResults returnResults) {
        boolean isValid = isValid(t);

        if (!customMessage) {
            super.setFailedMessage(createDefaultFailedMessage());
        }
        addResult(isValid, returnResults);

        addResult(isValid, returnResults);

        if (!hasNext()) {
            return isValid;
        }

        if (!isValid && exitWhenFailed) {
            return false;
        }

        return isValid && nextValidator.validate(t, returnResults);
    }

    @Override
    public void setFailedMessage(String message) {
        super.setFailedMessage(message);
        customMessage = true;
    }

    protected abstract String createDefaultFailedMessage();

    public abstract boolean isValid(T t);

}
