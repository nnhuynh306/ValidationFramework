package validators.builders;

import validators.builtin.AssertFalseValidator;
import validators.builtin.AssertTrueValidator;

import java.lang.reflect.Field;

public class BooleanChainValidatorBuilder extends BaseChainValidatorBuilder<Boolean> {
    protected BooleanChainValidatorBuilder() {

    }

    public BooleanChainValidatorBuilder assetTrue() {
        addValidatorToChain(new AssertTrueValidator<>());
        return this;
    }

    public BooleanChainValidatorBuilder assetFalse() {
        addValidatorToChain(new AssertFalseValidator<>());
        return this;
    }

    public BooleanChainValidatorBuilder name(String name) {
        addNameForLastValidator(name);
        return this;
    }

    public BooleanChainValidatorBuilder withMessage(String message) {
        setFailedMessageForLastValidator(message);
        return this;
    }

    @Override
    public void processAnnotatedField(Field field) {

    }
}
