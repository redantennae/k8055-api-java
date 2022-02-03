package be.thepieterdc.k8055.output;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.helpers.IO;
import be.thepieterdc.k8055.helpers.Voltage;

/**
 * Analog output handler
 *
 * @author Pieter De Clercq
 */
public class AnalogOutput extends Output<AnalogOutput.AnalogOutputs> {

    /**
     * Enum that contains the actual analog outputs.
     */
    public enum AnalogOutputs implements IO.IOInterface {
        ONE(1),
        TWO(2);

        private final int channel;

        /**
         * AnalogOutputs constructor.
         *
         * @param chan the channel
         */
        AnalogOutputs(int chan) {
            this.channel = chan;
        }

        /**
         * @return the channel number
         */
        @Override
        public int channel() {
            return this.channel;
        }

        /**
         * Gets an analog output from the given channel.
         *
         * @param c the channel
         * @return the analog output
         */
        public static AnalogOutputs fromChannel(int c) {
            for(AnalogOutputs aos : AnalogOutputs.values()) {
                if(aos.channel == c) {
                    return aos;
                }
            }
            throw new IllegalArgumentException("Channel must be in range [1-2].");
        }
    }

    /**
     * AnalogOutput constructor.
     *
     * @param k8055 the k8055 to use
     * @param analogOutputs the analog output
     */
    public AnalogOutput(K8055 k8055, AnalogOutputs analogOutputs) {
        super(k8055, Signal.ANALOG, analogOutputs);
    }

    /**
     * Sets the output value to 0.
     */
    @Override
    public void clear() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().ClearAnalogChannel(this.ioInterface.channel);
    }

    /**
     * Sets the output value to maximum(255).
     */
    public void max() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().SetAnalogChannel(this.ioInterface.channel);
    }

    /**
     * Sets the output value to a specified voltage.
     *
     * @param voltage the voltage to set
     */
    public void set(int voltage) {
        if(voltage < 0 || voltage > 255) {
            throw new IllegalArgumentException("Voltage must be in range [0-255].");
        }
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().OutputAnalogChannel(this.ioInterface.channel, voltage);
    }

    /**
     * Sets the output value to a specified voltage.
     *
     * @param voltage the voltage to set
     */
    public void set(Voltage voltage) {
        set(voltage.analog());
    }
}