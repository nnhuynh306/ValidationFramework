package validators.builtin;

public class AssertFalseValidator<Boolean> extends BuiltInValidator<Boolean> {
    public AssertFalseValidator() {
        super();
    }

    @Override
    protected String createDefaultFailedMessage() {
        return "Must be FAILED but TRUE now";
    }

    @Override
    public boolean isValid(Boolean value) {
        return false;
    }
}
