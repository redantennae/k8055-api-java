package be.thepieterdc.k8055.exceptions;

public class ConnectionStatusException extends RuntimeException {

    public ConnectionStatusException(String message) {
        super("[K8055/ConnectionStatus] "+message);
    }

    public static ConnectionStatusException connectionForbidden() {
        return new ConnectionStatusException("You may not be connected to a K8055 to perform this action.");
    }

    public static ConnectionStatusException connectionRequired() {
        return new ConnectionStatusException("You must be connected to a K8055 to perform this action.");
    }
}
