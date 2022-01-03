package validators.builtin;

public class AssertTrueValidator<Boolean> extends BuiltInValidator<Boolean> {
    public AssertTrueValidator() {
        super();
    }

    @Override
    protected String createDefaultFailedMessage() {
        return "Must be TRUE but FAILED now";
    }

    @Override
    public boolean isValid(Boolean value) {
        return true;
    }
}
