package validators.builders;

import util.comparator.DateComparator;
import validators.builtin.EqualValidator;
import validators.builtin.MinValidator;
import validators.builtin.NotEmptyValidator;
import validators.builtin.NotNullValidator;

import java.lang.reflect.Field;
import java.util.Date;

public class DateChainValidatorBuilder extends BaseChainValidatorBuilder<Date> {

    protected DateChainValidatorBuilder() {

    }

    public DateChainValidatorBuilder minDate(Date min) {
        addValidatorToChain(new MinValidator<>(true,new DateComparator(),min));
        return this;
    }

    public DateChainValidatorBuilder notEmpty() {
        addValidatorToChain(new NotEmptyValidator<>(true));
        return this;
    }

    public DateChainValidatorBuilder maxDate(Date max, boolean include) {
        addValidatorToChain(new MinValidator<>(true,new DateComparator(),max,include));
        return this;
    }

    public DateChainValidatorBuilder notNull() {
        addValidatorToChain(new NotNullValidator<>(true));
        return this;
    }

    public DateChainValidatorBuilder equal(Date value){
        addValidatorToChain(new EqualValidator<>(true,new DateComparator(),value));
        return this;
    }

    public DateChainValidatorBuilder name(String name) {
        addNameForLastValidator(name);
        return this;
    }

    public DateChainValidatorBuilder withMessage(String message) {
        setFailedMessageForLastValidator(message);
        return this;
    }

    @Override
    public void processAnnotatedField(Field field) {

    }
}
