package output;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionException;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.helpers.Voltage;
import be.thepieterdc.k8055.output.AnalogOutput;
import be.thepieterdc.k8055.output.DigitalOutput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Pieter De Clercq
 */
public class DigitalOutputTest {

    private K8055 k8055;
    private DigitalOutput output;

    @Before
    public void setUp() {
        this.k8055 = new K8055(0);
        this.output = this.k8055.digitalOutputs()[0];
        try {
            this.k8055.connect();
        } catch(ConnectionException e) {
            fail();
        }
    }

    @After
    public void tearDown() {
        if(this.k8055.connected()) {
            this.k8055.disconnect();
        }
        this.k8055 = null;
    }

    @Test
    public void testClear() {
        this.output.clear();
    }

    @Test(expected=ConnectionStatusException.class)
    public void testClearNotConnected() {
        this.k8055.disconnect();
        this.output.clear();
    }

    @Test
    public void testClearAll() {
        for(DigitalOutput dout : this.k8055.digitalOutputs()) {
            dout.clear();
        }
    }

    @Test
    public void testOn() {
        this.output.on();
    }

    @Test(expected=ConnectionStatusException.class)
    public void testOnNotConnected() {
        this.k8055.disconnect();
        this.output.on();
    }

    @Test
    public void testSetOnAll() {
        for(DigitalOutput dout : this.k8055.digitalOutputs()) {
            dout.on();
        }
    }

    @Test
    public void testDigitalOutputsFromChannel() {
        DigitalOutput.DigitalOutputs seven = DigitalOutput.DigitalOutputs.fromChannel(7);
        assertEquals(DigitalOutput.DigitalOutputs.SEVEN, seven);
    }

    @Test
    public void testChannel() {
        assertEquals(1, this.output.channel());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAnalogOutputsInvalid() {
        DigitalOutput.DigitalOutputs invalid = DigitalOutput.DigitalOutputs.fromChannel(9);
    }
}