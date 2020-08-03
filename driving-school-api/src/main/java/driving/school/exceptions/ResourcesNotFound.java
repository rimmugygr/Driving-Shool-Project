package driving.school.exceptions;

public class ResourcesNotFound extends RuntimeException {
    public ResourcesNotFound() {
    }

    public ResourcesNotFound(String message) {
        super(message);
    }
}
