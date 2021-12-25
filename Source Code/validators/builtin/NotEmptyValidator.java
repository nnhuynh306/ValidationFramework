package validators.builtin;

public class NotEmptyValidator <T,S> extends BuiltInValidator<T>{
    public NotEmptyValidator(boolean exitWhenFailed) {
        super(exitWhenFailed);
    }

    @Override
    protected void createDefaultFailedMessage() {

    }

    @Override
    public boolean isValid(T t) {
        return false;
    }
}
