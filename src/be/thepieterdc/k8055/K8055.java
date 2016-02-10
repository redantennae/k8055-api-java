package be.thepieterdc.k8055;

/**
 * Created by pieter on 10/02/16.
 */
public class K8055 {

    /**
     * The address of the K8055.
     */
    private final int address;

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
    }

    /**
     * @return the address of the K8055
     */
    public int address() {
        return this.address;
    }
}
