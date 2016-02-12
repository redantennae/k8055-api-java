package be.thepieterdc.k8055.input;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;

public class AnalogInput {

    public enum AnalogInputs {
        ONE(1),
        TWO(2);

        private final int channel;

        AnalogInputs(int chan) {
            this.channel = chan;
        }

        public int channel() {
            return this.channel;
        }

        public static AnalogInputs fromChannel(int c) {
            for(AnalogInputs ais : AnalogInputs.values()) {
                if(ais.channel == c) {
                    return ais;
                }
            }
            throw new IllegalArgumentException("Channel must be in range [1-2].");
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
        return this.ai.channel;
    }

    public AnalogInputs input() {
        return this.ai;
    }

    @Override
    public String toString() {
        return "AnalogInput[channel="+this.ai.channel+"]";
    }

    public int value() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        return this.k8055.board().ReadAnalogChannel(this.ai.channel);
    }
}