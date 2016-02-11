package be.thepieterdc.k8055;

import be.thepieterdc.k8055.exceptions.ConnectionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pieter on 10/02/16.
 */
public class K8055Test {

    private static K8055 k8055;

    @Before
    public void setUp() throws Exception {
        k8055 = new K8055(0);
    }

    @After
    public void tearDown() throws Exception {
        if(k8055.connected()) {
            k8055.disconnect();
        }
    }

    @Test
    public void testAddress() {
        assertEquals(0, k8055.address());
    }

    @Test
    public void testConnect() {
        try {
            k8055.connect();
        } catch (ConnectionException e) {
            fail();
        }
    }

    @Test
    public void testConnected() {
        try {
            k8055.connect();
        } catch(Exception e) {
            fail();
        }
        try {
            assertTrue(k8055.connected());
            k8055.disconnect();
            assertFalse(k8055.connected());
            k8055.connect();
            assertTrue(k8055.connected());
        } catch(ConnectionException e) {
            fail();
        }
    }

    @Test(expected=IllegalStateException.class)
    public void testConnectWhileConnected() {
        try {
            if(!k8055.connected()) {
                k8055.connect();
            }
            k8055.connect();
        } catch(ConnectionException e) {
            fail();
        }
    }

    @Test(expected=ConnectionException.class)
    public void testConnectionInvalid() throws ConnectionException {
        K8055 invalid = new K8055(3);
        invalid.connect();
    }

    @Test(expected=IllegalStateException.class)
    public void testDisconnectWhileNotConnected() {
        K8055 invalid = new K8055(3);
        invalid.disconnect();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidConstructorAddressLower() {
        K8055 shouldFail = new K8055(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidConstructorAddressUpper() {
        K8055 shouldFail = new K8055(4);
    }
}