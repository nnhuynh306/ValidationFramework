package validators.result;

public class ValidationResult {
    private RESULT result;
    private String name;
    private String message;

    public ValidationResult(RESULT result, String name, String message) {
        this.result = result;
        this.name = name;
        this.message = message;
    }

    public enum RESULT {
        OK,
        FAILED,
    }

    public RESULT getResult() {
        return result;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
