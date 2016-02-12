package input;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionException;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.helpers.IO;
import be.thepieterdc.k8055.input.AnalogInput;
import be.thepieterdc.k8055.input.DigitalInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Pieter De Clercq
 */
public class DigitalInputTest {

    private K8055 k8055;
    private DigitalInput input;

    @Before
    public void setUp() {
        this.k8055 = new K8055(0);
        this.input = this.k8055.digitalInputs()[0];
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
        assertFalse(this.input.value());
    }

    @Test(expected=ConnectionStatusException.class)
    public void testValueNotConnected() {
        this.k8055.disconnect();
        boolean invalid = this.input.value();
    }

    @Test
    public void testChannel() {
        assertEquals(1, this.input.channel());
    }

    @Test
    public void testDigitalInputsFromChannel() {
        DigitalInput.DigitalInputs two = DigitalInput.DigitalInputs.fromChannel(2);
        assertEquals(DigitalInput.DigitalInputs.TWO, two);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDigitalInputsInvalid() {
        DigitalInput.DigitalInputs invalid = DigitalInput.DigitalInputs.fromChannel(6);
    }

    /* helpers.IO tests */

    @Test
    public void testIOType() {
        assertEquals(IO.Type.INPUT, this.input.type());
    }

    @Test
    public void testIOSignal() {
        assertEquals(IO.Signal.DIGITAL, this.input.signal());
    }

    @Test
    public void testIOToString() {
        assertEquals("IO[signal=Digital, type=Input, channel=1]", this.input.toString());
    }

    @Test
    public void testIOInterface() {
        DigitalInput.DigitalInputs digitalInput = DigitalInput.DigitalInputs.ONE;
        assertEquals(digitalInput, this.input.ioInterface());
    }

    @Test
    public void testIOEquals() {
        DigitalInput one = new DigitalInput(this.k8055, DigitalInput.DigitalInputs.ONE);
        assertTrue(one.equals(this.input));
    }

    @Test
    public void testHashcode() {
        DigitalInput one = new DigitalInput(this.k8055, DigitalInput.DigitalInputs.ONE);
        assertEquals(one.hashCode(), this.input.hashCode());
    }

    @Test
    public void testTypeScalar() {
        assertEquals("Input", this.input.type().scalar());
    }

    @Test
    public void testSignalScalar() {
        assertEquals("Digital", this.input.signal().scalar());
    }

    @Test
    public void testSignalFromScalar() {
        IO.Signal digi = IO.Signal.fromScalar("Digital");
        assertEquals(IO.Signal.DIGITAL, digi);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSignalFromScalarInvalid() {
        IO.Signal invalid = IO.Signal.fromScalar("Test");
    }

    @Test
    public void testTypeFromScalar() {
        IO.Type in = IO.Type.fromScalar("Input");
        assertEquals(IO.Type.INPUT, in);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testTypeFromScalarInvalid() {
        IO.Type invalid = IO.Type.fromScalar("Test");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructK8055Null() {
        IO invalid = new DigitalInput(null, DigitalInput.DigitalInputs.ONE);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructIOInterfaceNull() {
        IO invalid = new DigitalInput(this.k8055, null);
    }
}
