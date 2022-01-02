package validators;


import util.ChainValidatorLinker;
import validators.builders.*;
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

        boolean result;
        if (firstValidator != null) {
            result = chainValidatorLinker.getFirstValidator().validate(t, returnResults);
        }
        else {
            result = true;
        }

        addResult(result, returnResults);

        return result;
    }


    public final StringChainValidatorBuilder AddStringRuleFor(Function<T, String> getStringFunction) {
        StringChainValidatorBuilder stringValidatorBuilder = ChainValidatorBuilderFactory.getStringChainValidatorBuilder();
        chainValidatorLinker.add(new Rule<>(stringValidatorBuilder, getStringFunction));
        return stringValidatorBuilder;
    }

    public final DateChainValidatorBuilder AddDateRuleFor(Function<T, Date> getDateFunction){
        DateChainValidatorBuilder dateValidatorBuilder = ChainValidatorBuilderFactory.getDateChainValidatorBuilder();
        chainValidatorLinker.add(new Rule<>(dateValidatorBuilder,getDateFunction));
        return dateValidatorBuilder;
    }

    public final <S> CustomChainValidatorBuilder<S> AddCustomRuleFor(Function<T, S> getFunction) {
        CustomChainValidatorBuilder<S> customValidatorBuilder = ChainValidatorBuilderFactory.getCustomChainValidatorBuilder();
        chainValidatorLinker.add(new Rule<>(customValidatorBuilder, getFunction));
        return customValidatorBuilder;
    }

    public final<S> NumericChainValidatorBuilder<S> AddNumericRuleFor(Function<T, S> getFunction, Class<S> sClass) throws IllegalArgumentException {
        NumericChainValidatorBuilder<S> numericValidatorBuilder =  ChainValidatorBuilderFactory.getNumericChainValidatorBuilderOf(sClass);

        if (numericValidatorBuilder == null) {
            throw new IllegalArgumentException(sClass.getName() + " is not a supported numeric type");
        }

        chainValidatorLinker.add(new Rule<>(numericValidatorBuilder, getFunction));
        return numericValidatorBuilder;
    }

    public final BooleanChainValidatorBuilder AddBooleanRuleFor(Function<T, Boolean> getFunction) {
        BooleanChainValidatorBuilder booleanValidatorBuilder =  ChainValidatorBuilderFactory.getBooleanChainValidatorBuilder();
        chainValidatorLinker.add(new Rule<>(booleanValidatorBuilder, getFunction));
        return booleanValidatorBuilder;
    }
}
