package be.thepieterdc.k8055.exceptions;

/**
 * Created by pieter on 10/02/16.
 */
public class ConnectionException extends Exception {

    /**
     * ConnectionException constructor.
     *
     * @param error String the error message
     */
    public ConnectionException(String error) {
        super("[K8055/Connection] "+error);
    }

    /**
     * ConnectionException constructor.
     *
     * @param result int the error code.
     */
    public ConnectionException(int result) {
        super("[K8055/Connection] Connection failed with error code: "+result);
    }
}
