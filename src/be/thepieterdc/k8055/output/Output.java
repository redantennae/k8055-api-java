package be.thepieterdc.k8055.output;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.helpers.IO;

public abstract class Output<O extends IO.IOInterface> extends IO<O> {

    public Output(K8055 k8055, Signal signal, O o) {
        super(k8055, signal, Type.OUTPUT, o);
    }

    abstract void clear();
}
