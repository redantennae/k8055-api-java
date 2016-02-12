package be.thepieterdc.k8055.input;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;

public class DigitalInput {

    public enum DigitalInputs {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);

        private final int channel;

        DigitalInputs(int chan) {
            this.channel = chan;
        }

        public int channel() {
            return this.channel;
        }

        public static DigitalInputs fromChannel(int c) {
            for(DigitalInputs dis : DigitalInputs.values()) {
                if(dis.channel == c) {
                    return dis;
                }
            }
            throw new IllegalArgumentException("Channel must be in range [1-5].");
        }
    }

    private final DigitalInputs di;
    private final K8055 k8055;

    public DigitalInput(K8055 k8055, DigitalInputs digitalInputs) {
        if(k8055 == null) {
            throw new IllegalArgumentException("K8055 is null.");
        } else if(digitalInputs == null) {
            throw new IllegalArgumentException("Digital input is null.");
        }
        this.di = digitalInputs;
        this.k8055 = k8055;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof DigitalInput && ((DigitalInput) other).di.equals(this.di);
    }

    public DigitalInputs input() {
        return this.di;
    }

    @Override
    public int hashCode() {
        return this.di.channel;
    }

    @Override
    public String toString() {
        return "DigitalInput[channel="+this.di.channel+"]";
    }

    public boolean value() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        return this.k8055.board().ReadDigitalChannel(this.di.channel);
    }
}