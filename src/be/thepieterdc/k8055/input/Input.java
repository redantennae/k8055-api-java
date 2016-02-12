package be.thepieterdc.k8055.input;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.helpers.IO;

public abstract class Input<O extends IO.IOInterface,V> extends IO<O> {

    public Input(K8055 k8055, Signal signal, O o) {
        super(k8055, signal, Type.INPUT, o);
    }

    abstract V value();
}
