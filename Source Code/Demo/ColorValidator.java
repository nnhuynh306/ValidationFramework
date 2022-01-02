package Demo;

import validators.builtin.BuiltInValidator;

public class ColorValidator extends BuiltInValidator<String> {

    public ColorValidator() {
        super();
    }

    @Override
    protected String createDefaultFailedMessage() {
        return null;
    }

    @Override
    public boolean isValid(String s) {
        return s.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }
}
