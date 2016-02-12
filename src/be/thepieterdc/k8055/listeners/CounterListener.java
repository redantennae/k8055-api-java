package be.thepieterdc.k8055.listeners;

import be.thepieterdc.k8055.events.CounterEvent;

import java.util.EventListener;

/**
 * Created by pieter on 11/02/16.
 */
interface CounterListener extends EventListener {
    void onCounterIncrement(CounterEvent e);
    void onCounterReset(CounterEvent e);
}
