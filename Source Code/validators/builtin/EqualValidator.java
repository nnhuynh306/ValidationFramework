package validators.builtin;

import util.comparator.Comparator;

public class EqualValidator<T, S> extends BuiltInValidator<T> {
    private final Comparator<T, S> comparator;
    private final S value;

    public EqualValidator(boolean exitWhenFailed, Comparator<T, S> comparator, S value) {
        super(exitWhenFailed);
        this.comparator = comparator;
        this.value = value;
    }

    @Override
    protected void createDefaultFailedMessage() {
        failedMessage = "TEST FAILED";
    }

    @Override
    public boolean isValid(T t) {
        return comparator.compare(t, value) == 0;
    }
}
