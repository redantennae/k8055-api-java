package output;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionException;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.helpers.Voltage;
import be.thepieterdc.k8055.output.AnalogOutput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Pieter De Clercq
 */
public class AnalogOutputTest {

    private K8055 k8055;
    private AnalogOutput output;

    @Before
    public void setUp() {
        this.k8055 = new K8055(0);
        this.output = this.k8055.analogOutputs()[0];
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
    public void testMax() {
        this.output.max();
    }

    @Test(expected=ConnectionStatusException.class)
    public void testMaxNotConnected() {
        this.k8055.disconnect();
        this.output.max();
    }

    @Test
    public void testSet() {
        this.output.set(250);
    }

    @Test(expected=ConnectionStatusException.class)
    public void testSetNotConnected() {
        this.k8055.disconnect();
        this.output.set(250);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidLower() {
        this.output.set(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetInvalidUpper() {
        this.output.set(256);
    }

    @Test
    public void testSetVoltage() {
        this.output.set(new Voltage(2500));
    }

    @Test
    public void testAnalogOutputsFromChannel() {
        AnalogOutput.AnalogOutputs two = AnalogOutput.AnalogOutputs.fromChannel(2);
        assertEquals(AnalogOutput.AnalogOutputs.TWO, two);
    }

    @Test
    public void testChannel() {
        assertEquals(1, this.output.channel());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAnalogOutputsInvalid() {
        AnalogOutput.AnalogOutputs invalid = AnalogOutput.AnalogOutputs.fromChannel(3);
    }
}