package be.thepieterdc.k8055.exceptions;

public class ConnectionException extends Exception {

    public ConnectionException(int result) {
        super("[K8055/Connection] Connection failed with error code: "+result);
    }

    public ConnectionException(String error) {
        super("[K8055/Connection] "+error);
    }

}
