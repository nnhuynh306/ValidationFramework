package Demo;

import validators.annotation.AnnotationValidator;
import validators.annotation.parser.AnnotatedClassParserImpl;
import validators.result.ValidationResults;

public class Main {
    public static void main(String[] args) {
        UserValidator test = new UserValidator();
        TestUser user = new TestUser();
        user.setName("Thai");
        ValidationResults results = new ValidationResults();
        System.out.println(test.validate(user, results));

        CustomUser customUser = new CustomUser();

        customUser.setTestUser(user);

        ClassValidator classValidator = new ClassValidator();

        System.out.println(classValidator.validate(customUser, results));

        System.out.println(new AnnotationValidator<>().validate(customUser, results));
    }

}
