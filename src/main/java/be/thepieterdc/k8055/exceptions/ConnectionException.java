package be.thepieterdc.k8055.exceptions;

/**
 * Exception to be thrown when a connection error occurs.
 *
 * @author Pieter De Clercq
 */
public class ConnectionException extends Exception {

    /**
     * ConnectionException constructor.
     *
     * @param errorCode the error code from the Interface
     */
    public ConnectionException(int errorCode) {
        super("[K8055/Connection] Connection failed with error code: "+errorCode);
    }

    /**
     * ConnectionException constructor.
     *
     * @param error the error message
     */
    public ConnectionException(String error) {
        super("[K8055/Connection] "+error);
    }

}
