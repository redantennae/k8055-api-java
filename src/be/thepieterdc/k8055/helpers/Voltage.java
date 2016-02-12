package be.thepieterdc.k8055.helpers;

/**
 * Voltage helper to convert Volt to analog values.
 */
public class Voltage {
    private final int amount;

    /**
     * Voltage constructor.
     *
     * @param amt the amount of microVolts [0-5000].
     */
    public Voltage(int amt) {
        if(amt < 0 || amt > 5000) {
            throw new IllegalArgumentException("Voltage level must be in range [0-5000 mV].");
        }
        this.amount = amt;
    }

    /**
     * @return the amount of mV.
     */
    public int amount() {
        return this.amount;
    }

    /**
     * @return the amount in V.
     */
    public double amountVolt() {
        return ((double) this.amount)/1000;
    }

    /**
     * @return the analog value of the voltage [0-255].
     */
    public int analog() {
        return (int) ((double)this.amount*255/5000);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Voltage && ((Voltage) other).amount == this.amount;
    }

    /**
     * Constructs a Voltage instance from an analog value.
     *
     * @param analog the analog value
     * @return the Voltage instance
     */
    public static Voltage fromAnalog(int analog) {
        return new Voltage((analog / 255) * 5000);
    }

    @Override
    public int hashCode() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "Voltage[amount="+this.amountVolt()+"V]";
    }
}
