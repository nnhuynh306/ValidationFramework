package validators.builtin;

import util.comparator.Comparator;

public class MinValidator<T, S> extends BuiltInValidator<T> {
    private final Comparator<T, S> comparator;
    private final S value;
    private boolean included = true;

    public MinValidator(Comparator<T, S> comparator, S value) {
        super();
        this.comparator = comparator;
        this.value = value;
    }

    public MinValidator(Comparator<T, S> comparator, S value, boolean included) {
        super();
        this.comparator = comparator;
        this.value = value;
        this.included = included;
    }

    @Override
    protected String createDefaultFailedMessage() {
        return getName() + " min value must be " + value + " (" + (included?"included":"excluded") + ")";
    }

    @Override
    public boolean isValid(T t) {
        int compareValue = comparator.compare(t, value);
        if (compareValue == 0) return included;
        return compareValue > 0;
    }

}
