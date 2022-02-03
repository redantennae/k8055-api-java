package be.thepieterdc.k8055.input;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.exceptions.ConnectionStatusException;
import be.thepieterdc.k8055.helpers.IO;

/**
 * Analog input handler.
 *
 * @author Pieter De Clercq
 */
public class AnalogInput extends Input<AnalogInput.AnalogInputs,Integer>{

    /**
     * Enum that contains the actual analog inputs.
     */
    public enum AnalogInputs implements IO.IOInterface {
        ONE(1),
        TWO(2);

        private final int channel;

        /**
         * AnalogInputs constructor.
         *
         * @param chan the channel
         */
        AnalogInputs(int chan) {
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
         * Gets an analog input from the given channel.
         *
         * @param c the channel
         * @return the analog input
         */
        public static AnalogInputs fromChannel(int c) {
            for(AnalogInputs ais : AnalogInputs.values()) {
                if(ais.channel == c) {
                    return ais;
                }
            }
            throw new IllegalArgumentException("Channel must be in range [1-2].");
        }
    }

    /**
     * AnalogInput constructor.
     *
     * @param k8055 the k8055 to use
     * @param analogInputs the analog input
     */
    public AnalogInput(K8055 k8055, AnalogInputs analogInputs) {
        super(k8055, Signal.ANALOG, analogInputs);
    }

    /**
     * @return the value of the analog input channel
     */
    @Override
    public Integer value() {
        if(!this.k8055.connected()) {
            throw ConnectionStatusException.connectionRequired();
        }
        return this.k8055.board().ReadAnalogChannel(this.ioInterface.channel);
    }
}