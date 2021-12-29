package validators;

import util.ChainValidatorLinker;
import validators.builders.DateValidatorBuilder;
import validators.builders.NumericValidatorBuilder;
import validators.builders.StringValidatorBuilder;
import validators.builders.ValidatorBuilderFactory;
import validators.builtin.Rule;
import validators.result.ValidationResults;

import java.util.Date;
import java.util.function.Function;

public abstract class AbstractValidator<T> extends BaseValidator<T>  {

    protected ChainValidatorLinker<T> chainValidatorLinker = new ChainValidatorLinker<>();

    public AbstractValidator() {
    }

    @Override
    public boolean validate(T t, ValidationResults returnResults) {
        Validator<T> firstValidator = chainValidatorLinker.getFirstValidator();

        if (firstValidator != null) {
            return chainValidatorLinker.getFirstValidator().validate(t, returnResults);
        }
        else {
            return true;
        }
    }


    public final StringValidatorBuilder AddStringRuleFor(Function<T, String> getStringFunction) {
        StringValidatorBuilder stringValidatorBuilder = (StringValidatorBuilder) ValidatorBuilderFactory.getStringValidatorBuilder();
        chainValidatorLinker.add(new Rule<T, String>(stringValidatorBuilder, getStringFunction));
        return stringValidatorBuilder;
    }

    public final NumericValidatorBuilder<Integer> AddIntegerRuleFor(Function<T, Integer> getIntegerFunction) {
        NumericValidatorBuilder<Integer> integerValidatorBuilder
                =  ValidatorBuilderFactory.getNumericBuilderOf(Integer.class);
        chainValidatorLinker.add(new Rule<T, Integer>(integerValidatorBuilder, getIntegerFunction));
        return integerValidatorBuilder;
    }

    public final DateValidatorBuilder AddDateRuleFor(Function<T, Date> getDateFunction){
        DateValidatorBuilder dateValidatorBuilder = (DateValidatorBuilder) ValidatorBuilderFactory.getDateValidatorBuilder();
        chainValidatorLinker.add(new Rule<T,Date>(dateValidatorBuilder,getDateFunction));
        return dateValidatorBuilder;
    }
}
