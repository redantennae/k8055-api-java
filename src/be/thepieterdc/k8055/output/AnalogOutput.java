package be.thepieterdc.k8055.output;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.helpers.Voltage;

public class AnalogOutput {

    public enum AnalogOutputs {
        ONE(1),
        TWO(2);

        private final int channel;

        AnalogOutputs(int chan) {
            this.channel = chan;
        }

        public int channel() {
            return this.channel;
        }

        public static AnalogOutputs fromChannel(int c) {
            for(AnalogOutputs aos : AnalogOutputs.values()) {
                if(aos.channel == c) {
                    return aos;
                }
            }
            throw new IllegalArgumentException("Channel must be in range [1-2].");
        }
    }

    private final AnalogOutputs ao;
    private final K8055 k8055;

    public AnalogOutput(K8055 k8055, AnalogOutputs analogInputs) {
        if(k8055 == null) {
            throw new IllegalArgumentException("K8055 is null.");
        } else if(analogInputs == null) {
            throw new IllegalArgumentException("Analog output is null.");
        }
        this.ao = analogInputs;
        this.k8055 = k8055;
    }

    public void clear() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().ClearAnalogChannel(this.ao.channel);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof AnalogOutput && ((AnalogOutput) other).ao.equals(this.ao);
    }

    @Override
    public int hashCode() {
        return this.ao.channel;
    }

    public AnalogOutputs output() {
        return this.ao;
    }

    public void max() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().SetAnalogChannel(this.ao.channel);
    }

    public void set(int voltage) {
        if(voltage < 0 || voltage > 255) {
            throw new IllegalArgumentException("Voltage must be in range [0-255].");
        }
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().OutputAnalogChannel(this.ao.channel, voltage);
    }

    public void set(Voltage voltage) {
        set(voltage.analog());
    }

    @Override
    public String toString() {
        return "AnalogOutput[channel="+this.ao.channel+"]";
    }
}