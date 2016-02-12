package be.thepieterdc.k8055.input;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.helpers.IO;

public class AnalogInput extends Input<AnalogInput.AnalogInputs,Integer>{

    public enum AnalogInputs implements IO.IOInterface {
        ONE(1),
        TWO(2);

        private final int channel;

        AnalogInputs(int chan) {
            this.channel = chan;
        }

        @Override
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

    public AnalogInput(K8055 k8055, AnalogInputs analogInputs) {
        super(k8055, Signal.ANALOG, analogInputs);
    }

    @Override
    public Integer value() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        return this.k8055.board().ReadAnalogChannel(this.ioInterface.channel);
    }
}