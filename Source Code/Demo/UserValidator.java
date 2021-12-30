package Demo;

import validators.AbstractValidator;

import java.lang.reflect.InvocationTargetException;

public class UserValidator extends AbstractValidator<TestUser> {
    public UserValidator() {
        AddStringRuleFor(TestUser::getName).minLength(4, true, "Halo").name("username length");
    }

}
