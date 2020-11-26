package development.bulletinboard.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final String NO_SUCH_ELEMENT = "Для этого индекса не найдено объявление!";

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(Exception e) {
        return NO_SUCH_ELEMENT + " " + e.getClass().getSimpleName();
    }
}
