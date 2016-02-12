package be.thepieterdc.k8055.events;

import be.thepieterdc.k8055.Counter;
import be.thepieterdc.k8055.K8055;

import java.util.EventObject;

public class CounterEvent extends EventObject {

    private final Counter counter;
    private final int newValue;
    private final int oldValue;

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
        return "CounterEvent[counter="+this.counter.number()+", oldValue="+this.oldValue+", newValue="+this.newValue+"]";
    }
}
