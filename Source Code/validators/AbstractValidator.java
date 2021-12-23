package validators;

import validators.builders.NumericValidatorBuilder;
import validators.builders.StringValidatorBuilder;
import validators.builders.ValidatorBuilderFactory;
import util.ChainValidatorLinker;
import validators.builtin.Rule;
import validators.result.ValidationResults;

import java.util.function.Function;

public abstract class AbstractValidator<T> extends BaseValidator<T>  {

    public ChainValidatorLinker<T> chainValidatorLinker = new ChainValidatorLinker<>();

    public AbstractValidator() {
    }

    @Override
    public final boolean validate(T t, ValidationResults returnResults) {
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
}
