package Demo;

import validators.AbstractValidator;

public class UserValidator extends AbstractValidator<TestUser> {
    public UserValidator() {
//        AddStringRuleFor(TestUser::getName).min(4);
//        AddIntegerRuleFor(TestUser::getAge).notNull(true).min(6, true, false);
        AddStringRuleFor(TestUser::getName).equal("Thai");
    }

}
