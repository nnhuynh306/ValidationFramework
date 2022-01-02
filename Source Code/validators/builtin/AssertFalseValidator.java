package validators.builtin;

public class AssertFalseValidator<Boolean> extends BuiltInValidator<Boolean> {
    public AssertFalseValidator(boolean exitWhenFailed) {
        super(exitWhenFailed);
    }

    @Override
    protected String createDefaultFailedMessage() {
        return "TEST FAILED";
    }

    @Override
    public boolean isValid(Boolean value) {
        return false;
    }
}
