package io.github.slemke.methodextractor.exceptions;

/**
 * Signals that Exception occurred while handling GUI specific actions.
 *
 * @author Sascha Lemke
 * @version 0.1
 */
public class UserInterfaceException extends Exception {

    public UserInterfaceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
