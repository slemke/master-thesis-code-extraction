package io.github.slemke.methodextractor.exceptions;

/**
 * Signals that an Exception occured while reading or writing files in the application.
 *
 * @author Sascha Lemke
 * @version 0.1
 */
public class FileException extends Exception {

    public FileException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
