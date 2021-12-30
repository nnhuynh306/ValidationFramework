package Demo;

import validators.AbstractValidator;
import validators.BaseValidator;
import validators.builtin.BuiltInValidator;
import validators.result.ValidationResults;

public class ColorValidator extends BuiltInValidator<String> {

    public ColorValidator() {
        super();
    }

    @Override
    protected String createDefaultFailedMessage() {
        return "TEST FAILED";
    }

    @Override
    public boolean isValid(String s) {
        return s.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }
}
