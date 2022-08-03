package br.com.coffe.explorer.core.domain.port.output;

public interface ImageRepository<T> {

    String uploadImage(T image);
    String getFileName(T image);
    String getContentType(T image);
    long getFileSize(T image);
}
