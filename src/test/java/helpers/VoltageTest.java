package helpers;

import be.thepieterdc.k8055.helpers.Voltage;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author Pieter De Clercq
 */
public class VoltageTest {

    private Voltage voltage;

    @Before
    public void setUp() {
        this.voltage = new Voltage(2500);
    }

    @After
    public void tearDown() {
        this.voltage = null;
    }

    @Test
    public void testAmount() {
        assertEquals(2500, this.voltage.amount());
    }

    @Test
    public void testAmountVolt() {
        assertEquals(2.5, this.voltage.amountVolt(), 0.1);
    }

    @Test
    public void testAnalog() {
        assertEquals(127, this.voltage.analog());
    }

    @Test
    public void testEquals() {
        Voltage correctVoltage = new Voltage(2500);
        Voltage falseVoltage = new Voltage(2501);
        assertTrue(correctVoltage.equals(this.voltage));
        assertFalse(falseVoltage.equals(this.voltage));
    }

    @Test
    public void testFromAnalog() {
        Voltage newVoltage = Voltage.fromAnalog(255);
        assertEquals(5000, newVoltage.amount());
    }

    @Test
    public void testHashcode() {
        Voltage correctVoltage = new Voltage(2500);
        Voltage falseVoltage = new Voltage(2501);
        assertEquals(correctVoltage.hashCode(), this.voltage.hashCode());
        assertNotEquals(falseVoltage.hashCode(), this.voltage.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("Voltage[amount="+this.voltage.amountVolt()+"V]", this.voltage.toString());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidConstructLower() {
        Voltage illegal = new Voltage(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidConstructUpper() {
        Voltage illegal = new Voltage(5001);
    }
}
