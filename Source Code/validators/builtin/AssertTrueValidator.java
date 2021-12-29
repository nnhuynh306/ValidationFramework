package validators.builtin;

public class AssertTrueValidator<T> extends BuiltInValidator<T> {
    public AssertTrueValidator(boolean exitWhenFailed) {
        super(exitWhenFailed);
    }

    @Override
    protected void createDefaultFailedMessage() {

    }

    @Override
    public boolean isValid(T t) {
        return true;
    }
}
