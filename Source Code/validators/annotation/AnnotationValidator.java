package validators.annotation;

import util.ChainValidatorLinker;
import validators.Validator;
import validators.annotation.parser.AnnotatedClassParser;
import validators.BaseValidator;
import validators.annotation.parser.AnnotatedClassParserImpl;
import validators.result.ValidationResults;

public class AnnotationValidator<T> extends BaseValidator<T> {

    private AnnotatedClassParser<T> parser;

    ChainValidatorLinker<T> chainValidatorLinker;

    public AnnotationValidator(AnnotatedClassParser<T> parser) {
        this.parser = parser;
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
         chainValidatorLinker = parser.parse(t);
    }

}
