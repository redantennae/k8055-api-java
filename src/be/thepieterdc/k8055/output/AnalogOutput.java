package be.thepieterdc.k8055.output;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.helpers.IO;
import be.thepieterdc.k8055.helpers.Voltage;

public class AnalogOutput extends Output<AnalogOutput.AnalogOutputs> {

    public enum AnalogOutputs implements IO.IOInterface {
        ONE(1),
        TWO(2);

        private final int channel;

        AnalogOutputs(int chan) {
            this.channel = chan;
        }

        @Override
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

    public AnalogOutput(K8055 k8055, AnalogOutputs analogOutputs) {
        super(k8055, Signal.ANALOG, analogOutputs);
    }

    @Override
    public void clear() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().ClearAnalogChannel(this.ioInterface.channel);
    }

    public void max() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().SetAnalogChannel(this.ioInterface.channel);
    }

    public void set(int voltage) {
        if(voltage < 0 || voltage > 255) {
            throw new IllegalArgumentException("Voltage must be in range [0-255].");
        }
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().OutputAnalogChannel(this.ioInterface.channel, voltage);
    }

    public void set(Voltage voltage) {
        set(voltage.analog());
    }
}