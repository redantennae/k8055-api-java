package be.thepieterdc.k8055.output;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.helpers.IO;
import be.thepieterdc.k8055.helpers.Voltage;

/**
 * Digital output handler
 *
 * @author Pieter De Clercq
 */
public class DigitalOutput extends Output<DigitalOutput.DigitalOutputs> {

    /**
     * Enum that contains the actual digital outputs.
     */
    public enum DigitalOutputs implements IO.IOInterface {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8);

        private final int channel;

        /**
         * DigitalOutputs constructor.
         *
         * @param chan the channel
         */
        DigitalOutputs(int chan) {
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
         * Gets a digital output from the given channel.
         *
         * @param c the channel
         * @return the digital output
         */
        public static DigitalOutputs fromChannel(int c) {
            for(DigitalOutputs dos : DigitalOutputs.values()) {
                if(dos.channel == c) {
                    return dos;
                }
            }
            throw new IllegalArgumentException("Channel must be in range [1-2].");
        }
    }

    /**
     * DigitalOutput constructor.
     *
     * @param k8055 the k8055 to use
     * @param digitalOutputs the digital output
     */
    public DigitalOutput(K8055 k8055, DigitalOutputs digitalOutputs) {
        super(k8055, Signal.DIGITAL, digitalOutputs);
    }


    /**
     * Set the value of the output to false.
     */
    @Override
    public void clear() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().ClearDigitalChannel(this.ioInterface.channel);
    }

    /**
     * Set the value of the output to true.
     */
    public void on() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        this.k8055.board().SetDigitalChannel(this.ioInterface.channel);
    }
}