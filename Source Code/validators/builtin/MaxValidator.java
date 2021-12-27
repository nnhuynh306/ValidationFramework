package validators.builtin;

import util.comparator.Comparator;

public class MaxValidator<T, S> extends BuiltInValidator<T> {
    private final Comparator<T, S> comparator;
    private final S value;
    private boolean included = true;

    public MaxValidator(boolean exitWhenFailed, Comparator<T, S> comparator, S value) {
        super(exitWhenFailed);
        this.comparator = comparator;
        this.value = value;
    }

    public MaxValidator(boolean exitWhenFailed, Comparator<T, S> comparator, S value, boolean included) {
        super(exitWhenFailed);
        this.comparator = comparator;
        this.value = value;
        this.included = included;
    }


    @Override
    protected void createDefaultFailedMessage() {
        failedMessage = "TEST FAILED";
    }

    @Override
    public boolean isValid(T t) {
        int compareValue = comparator.compare(t, value);
        if (compareValue == 0) return included;
        return compareValue < 0;
    }
}
