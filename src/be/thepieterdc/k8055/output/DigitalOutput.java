package be.thepieterdc.k8055.output;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.helpers.Voltage;

public class DigitalOutput {

    public enum DigitalOutputs {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8);

        private final int channel;

        DigitalOutputs(int chan) {
            this.channel = chan;
        }

        public int channel() {
            return this.channel;
        }

        public static DigitalOutputs fromChannel(int c) {
            for(DigitalOutputs dos : DigitalOutputs.values()) {
                if(dos.channel == c) {
                    return dos;
                }
            }
            throw new IllegalArgumentException("Channel must be in range [1-2].");
        }
    }

    private final DigitalOutputs dou;
    private final K8055 k8055;

    public DigitalOutput(K8055 k8055, DigitalOutputs digitalOutputs) {
        if(k8055 == null) {
            throw new IllegalArgumentException("K8055 is null.");
        } else if(digitalOutputs == null) {
            throw new IllegalArgumentException("Digital output is null.");
        }
        this.dou = digitalOutputs;
        this.k8055 = k8055;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof DigitalOutput && ((DigitalOutput) other).dou.equals(this.dou);
    }

    @Override
    public int hashCode() {
        return this.dou.channel;
    }

    public void off() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().ClearDigitalChannel(this.dou.channel);
    }

    public void on() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().SetDigitalChannel(this.dou.channel);
    }

    public DigitalOutputs output() {
        return this.dou;
    }

    @Override
    public String toString() {
        return "DigitalOutput[channel="+this.dou.channel+"]";
    }
}