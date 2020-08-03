package driving.school.exceptions;

public class DuplicateUniqueKey extends RuntimeException {
    public DuplicateUniqueKey() {
    }

    public DuplicateUniqueKey(String message) {
        super(message);
    }
}
