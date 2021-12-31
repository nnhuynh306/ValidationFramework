package validators.builders;

import util.comparator.Comparator;
import validators.builtin.AssertFalseValidator;
import validators.builtin.AssertTrueValidator;

import java.lang.reflect.Field;

public class BooleanValidatorBuilder extends BaseValidatorBuilder<Boolean>{
    protected BooleanValidatorBuilder() {

    }

    public BooleanValidatorBuilder assetTrue() {
        addValidatorToChain(new AssertTrueValidator<>(true));
        return this;
    }

    public BooleanValidatorBuilder assetFalse() {
        addValidatorToChain(new AssertFalseValidator<>(true));
        return this;
    }

    public BooleanValidatorBuilder name(String name) {
        addNameForLastValidator(name);
        return this;
    }

    @Override
    public void processAnnotatedField(Field field) {

    }
}
