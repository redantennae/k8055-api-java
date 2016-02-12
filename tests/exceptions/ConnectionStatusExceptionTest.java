package exceptions;

import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Pieter De Clercq
 */
public class ConnectionStatusExceptionTest {

    @Test
    public void testConstructor() {
        ConnectionStatusException csx = new ConnectionStatusException("Testmessage");
        assertEquals("[K8055/ConnectionStatus] Testmessage", csx.getMessage());
    }

    @Test
    public void testForbidden() {
        ConnectionStatusException csx = ConnectionStatusException.connectionForbidden();
        assertEquals("[K8055/ConnectionStatus] You may not be connected to a K8055 to perform this action.", csx.getMessage());
    }

    @Test
    public void testRequired() {
        ConnectionStatusException csx = ConnectionStatusException.connectionRequired();
        assertEquals("[K8055/ConnectionStatus] You must be connected to a K8055 to perform this action.", csx.getMessage());
    }
}
