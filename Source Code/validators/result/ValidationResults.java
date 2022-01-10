package validators.result;

import java.util.ArrayList;

public class ValidationResults {

    ArrayList<ValidationResult> validationResults = new ArrayList<>();

    public ValidationResults() {
    }

    public void add(ValidationResult result) {
        if (result != null) {
            validationResults.add(result);
        }
    }

    public boolean remove(ValidationResult result) {
        return validationResults.remove(result);
    }

    public boolean remove(String name, boolean ignoreCase) {
        for (int i = 0; i < validationResults.size(); i++) {
            ValidationResult result = validationResults.get(i);
            if (ignoreCase) {
                if (result.getName().equalsIgnoreCase(name)) {
                    return validationResults.remove(result);
                }
            } else {
                if (result.getName().equals(name)) {
                    return validationResults.remove(result);
                }
            }
        }
        return false;
    }

    public void removeAll(String name, boolean ignoreCase) {
        for (int i = 0; i < validationResults.size(); i++) {
            ValidationResult result = validationResults.get(i);
            if (ignoreCase) {
                if (result.getName().equalsIgnoreCase(name)) {
                    validationResults.remove(result);
                }
            } else {
                if (result.getName().equals(name)) {
                    validationResults.remove(result);
                }
            }
        }
    }

    public ArrayList<ValidationResult> getFailedResults() {
        ArrayList<ValidationResult> failedResults = new ArrayList<>();

        for (ValidationResult result : validationResults) {
            if (result.hasFailed()) {
                failedResults.add(result);
            }
        }

        return failedResults;
    }

    public ValidationResult get(int index) throws IndexOutOfBoundsException {
        return validationResults.get(index);
    }

    public ValidationResult get(String name, boolean ignoreCase) {
        for (ValidationResult result : validationResults) {
            if (ignoreCase ? result.getName().equalsIgnoreCase(name) : result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    public void clear(){
        validationResults.clear();
    }
}
