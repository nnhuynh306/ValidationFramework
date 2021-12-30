package validators.result;

public class ValidationResult {
    private RESULT result;
    private String name;
    private String message;

    private ValidationResult(RESULT result, String name, String message) {
        this.result = result;
        this.name = name == null?"": name;
        this.message = message;
    }

    public enum RESULT {
        OK,
        FAILED,
    }

    public boolean hasFailed() {
        return result == RESULT.FAILED;
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

    public static ValidationResult create(boolean result, String name, String message) {
        return new ValidationResult(result? RESULT.OK: RESULT.FAILED, name, message);
    }

    public static ValidationResult create(RESULT result, String name, String message) {
        return new ValidationResult(result, name, message);
    }
}
