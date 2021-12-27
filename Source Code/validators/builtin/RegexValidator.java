package validators.builtin;

public class RegexValidator<T> extends BuiltInValidator<T> {
    private final String regex;

    public RegexValidator(boolean exitWhenFailed, String regex) {
        super(exitWhenFailed);
        this.regex = regex;
    }

    @Override
    protected void createDefaultFailedMessage() {
        failedMessage = "TEST FAILED";
    }

    @Override
    public boolean isValid(T t) {
        if (t instanceof String) return ((String) t).matches(regex);
        return false;
    }
}
