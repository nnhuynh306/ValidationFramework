package validators.builtin;

public class AssertTrueValidator<T> extends BuiltInValidator<T> {
    public AssertTrueValidator(boolean exitWhenFailed) {
        super(exitWhenFailed);
    }

    @Override
    protected String createDefaultFailedMessage() {
        return "TEST FAILED";
    }

    @Override
    public boolean isValid(T t) {
        return true;
    }
}
