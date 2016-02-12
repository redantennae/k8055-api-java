package be.thepieterdc.k8055.helpers;

public class Voltage {

    private final int amount;

    public Voltage(int amt) {
        if(amt < 0 || amt > 5000) {
            throw new IllegalArgumentException("Voltage level must be in range [0-5000 mV].");
        }
        this.amount = amt;
    }

    public int amount() {
        return this.amount;
    }

    public double amountVolt() {
        return this.amount / 1000;
    }

    public int analog() {
        return (this.amount / 5000) * 255;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Voltage && ((Voltage) other).amount == this.amount;
    }

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
