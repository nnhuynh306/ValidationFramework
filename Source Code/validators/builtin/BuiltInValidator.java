package validators.builtin;

import annotations.NotNull;
import validators.BaseValidator;
import validators.result.ValidationResult;
import validators.result.ValidationResults;

public abstract class BuiltInValidator<T> extends BaseValidator<T> {

    public static boolean DEFAULT_EXIT_WHEN_FAILED = true;
    private final boolean exitWhenFailed;
    private String failedMessage;
    private String name;

    public BuiltInValidator(boolean exitWhenFailed) {
        failedMessage = createDefaultFailedMessage();
        this.exitWhenFailed = exitWhenFailed;
    }

    public BuiltInValidator() {
        failedMessage = createDefaultFailedMessage();
        this.exitWhenFailed = DEFAULT_EXIT_WHEN_FAILED;
    }

    @Override
    public final boolean validate(T t, ValidationResults returnResults) {
        boolean isValid = isValid(t);

        if (returnResults != null) {
            returnResults.add(createResult(isValid));
        }

        if (!hasNext()) {
            return isValid;
        }

        if (!isValid && exitWhenFailed) {
            return false;
        }

        return isValid && nextValidator.validate(t, returnResults);
    }

    protected abstract String createDefaultFailedMessage();

    public abstract boolean isValid(T t);

    public void setFailedMessage(String failedMessage) {
        this.failedMessage = failedMessage;
    }

    private ValidationResult createResult(boolean isValid) {
        return new ValidationResult(isValid ? ValidationResult.RESULT.OK : ValidationResult.RESULT.FAILED,
                getName(),
                isValid ? failedMessage : "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
