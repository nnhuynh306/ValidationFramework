package validators.builders;

import util.comparator.Comparator;

public class BooleanValidatorBuilder <T> extends BaseValidatorBuilder<T>{
    private Comparator<T, T> comparator;

    private final Class<T> TClass;

    protected BooleanValidatorBuilder(Class<T> TClass) {
        this.TClass = TClass;
    }

    public BooleanValidatorBuilder<T> assetTrue() {
        return this;
    }

    public BooleanValidatorBuilder<T> assetFalse() {
        return this;
    }

    public Class<T> getTClass() {
        return TClass;
    }
}
