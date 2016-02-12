package be.thepieterdc.k8055;

import be.thepieterdc.k8055.exceptions.ConnectionException;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.input.AnalogInput;

public class K8055 {

    private final int address;
    private BoardInterface.Board board;
    private boolean connected = false;

    private final AnalogInput[] analogInputs;
    private final Counter[] counters;

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
        this.counters = new Counter[] {
                new Counter(this, Counter.Counters.ONE),
                new Counter(this, Counter.Counters.TWO)
        };
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

    @Override
    public String toString() {
        return "K8055[address="+this.address+", connected="+this.connected+"]";
    }
}
