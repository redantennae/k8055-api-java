package be.thepieterdc.k8055.output;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.helpers.IO;

/**
 * Abstract output handler.
 *
 * @author Pieter De Clercq
 *
 * @param <O> the output type
 */
public abstract class Output<O extends IO.IOInterface> extends IO<O> {

    /**
     * Output constructor.
     *
     * @param k8055 the k8055 to use
     * @param signal the signal type
     * @param o the output to use
     */
    public Output(K8055 k8055, Signal signal, O o) {
        super(k8055, signal, Type.OUTPUT, o);
    }

    /**
     * Clears/resets the output.
     */
    abstract void clear();
}
