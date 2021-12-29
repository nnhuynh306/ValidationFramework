package validators.builders;

import util.comparator.Comparator;
import validators.builtin.AssertFalseValidator;
import validators.builtin.AssertTrueValidator;

import java.lang.reflect.Field;

public class BooleanValidatorBuilder <T> extends BaseValidatorBuilder<T>{
    private Comparator<T, T> comparator;

    private final Class<T> TClass;

    protected BooleanValidatorBuilder(Class<T> TClass) {
        this.TClass = TClass;
    }

    public BooleanValidatorBuilder<T> assetTrue() {
        addValidatorToChain(new AssertTrueValidator<>(true));
        return this;
    }

    public BooleanValidatorBuilder<T> assetFalse() {
        addValidatorToChain(new AssertFalseValidator<>(true));
        return this;
    }

    public Class<T> getTClass() {
        return TClass;
    }

    @Override
    public void processAnnotatedField(Field field) {

    }
}
