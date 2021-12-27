package Demo;

import validators.AbstractValidator;

import java.lang.reflect.InvocationTargetException;

public class UserValidator extends AbstractValidator<TestUser> {
    public UserValidator() {
//        AddStringRuleFor(TestUser::getName).min(4);
        AddIntegerRuleFor(TestUser::getAge).notNull(true).min(0, true, false);
        AddStringRuleFor(TestUser::getName).minLength(1);
    }

}
