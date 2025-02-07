package mindera.porto.moveWell.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Hidden
public class globalExceptionHandler {
    @ExceptionHandler (DataIntegrityViolationException.class)
    public ResponseEntity<String>handleDataIntegrityViolatonException (DataIntegrityViolationException e){
        if (e.getMessage().contains("video not found")){
            return new ResponseEntity<>("Video not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Data integrity violation", HttpStatus.BAD_REQUEST)
    }
}
@ExceptionHandler(UserException.class)
public ResponseEntity<String> handleIllegalStateException(IllegalStateException e){
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
}