package validators.builtin;

public class NotNullValidator<T> extends BuiltInValidator<T> {

    public NotNullValidator() {
        super();
    }

    @Override
    protected String createDefaultFailedMessage() {
        return "TEST FAILED";
    }

    @Override
    public boolean isValid(T t) {
        return t != null;
    }
}
