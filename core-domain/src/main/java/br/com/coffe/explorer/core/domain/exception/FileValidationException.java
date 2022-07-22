package br.com.coffe.explorer.core.domain.exception;

public class FileValidationException extends RuntimeException {

    public FileValidationException(String message) {
        super(message);
    }
}
