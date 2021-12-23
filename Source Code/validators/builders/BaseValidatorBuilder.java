package validators.builders;

import util.ChainValidatorLinker;
import validators.Validator;

public class BaseValidatorBuilder<T> implements ValidatorBuilder<T> {
    private ChainValidatorLinker<T> chainValidatorLinker = new ChainValidatorLinker<>();

    protected void addValidatorToChain(Validator<T> validator) {
        chainValidatorLinker.add(validator);
    }

    @Override
    public Validator<T> build() {
        return chainValidatorLinker.getFirstValidator();
    }
}
