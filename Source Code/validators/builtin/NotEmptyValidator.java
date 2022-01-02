package validators.builtin;

public class NotEmptyValidator<T> extends BuiltInValidator<T> {
    public NotEmptyValidator() {
        super();
    }

    @Override
    protected String createDefaultFailedMessage() {
        return getName() + " must be not empty";
    }


    @Override
    public boolean isValid(T t) {
        if (t instanceof String) return ((String) t).length() > 0;
        return false;
    }
}
