package validators.builtin;

public class NotEmptyValidator<T> extends BuiltInValidator<T> {
    public NotEmptyValidator(boolean exitWhenFailed) {
        super(exitWhenFailed);
    }

    @Override
    protected void createDefaultFailedMessage() {
        failedMessage = "TEST FAILED";
    }

    @Override
    public boolean isValid(T t) {
        if (t instanceof String) return ((String) t).length() > 0;
        return false;
    }
}
