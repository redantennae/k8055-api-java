package be.thepieterdc.k8055.exceptions;

/**
 * ConnectionException
 *
 * @author Pieter De Clercq
 */
public class ConnectionException extends Exception {

    /**
     * ConnectionException constructor.
     *
     * @param error the error message
     */
    public ConnectionException(String error) {
        super("[K8055/Connection] "+error);
    }

    /**
     * ConnectionException constructor.
     *
     * @param result the error code.
     */
    public ConnectionException(int result) {
        super("[K8055/Connection] Connection failed with error code: "+result);
    }
}
