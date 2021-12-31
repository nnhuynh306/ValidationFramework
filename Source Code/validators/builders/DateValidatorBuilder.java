package validators.builders;

import util.comparator.DateComparator;
import validators.builtin.EqualValidator;
import validators.builtin.MinValidator;
import validators.builtin.NotEmptyValidator;
import validators.builtin.NotNullValidator;

import java.lang.reflect.Field;
import java.util.Date;

public class DateValidatorBuilder extends BaseValidatorBuilder<Date>{

    protected DateValidatorBuilder() {

    }

    public DateValidatorBuilder minDate(Date min) {
        addValidatorToChain(new MinValidator<>(true,new DateComparator(),min));
        return this;
    }

    public DateValidatorBuilder notEmpty() {
        addValidatorToChain(new NotEmptyValidator<>(true));
        return this;
    }

    public DateValidatorBuilder maxDate(Date max, boolean include) {
        addValidatorToChain(new MinValidator<>(true,new DateComparator(),max,include));
        return this;
    }

    public DateValidatorBuilder notNull() {
        addValidatorToChain(new NotNullValidator<>(true));
        return this;
    }

    public DateValidatorBuilder equal(Date value){
        addValidatorToChain(new EqualValidator<>(true,new DateComparator(),value));
        return this;
    }

    public DateValidatorBuilder name(String name) {
        addNameForLastValidator(name);
        return this;
    }

    @Override
    public void processAnnotatedField(Field field) {

    }
}
