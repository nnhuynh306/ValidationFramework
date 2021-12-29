package Demo;

import validators.AbstractValidator;

import java.lang.reflect.InvocationTargetException;

public class ClassValidator extends AbstractValidator<CustomUser> {
    public ClassValidator() {
        try {
            AddCustomRuleFor(CustomUser::getTestUser).notNull().validatedBy(UserValidator.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
