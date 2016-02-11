package be.thepieterdc.k8055.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pieter on 10/02/16.
 */
public class ConnectionExceptionTest {

    @Test
    public void testConstructorString() {
        ConnectionException ce = new ConnectionException("test");
        assertEquals("[K8055/Connection] test", ce.getMessage());
    }

    @Test
    public void testConstructorErrorCode() {
        ConnectionException ce = new ConnectionException(5000);
        assertEquals("[K8055/Connection] Connection failed with error code: 5000", ce.getMessage());
        ConnectionException ceNegative = new ConnectionException(-5000);
        assertEquals("[K8055/Connection] Connection failed with error code: -5000", ceNegative.getMessage());
    }
}