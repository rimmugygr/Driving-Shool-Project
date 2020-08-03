package driving.school.controller;

import driving.school.exceptions.DuplicateUniqueKey;
import driving.school.exceptions.ResourcesNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {ResourcesNotFound.class})
    public ResponseEntity<Object> handleResourceNotfound(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(),null,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DuplicateUniqueKey.class})
    public ResponseEntity<Object> handleDuplicateUniqueKey(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(),null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
