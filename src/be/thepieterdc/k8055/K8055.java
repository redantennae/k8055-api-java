package be.thepieterdc.k8055;

import be.thepieterdc.k8055.exceptions.ConnectionException;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.input.AnalogInput;
import be.thepieterdc.k8055.input.DigitalInput;
import be.thepieterdc.k8055.listeners.CounterListener;
import be.thepieterdc.k8055.output.AnalogOutput;
import be.thepieterdc.k8055.output.DigitalOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class K8055 {

    private final int address;
    private BoardInterface.Board board;
    private boolean connected = false;

    private final AnalogInput[] analogInputs;
    private final AnalogOutput[] analogOutputs;
    private final Counter[] counters;
    private final DigitalInput[] digitalInputs;
    private final DigitalOutput[] digitalOutputs;

    private final List<CounterListener> counterListeners = new ArrayList<>();

    public K8055(int addr) {
        if(addr < 0 || addr > 3) {
            throw new IllegalArgumentException("Address must be in range [0-3].");
        }
        this.address = addr;
        this.board = BoardInterface.instance();
        this.analogInputs = new AnalogInput[]{
                new AnalogInput(this, AnalogInput.AnalogInputs.ONE),
                new AnalogInput(this, AnalogInput.AnalogInputs.TWO)
        };
        this.analogOutputs = new AnalogOutput[]{
                new AnalogOutput(this, AnalogOutput.AnalogOutputs.ONE),
                new AnalogOutput(this, AnalogOutput.AnalogOutputs.TWO)
        };
        this.counters = new Counter[] {
                new Counter(this, Counter.Counters.ONE),
                new Counter(this, Counter.Counters.TWO)
        };
        this.digitalInputs = new DigitalInput[]{
                new DigitalInput(this, DigitalInput.DigitalInputs.ONE),
                new DigitalInput(this, DigitalInput.DigitalInputs.TWO),
                new DigitalInput(this, DigitalInput.DigitalInputs.THREE),
                new DigitalInput(this, DigitalInput.DigitalInputs.FOUR),
                new DigitalInput(this, DigitalInput.DigitalInputs.FIVE)
        };
        this.digitalOutputs = new DigitalOutput[]{
                new DigitalOutput(this, DigitalOutput.DigitalOutputs.ONE),
                new DigitalOutput(this, DigitalOutput.DigitalOutputs.TWO),
                new DigitalOutput(this, DigitalOutput.DigitalOutputs.THREE),
                new DigitalOutput(this, DigitalOutput.DigitalOutputs.FOUR),
                new DigitalOutput(this, DigitalOutput.DigitalOutputs.FIVE),
                new DigitalOutput(this, DigitalOutput.DigitalOutputs.SIX),
                new DigitalOutput(this, DigitalOutput.DigitalOutputs.SEVEN),
                new DigitalOutput(this, DigitalOutput.DigitalOutputs.EIGHT)
        };
    }

    public void addListener(CounterListener c) {
        if(c == null) {
            throw new IllegalArgumentException("CounterListener is null.");
        }
        this.counterListeners.add(c);
    }

    public int address() {
        return this.address;
    }

    public BoardInterface.Board board() {
        return this.board;
    }

    public void connect() throws ConnectionException {
        if(this.connected) {
            throw ConnectionStatusException.connectionForbidden();
        }
        int result = this.board.OpenDevice(this.address);
        if(result < 0) {
            throw new ConnectionException(result);
        }
        this.connected = true;
    }

    public boolean connected() {
        return this.connected;
    }

    public Counter[] counters() {
        return this.counters.clone();
    }

    public final List<CounterListener> counterListeners() {
        return Collections.unmodifiableList(this.counterListeners);
    }

    public void disconnect() {
        if(!this.connected) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.board.CloseDevice();
        this.connected = false;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof K8055 && ((K8055) other).address == this.address;
    }

    @Override
    public int hashCode() {
        return this.address;
    }

    public void removeListener(CounterListener c) {
        if(c == null) {
            throw new IllegalArgumentException("CounterListener is null.");
        }
        this.counterListeners.remove(c);
    }

    @Override
    public String toString() {
        return "K8055[address="+this.address+", connected="+this.connected+"]";
    }
}
