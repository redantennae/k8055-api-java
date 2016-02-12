package be.thepieterdc.k8055.events;

import be.thepieterdc.k8055.Counter;

import java.util.EventObject;

public class CounterEvent extends EventObject {

    private final Counter counter;
    private final int newValue;
    private final int oldValue;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CounterEvent(Object source, Counter counter, int oldValue, int newValue) {
        super(source);
        if(counter == null) {
            throw new IllegalArgumentException("Counter is null.");
        } else if(oldValue < 0) {
            throw new IllegalArgumentException("Oldvalue cannot be negative.");
        } else if(newValue < 0) {
            throw new IllegalArgumentException("Newvalue cannot be negative.");
        }
        this.counter = counter;
        this.newValue = newValue;
        this.oldValue = oldValue;
    }

    public Counter counter() {
        return this.counter;
    }

    public int newValue() {
        return this.newValue;
    }

    public int oldValue() {
        return this.oldValue;
    }

    @Override
    public String toString() {
        return "CounterEvent[counter="+this.counter.counter().number()+", oldValue="+this.oldValue+", newValue="+this.newValue+"]";
    }
}
