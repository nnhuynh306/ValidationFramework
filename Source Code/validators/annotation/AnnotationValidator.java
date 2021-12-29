package validators.annotation;

import util.ChainValidatorLinker;
import validators.BaseValidator;
import validators.Validator;
import validators.builders.ValidatorBuilder;
import validators.builders.ValidatorBuilderFactory;
import validators.result.ValidationResults;

import java.lang.reflect.Field;

public class AnnotationValidator<T> extends BaseValidator<T> {

     ChainValidatorLinker<T> chainValidatorLinker;

    public AnnotationValidator() {
    }


    @Override
    public boolean validate(T t, ValidationResults returnResults) {
        if (chainValidatorLinker == null) {
            addRulesFrom(t);
        }

        try {
            return chainValidatorLinker.getFirstValidator().validate(t, returnResults);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return true;
        }
    }


    private void addRulesFrom(T t) {
        chainValidatorLinker = new ChainValidatorLinker<>();
        for (Field field: t.getClass().getDeclaredFields()) {
            chainValidatorLinker.add(new AnnotatedFieldValidator<>(field, field.getType()));
        }
    }

}
