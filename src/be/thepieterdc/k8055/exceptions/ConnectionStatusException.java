package be.thepieterdc.k8055.exceptions;

/**
 * Exception to be thrown when the connection state is invalid.
 *
 * @author Pieter De Clercq
 */
public class ConnectionStatusException extends RuntimeException {

    /**
     * ConnectionStatusException constructor.
     *
     * @param message the error message
     */
    public ConnectionStatusException(String message) {
        super("[K8055/ConnectionStatus] "+message);
    }

    /**
     *
     * @return the ConnectionStatusException that should be thrown when there is an active connection, while this should not be the case
     */
    public static ConnectionStatusException connectionForbidden() {
        return new ConnectionStatusException("You may not be connected to a K8055 to perform this action.");
    }

    /**
     * @return the ConnectionStatusException that should be thrown when there is no active connection, while this is required
     */
    public static ConnectionStatusException connectionRequired() {
        return new ConnectionStatusException("You must be connected to a K8055 to perform this action.");
    }
}
