package be.thepieterdc.k8055.helpers;

import be.thepieterdc.k8055.K8055;

/**
 * Created by pieter on 12/02/16.
 */
public abstract class IO<I extends IO.IOInterface> {

    public interface IOInterface {
        int channel();
    }

    public enum Signal {
        ANALOG("Analog"),
        DIGITAL("Digital");

        private final String scalar;

        Signal(String s) {
            this.scalar = s;
        }

        public static Signal fromScalar(String sc) {
            for(Signal s : Signal.values()) {
                if(s.scalar.equals(sc)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("Signal must be in [Analog, Digital].");
        }

        public String scalar() {
            return this.scalar;
        }
    }

    public enum Type {
        INPUT("Input"),
        OUTPUT("Output");

        private final String scalar;

        Type(String s) {
            this.scalar = s;
        }

        public static Type fromScalar(String s) {
            for(Type t : Type.values()) {
                if(t.scalar.equals(s)) {
                    return t;
                }
            }
            throw new IllegalArgumentException("Type must be in [Input, Output].");
        }

        public String scalar() {
            return this.scalar;
        }
    }

    protected final I ioInterface;
    protected final K8055 k8055;
    protected final Signal signal;
    protected final Type type;

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

    public I ioInterface() {
        return this.ioInterface;
    }

    public Signal signal() {
        return this.signal;
    }

    @Override
    public String toString() {
        return "IO[signal="+this.signal+", type="+this.type+", channel="+this.channel()+"]";
    }

    public Type type() {
        return this.type;
    }
}
