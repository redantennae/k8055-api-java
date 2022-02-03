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
public class CounterTest {

    private Counter counter;
    private K8055 k8055;

    @Before
    public void setUp() {
        this.k8055 = new K8055(0);
        this.counter = this.k8055.counters()[0];
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
    public void testCounter() {
        Counter.Counters counterOne = Counter.Counters.ONE;
        assertEquals(counterOne, this.counter.counter());
    }

    @Test
    public void testEquals() {
        Counter counterOne = new Counter(this.k8055, Counter.Counters.ONE);
        Counter counterTwo = new Counter(this.k8055, Counter.Counters.TWO);
        assertTrue(counterOne.equals(this.counter));
        assertFalse(counterTwo.equals(this.counter));
    }

    @Test
    public void testHashCode() {
        Counter counterOne = new Counter(this.k8055, Counter.Counters.ONE);
        assertEquals(counterOne.hashCode(), this.counter.hashCode());
    }

    @Test
    public void testReset() {
        this.counter.reset();
        assertEquals(0, this.counter.value());
    }

    @Test(expected=ConnectionStatusException.class)
    public void testResetNotConnected() {
        if(this.k8055.connected()) {
            this.k8055.disconnect();
        }
        this.counter.reset();
    }

    @Test
    public void testSetDebounce() {
        this.counter.setDebounce(2000);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidDebounceLower() {
        this.counter.setDebounce(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidDebounceUpper() {
        this.counter.setDebounce(5001);
    }

    @Test(expected=ConnectionStatusException.class)
    public void testDebounceNotConnected() {
        if(this.k8055.connected()) {
            this.k8055.disconnect();
        }
        this.counter.setDebounce(4000);
    }

    @Test
    public void testToString() {
        assertEquals("Counter[number="+this.counter.counter().number()+"]", this.counter.toString());
    }

    @Test(expected=ConnectionStatusException.class)
    public void testValueNotConnected() {
        if(this.k8055.connected()) {
            this.k8055.disconnect();
        }
        int i = this.counter.value();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorK8055Null() {
        Counter invalid = new Counter(null, Counter.Counters.ONE);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorCounterNull() {
        Counter invalid = new Counter(this.k8055, null);
    }

    @Test
    public void testCountersFromNumber() {
        Counter.Counters fromNumber = Counter.Counters.fromNumber(2);
        assertEquals(Counter.Counters.TWO, fromNumber);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCountersFromNumberInvalid() {
        Counter.Counters invalid = Counter.Counters.fromNumber(3);
    }
}