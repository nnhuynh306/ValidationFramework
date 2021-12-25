package validators.builtin;

import util.comparator.Comparator;

public class EqualValidator <T,S> extends BuiltInValidator <T>{
    private Comparator<T, S> comparator;

    private boolean included = true;

    public EqualValidator(boolean exitWhenFailed, Comparator<T, S> comparator, S value) {
        super(exitWhenFailed);
        this.comparator = comparator;
        this.value = value;
    }

    public EqualValidator(boolean exitWhenFailed, Comparator<T, S> comparator, S value, boolean included) {
        super(exitWhenFailed);
        this.comparator = comparator;
        this.value = value;
        this.included = included;
    }

    private S value;

    @Override
    protected void createDefaultFailedMessage() {
        failedMessage = "TEST FAILED";
    }

    @Override
    public boolean isValid(T t) {
        int compareValue = comparator.compare(t, value);
        if (compareValue > 0) {
            return false;
        } else if (compareValue == 0) {
            return true;
        } else {
            return false;
        }
    }
}
