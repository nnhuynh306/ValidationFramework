package validators.builders;

import util.comparator.Comparator;
import validators.builtin.MinValidator;
import validators.builtin.NotNullValidator;

public class NumericValidatorBuilder<T> extends BaseValidatorBuilder<T> {
    private Comparator<T, T> comparator;

    protected NumericValidatorBuilder(Comparator<T, T> comparator) {
        this.comparator = comparator;
    }

    public NumericValidatorBuilder<T> min(T value, boolean included, boolean exitWhenFailed) {
        addValidatorToChain(new MinValidator<T, T>(exitWhenFailed, comparator, value, included));
        return this;
    }

    public NumericValidatorBuilder<T> notNull(boolean exitWhenFailed) {
        addValidatorToChain(new NotNullValidator<T>(exitWhenFailed));
        return this;
    }
}
