package validators.builtin;

public class RegexValidator <T> extends BuiltInValidator<T>{
    public RegexValidator(boolean exitWhenFailed) {
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
