import be.thepieterdc.k8055.BoardInterface;
import be.thepieterdc.k8055.Counter;
import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionException;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Pieter De Clercq
 */
public class K8055Test {

    private K8055 k8055;

    @Before
    public void setUp() {
        this.k8055 = new K8055(0);
    }

    @After
    public void tearDown() {
        if(this.k8055.connected()) {
            this.k8055.disconnect();
        }
        this.k8055 = null;
    }

    @Test
    public void testAddress() {
        assertEquals(0, this.k8055.address());
    }

    @Test
    public void testBoard() {
        BoardInterface.Board boardInterface = BoardInterface.instance();
        assertEquals(boardInterface, this.k8055.board());
    }

    @Test(expected=ConnectionStatusException.class)
    public void testConnectConnected() {
        try {
            if (!this.k8055.connected()) {
                this.k8055.connect();
            }
            this.k8055.connect();
        } catch(ConnectionException e) {
            fail();
        }
    }

    @Test(expected=ConnectionException.class)
    public void testConnectInvalidAddress() throws ConnectionException {
        K8055 invalid = new K8055(1);
        invalid.connect();
    }

    @Test
    public void testConnection() throws ConnectionException {
        if(this.k8055.connected()) {
            this.k8055.disconnect();
        }
        assertFalse(this.k8055.connected());
        this.k8055.connect();
        assertTrue(this.k8055.connected());
        this.k8055.disconnect();
        assertFalse(this.k8055.connected());
        this.k8055.connect();
    }

    @Test(expected=ConnectionStatusException.class)
    public void testDisconnectNotConnected() {
        if(this.k8055.connected()) {
            this.k8055.disconnect();
        }
        this.k8055.disconnect();
    }

    @Test
    public void testCounters() {
        Counter[] counters = this.k8055.counters();
        assertEquals(2, counters.length);
        assertEquals(1, counters[0].counter().number());
        assertEquals(2, counters[1].counter().number());
    }

    @Test
    public void testEquals() {
        K8055 newK8055 = new K8055(0);
        assertEquals(newK8055, this.k8055);
    }

    @Test
    public void testHashcode() {
        K8055 newK8055 = new K8055(0);
        assertEquals(newK8055.hashCode(), this.k8055.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("K8055[address="+this.k8055.address()+", connected="+this.k8055.connected()+"]", this.k8055.toString());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidConstructLower() {
        K8055 newK8055 = new K8055(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidConstructUpper() {
        K8055 newK8055 = new K8055(4);
    }
}