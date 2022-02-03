package input;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionException;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.input.AnalogInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jna.Platform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Pieter De Clercq
 */
public class AnalogInputTest {

    private K8055 k8055;
    private AnalogInput input;

    @Before
    public void setUp() {
        this.k8055 = new K8055(0);
        this.input = this.k8055.analogInputs()[0];
        try {
            this.k8055.connect();
        } catch(ConnectionException e) {
            fail();
        }
    }

    @After
    public void tearDown() throws Exception {
        if(this.k8055.connected()) {
            this.k8055.disconnect();
        }
        this.k8055 = null;
    }

    @Test
    public void testValue() {
        assertEquals(0, this.input.value().intValue());
    }

    @Test(expected=ConnectionStatusException.class)
    public void testValueNotConnected() {
        this.k8055.disconnect();
        this.input.value();
    }

    @Test
    public void testChannel() {
        assertEquals(1, this.input.channel());
    }

    @Test
    public void testAnalogInputsFromChannel() {
        AnalogInput.AnalogInputs two = AnalogInput.AnalogInputs.fromChannel(2);
        assertEquals(AnalogInput.AnalogInputs.TWO, two);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAnalogInputsInvalid() {
        AnalogInput.AnalogInputs.fromChannel(3);
    }
}
