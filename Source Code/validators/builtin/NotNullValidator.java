package validators.builtin;

public class NotNullValidator<T> extends BuiltInValidator<T> {

    public NotNullValidator(boolean exitWhenFailed) {
        super(exitWhenFailed);
    }

    @Override
    protected void createDefaultFailedMessage() {

    }

    @Override
    public boolean isValid(T t) {
        return t != null;
    }
}
