package validators.builders;

import util.comparator.Comparator;
import validators.builtin.*;

public class NumericValidatorBuilder<T> extends BaseValidatorBuilder<T> {
    private Comparator<T, T> comparator;

    private final Class<T> TClass;

    protected NumericValidatorBuilder(Comparator<T, T> comparator, Class<T> TClass) {
        this.comparator = comparator;
        this.TClass = TClass;
    }

    public NumericValidatorBuilder<T> min(T value, boolean included, boolean exitWhenFailed) {
        addValidatorToChain(new MinValidator<T, T>(exitWhenFailed, comparator, value, included));
        return this;
    }

    public NumericValidatorBuilder<T> max(T value, boolean included, boolean exitWhenFailed) {
        addValidatorToChain(new MaxValidator<>(exitWhenFailed, comparator, value, included));
        return this;
    }

    public NumericValidatorBuilder<T> notNull(boolean exitWhenFailed) {
        addValidatorToChain(new NotNullValidator<T>(exitWhenFailed));
        return this;
    }

    public NumericValidatorBuilder<T> notEmpty() {
        addValidatorToChain(new NotEmptyValidator<>(true));
        return this;
    }

    public NumericValidatorBuilder<T> equal(T value){
        addValidatorToChain(new EqualValidator<T, T>(true, comparator, value));
        return this;
    }

    public Class<T> getTClass() {
        return TClass;
    }
}
