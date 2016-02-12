package be.thepieterdc.k8055.input;

import be.thepieterdc.k8055.K8055;
import be.thepieterdc.k8055.helpers.IO;

/**
 * Abstract input handler.
 *
 * @author Pieter De Clercq
 *
 * @param <I> the input type
 * @param <V> the return type of the value method
 */
public abstract class Input<I extends IO.IOInterface,V> extends IO<I> {

    /**
     * Input constructor.
     *
     * @param k8055 the k8055 to use
     * @param signal the signal type
     * @param i the input to use
     */
    public Input(K8055 k8055, Signal signal, I i) {
        super(k8055, signal, Type.INPUT, i);
    }

    /**
     * @return the value of the input channel
     */
    abstract V value();
}
