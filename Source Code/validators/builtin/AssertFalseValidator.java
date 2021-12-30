package validators.builtin;

public class AssertFalseValidator <T> extends BuiltInValidator<T>{
    public AssertFalseValidator(boolean exitWhenFailed) {
        super(exitWhenFailed);
    }

    @Override
    protected String createDefaultFailedMessage() {
        return "TEST FAILED";
    }

    @Override
    public boolean isValid(T t) {
        return false;
    }
}
