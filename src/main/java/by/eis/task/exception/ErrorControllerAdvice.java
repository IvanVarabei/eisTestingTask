package by.eis.task.exception;

import by.eis.task.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorControllerAdvice {
    /**
     * Run if url is not supported (404)
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handle(NoHandlerFoundException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorMessage(ex.getMessage());
        exceptionDto.setErrorCode(40401);
        exceptionDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(NOT_FOUND).body(exceptionDto);
    }

    /**
     * Handles errors from @Validated
     */
    @ExceptionHandler
    public ResponseEntity<List<ExceptionDto>> handle(ConstraintViolationException ex) {
        List<ExceptionDto> exceptionDtoList = new ArrayList<>();
        ex.getConstraintViolations()
                .forEach(e -> {
                    String className = e.getRootBeanClass().getSimpleName();
                    String property = e.getPropertyPath().toString();
                    String message = e.getMessage();
                    String invalidValue = e.getInvalidValue().toString();
                    String fullMessage = String.format("%s.%s value '%s' %s", className, property, invalidValue, message);
                    ExceptionDto exceptionDto = new ExceptionDto();
                    exceptionDto.setErrorMessage(fullMessage);
                    exceptionDto.setErrorCode(40001);
                    exceptionDto.setTimestamp(LocalDateTime.now());
                    exceptionDtoList.add(exceptionDto);
                });
        return ResponseEntity.badRequest().body(exceptionDtoList);
    }

    /**
     * Run if a path variable can't be parsed to an integer
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handle(MethodArgumentTypeMismatchException ex) {
        String parameterName = ex.getName();
        String message = String.format("Parameter '%s' is wrong. %s", parameterName, ex.getMessage());
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorMessage(message);
        exceptionDto.setErrorCode(40001);
        exceptionDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    /**
     * Handles errors from @Valid
     */
    @ExceptionHandler
    public ResponseEntity<List<ExceptionDto>> handle(MethodArgumentNotValidException ex) {
        List<ExceptionDto> exceptionDtoList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            String fieldName = ((FieldError) error).getField();
            ExceptionDto exceptionDto = new ExceptionDto();
            exceptionDto.setErrorMessage(String.format("%s - %s", fieldName, errorMessage));
            exceptionDto.setErrorCode(4001);
            exceptionDto.setTimestamp(LocalDateTime.now());
            exceptionDtoList.add(exceptionDto);
        });
        return ResponseEntity.badRequest().body(exceptionDtoList);
    }

    /**
     * Handles custom exception ResourceNotFoundException
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handle(ResourceNotFoundException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorMessage(ex.getMessage());
        exceptionDto.setErrorCode(40401);
        exceptionDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(NOT_FOUND).body(exceptionDto);
    }

    /**
     * Run if enum passed as a field of a dto not matching actual enum name
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handle(HttpMessageNotReadableException ex) {
        EnumValidationException exception = (EnumValidationException) ex.getMostSpecificCause();
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorMessage(exception.getMessage());
        exceptionDto.setErrorCode(40001);
        exceptionDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    /**
     * Handles custom exception ForbiddenActionException
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handle(ForbiddenActionException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorMessage(ex.getMessage());
        exceptionDto.setErrorCode(40001);
        exceptionDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    /**
     * Handles duplicate key error
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handle(DataIntegrityViolationException ex) {
        PSQLException cause = (PSQLException) ex.getCause().getCause();
        String errorMessage = cause.getMessage();
        errorMessage = errorMessage.replaceAll("\\s\"\\w+\"\n\\s", ".");
        errorMessage = errorMessage.replaceAll("Key \\(\\w+\\)", "value");
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorMessage(errorMessage);
        exceptionDto.setErrorCode(40001);
        exceptionDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    /**
     * Run if http request method not supported
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handle(HttpRequestMethodNotSupportedException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorMessage(ex.getMessage());
        exceptionDto.setErrorCode(40501);
        exceptionDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(METHOD_NOT_ALLOWED).body(exceptionDto);
    }

    /**
     * Catches all unexpected exceptions
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handle(Throwable ex) {
        log.error(ex.getMessage(), ex);
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setErrorMessage(ex.getMessage());
        exceptionDto.setErrorCode(50001);
        exceptionDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exceptionDto);
    }
}
