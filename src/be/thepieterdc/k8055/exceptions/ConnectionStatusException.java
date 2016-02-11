package be.thepieterdc.k8055.exceptions;

/**
 * ConnectionStatusException
 *
 * @author Pieter De Clercq
 */
public class ConnectionStatusException extends Exception {

    /**
     * ConnectionStatusException constructor.
     *
     * @param message the message to throw
     */
    public ConnectionStatusException(String message) {
        super("[K8055/ConnectionStatus] "+message);
    }

    /**
     * Exception to be thrown when a connection is required.
     *
     * @return the exception
     */
    public static ConnectionStatusException connectionRequired() {
        return new ConnectionStatusException("You must be connected to a K8055 to perform this action.");
    }

    /**
     * Exception to be thrown when a connection is forbidden.
     *
     * @return the exception
     */
    public static ConnectionStatusException connectionForbidden() {
        return new ConnectionStatusException("You may not be connected to a K8055 to perform this action.");
    }
}
