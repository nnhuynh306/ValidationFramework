package Demo;

import validators.AbstractValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class UserValidator extends AbstractValidator<TestUser> {
    public UserValidator() {
        AddStringRuleFor(TestUser::getName).minLength(4, true, "Halo").name("username length");
        AddNumericRuleFor(TestUser::getAge, int.class);
    }

}
