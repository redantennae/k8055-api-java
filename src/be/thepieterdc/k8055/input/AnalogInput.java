package be.thepieterdc.k8055.input;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;

public class AnalogInput {

    public enum AnalogInputs {
        ONE(1),
        TWO(2);

        private final int address;

        AnalogInputs(int addr) {
            this.address = addr;
        }

        public static AnalogInputs fromAddress(int a) {
            for(AnalogInputs ais : AnalogInputs.values()) {
                if(ais.address == a) {
                    return ais;
                }
            }
            throw new IllegalArgumentException("Addres must be in range [1-2].");
        }

        public int address() {
            return this.address;
        }
    }

    private final AnalogInputs ai;
    private final K8055 k8055;

    public AnalogInput(K8055 k8055, AnalogInputs analogInputs) {
        if(k8055 == null) {
            throw new IllegalArgumentException("K8055 is null.");
        } else if(analogInputs == null) {
            throw new IllegalArgumentException("Analog input is null.");
        }
        this.ai = analogInputs;
        this.k8055 = k8055;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof AnalogInput && ((AnalogInput) other).ai.equals(this.ai);
    }

    @Override
    public int hashCode() {
        return this.ai.address;
    }

    @Override
    public String toString() {
        return "AnalogInput[address="+this.ai.address+"]";
    }
}