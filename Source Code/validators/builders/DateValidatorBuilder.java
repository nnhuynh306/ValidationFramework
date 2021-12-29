package validators.builders;

import util.comparator.Comparator;
import validators.builtin.MaxValidator;
import validators.builtin.MinValidator;
import validators.builtin.NotNullValidator;

import java.lang.reflect.Field;
import java.util.Date;

public class DateValidatorBuilder<T> extends BaseValidatorBuilder<T>{
    private Comparator<T, T> comparator;

    private final Class<T> TClass;

    protected DateValidatorBuilder(Comparator<T, T> comparator, Class<T> TClass) {
        this.comparator = comparator;
        this.TClass = TClass;
    }

    public DateValidatorBuilder<T> min(T value, boolean included, boolean exitWhenFailed) {
        addValidatorToChain(new MinValidator<T, T>(exitWhenFailed, comparator, value, included));
        return this;
    }

    public DateValidatorBuilder<T> max(T value, boolean included,boolean exitWhenFailed) {
        addValidatorToChain(new MaxValidator<T,T>(exitWhenFailed,comparator,value,included));
        return this;
    }

    public Class<T> getTClass() {
        return TClass;
    }

    @Override
    public void processAnnotatedField(Field field) {

    }
}
