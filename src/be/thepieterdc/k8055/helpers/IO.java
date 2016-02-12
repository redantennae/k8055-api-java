package be.thepieterdc.k8055.helpers;

import be.thepieterdc.k8055.K8055;


/**
 * Abstract input or output handler.
 *
 * @author Pieter De Clercq
 *
 * @param <I> the input or output
 */
public abstract class IO<I extends IO.IOInterface> {

    /**
     * Interface for input or output channels.
     */
    public interface IOInterface {
        /**
         * Gets the channel number.
         *
         * @return the channel number
         */
        int channel();
    }

    /**
     * Enum that contains signal types.
     */
    public enum Signal {
        ANALOG("Analog"),
        DIGITAL("Digital");

        private final String scalar;

        /**
         * Signal constructor.
         *
         * @param s the name of the signal
         */
        Signal(String s) {
            this.scalar = s;
        }

        /**
         * Gets the signal from a scalar name.
         *
         * @param sc the name of the signal
         * @return the signal
         */
        public static Signal fromScalar(String sc) {
            for(Signal s : Signal.values()) {
                if(s.scalar.equals(sc)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("Signal must be in [Analog, Digital].");
        }

        /**
         * @return the name of the signal
         */
        public String scalar() {
            return this.scalar;
        }
    }

    /**
     * Enum that contains IO types.
     */
    public enum Type {
        INPUT("Input"),
        OUTPUT("Output");

        private final String scalar;

        /**
         * Type constructor.
         *
         * @param s the name of the signal
         */
        Type(String s) {
            this.scalar = s;
        }

        /**
         * Gets the type from a scalar name.
         *
         * @param s the name of the type
         * @return the type
         */
        public static Type fromScalar(String s) {
            for(Type t : Type.values()) {
                if(t.scalar.equals(s)) {
                    return t;
                }
            }
            throw new IllegalArgumentException("Type must be in [Input, Output].");
        }

        /**
         * @return the name of the type
         */
        public String scalar() {
            return this.scalar;
        }
    }

    protected final I ioInterface;
    protected final K8055 k8055;
    protected final Signal signal;
    protected final Type type;

    /**
     * IO constructor.
     *
     * @param k the k8055 to use
     * @param s the signal
     * @param t the type
     * @param ioInterface the input or output channel
     */
    public IO(K8055 k, Signal s, Type t, I ioInterface) {
        if(k == null) {
            throw new IllegalArgumentException("K8055 cannot be null.");
        } else if(s == null) {
            throw new IllegalArgumentException("Signal cannot be null.");
        } else if(t == null) {
            throw new IllegalArgumentException("Type cannot be null.");
        } else if(ioInterface == null) {
            throw new IllegalArgumentException("IOInterface cannot be null.");
        }
        this.ioInterface = ioInterface;
        this.k8055 = k;
        this.signal = s;
        this.type = t;
    }

    /**
     * @return the channel number
     */
    public int channel() {
        return this.ioInterface.channel();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof IO && ((IO) other).signal.equals(this.signal) && ((IO) other).type.equals(this.type) && ((IO) other).channel() == this.channel();
    }

    @Override
    public int hashCode() {
        return this.ioInterface.channel();
    }

    /**
     * @return the input or output channel
     */
    public I ioInterface() {
        return this.ioInterface;
    }

    /**
     * @return the signal
     */
    public Signal signal() {
        return this.signal;
    }

    @Override
    public String toString() {
        return "IO[signal="+this.signal+", type="+this.type+", channel="+this.channel()+"]";
    }

    /**
     * @return the type
     */
    public Type type() {
        return this.type;
    }
}
