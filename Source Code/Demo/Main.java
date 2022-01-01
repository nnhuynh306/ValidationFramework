package Demo;

import validators.annotation.AnnotationValidator;
import validators.result.ValidationResult;
import validators.result.ValidationResults;

public class Main {
    public static void main(String[] args) {
        UserValidator test = new UserValidator();
        TestUser user = new TestUser();
        user.setName("#00");
        ValidationResults results = new ValidationResults();
        System.out.println(test.validate(user, results));

        ValidationResult validationResult = results.get("int test", true);

        System.out.println(validationResult.getMessage());
    }

}
