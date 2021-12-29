package validators.builders;

import util.comparator.StringComparator;
import util.comparator.StringIntComparator;
import validators.builtin.*;


public class StringValidatorBuilder extends BaseValidatorBuilder<String> {

    public StringValidatorBuilder minLength(int min) {
        addValidatorToChain(new MinValidator<>(true, new StringIntComparator(), min));
        return this;
    }

    public StringValidatorBuilder notEmpty() {
        addValidatorToChain(new NotEmptyValidator<>(true));
        return this;
    }

    public StringValidatorBuilder maxLength(int max, boolean include) {
        addValidatorToChain(new MaxValidator<>(true,new StringIntComparator(),max, include));
        return this;
    }

    public StringValidatorBuilder notNull() {
        addValidatorToChain(new NotNullValidator<>(true));
        return this;
    }

    public StringValidatorBuilder equal(String value){
        addValidatorToChain(new EqualValidator<>(true, new StringComparator(), value));
        return this;
    }

    public StringValidatorBuilder regex(String regexStr) {
        addValidatorToChain(new RegexValidator<>(true, regexStr));
        return this;
    }
}
