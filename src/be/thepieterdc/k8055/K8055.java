package be.thepieterdc.k8055;

import be.thepieterdc.k8055.exceptions.ConnectionException;

/**
 * K8055
 *
 * @author Pieter De Clercq
 */
public class K8055 {

    /**
     * The address of the K8055.
     */
    private final int address;

    /**
     * The raw board.
     */
    private BoardInterface.Board board;

    /**
     * True if connected.
     */
    private boolean connected = false;

    /**
     * K8055 constructor.
     *
     * @param addr the address
     */
    public K8055(int addr) {
        if(addr < 0 || addr > 3) {
            throw new IllegalArgumentException("Address must be in range [0-3]");
        }
        this.address = addr;
        this.board = BoardInterface.instance();
    }

    /**
     * Gets the address [0-3] of the K8055.
     *
     * @return the address of the K8055
     */
    public int address() {
        return this.address;
    }

    /**
     * Connects to the K8055.
     *
     * @throws ConnectionException connecting failed
     */
    public void connect() throws ConnectionException {
        if(this.connected) {
            throw new IllegalStateException("Already connected.");
        }
        int result = this.board.OpenDevice(this.address);
        if(result < 0) {
            throw new ConnectionException(result);
        }
        this.connected = true;
    }

    /**
     * Gets the connection state.
     *
     * @return true if connected to the K8055
     */
    public boolean connected() {
        return this.connected;
    }

    /**
     * Disconnects from the K8055.
     */
    public void disconnect() {
        if(!this.connected) {
            throw new IllegalStateException("Not connected.");
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
}
