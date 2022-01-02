package Demo;

import validators.AbstractValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class UserValidator extends AbstractValidator<TestUser> {
    public UserValidator() {
        AddNumericRuleFor(TestUser::getAge, int.class).min(1, true).name("int test").withMessage("LOL");

    }

}
