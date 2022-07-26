package br.com.coffe.explorer.flavor.service.error;

import br.com.coffe.explorer.core.domain.exception.FlavorNotFoundException;
import br.com.coffe.explorer.core.domain.model.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@ControllerAdvice
public class ExceptionErrorHandling {

    @ExceptionHandler(FlavorNotFoundException.class)
    public ResponseEntity<ErrorModel> flavorNotFoundException(FlavorNotFoundException ex, WebRequest request) {
        ErrorModel errorModel = new ErrorModel("Flavor not found", getTime());
        log.error(errorModel.description(), ex);
        return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> exception(Exception ex, WebRequest request) {
        ErrorModel errorModel = new ErrorModel("Ocorreu um erro", getTime());
        log.error(errorModel.description(), ex);
        return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getTime() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

}
