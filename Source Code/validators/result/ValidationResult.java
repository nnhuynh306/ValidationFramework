package validators.result;

public class ValidationResult {
    private RESULT result;
    private String name;
    private String message;

    public ValidationResult(RESULT result, String name, String message) {
        this.result = result;
        this.name = name == null?"": name;
        this.message = message;
    }

    public ValidationResult(boolean result, String name, String message) {
        this.result = result? RESULT.OK: RESULT.FAILED;
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
}
