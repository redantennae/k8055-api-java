package exceptions;

import be.thepieterdc.k8055.exceptions.ConnectionException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Pieter De Clercq
 */
public class ConnectionExceptionTest {

    @Test
    public void testConstructorErrorCode() {
        ConnectionException cex = new ConnectionException(9000);
        assertEquals("[K8055/Connection] Connection failed with error code: 9000", cex.getMessage());
    }

    @Test
    public void testConstructorErrorMessage() {
        ConnectionException cex = new ConnectionException("Testmessage");
        assertEquals("[K8055/Connection] Testmessage", cex.getMessage());
    }
}
