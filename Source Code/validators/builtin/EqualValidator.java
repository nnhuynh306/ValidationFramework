package validators.builtin;

import util.comparator.Comparator;

public class EqualValidator<T, S> extends BuiltInValidator<T> {
    private final Comparator<T, S> comparator;
    private final S value;

    public EqualValidator(Comparator<T, S> comparator, S value) {
        super();
        this.comparator = comparator;
        this.value = value;
    }

    @Override
    protected String createDefaultFailedMessage() {
        return getName() + "must equal " + value;
    }

    @Override
    public boolean isValid(T t) {
        return comparator.compare(t, value) == 0;
    }
}
