package mindera.porto.moveWell.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Component
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<String> handleSpecificException(ResponseStatusException e) {
        if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden: " + e.getMessage());
        } else if (e.getStatusCode() == HttpStatus.CONFLICT) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: " + e.getMessage());
        } else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + e.getMessage());
        } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request: " + e.getMessage());
        } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: " + e.getMessage());
        }
        return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
    }

//    @ExceptionHandler(value = {ResponseStatusException.class})
//    public ResponseEntity<String> handleConflictException(Exception e) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }




}
