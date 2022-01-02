package Demo;

import validators.AbstractValidator;

public class UserValidator extends AbstractValidator<TestUser> {
    public UserValidator() {
        AddNumericRuleFor(TestUser::getAge, int.class).min(1, true, true).name("int test").withMessage("LOL");

    }

}
