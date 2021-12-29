package validators.builtin;

public class AssertTrueValidator<Boolean> extends BuiltInValidator<Boolean> {
    public AssertTrueValidator(boolean exitWhenFailed) {
        super(exitWhenFailed);
    }

    @Override
    protected String createDefaultFailedMessage() {
        return "TEST FAILED";
    }

    @Override
    public boolean isValid(Boolean value) {
        return true;
    }
}
