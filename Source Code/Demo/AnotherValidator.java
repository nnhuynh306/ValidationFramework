package Demo;

import validators.AbstractValidator;

public class AnotherValidator extends AbstractValidator<String> {
    @Override
    public String createFailedMessage() {
        return null;
    }

    @Override
    public String createName() {
        return null;
    }
}
