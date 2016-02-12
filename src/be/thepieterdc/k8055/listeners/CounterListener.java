package be.thepieterdc.k8055.listeners;

import be.thepieterdc.k8055.events.CounterEvent;

import java.util.EventListener;

public interface CounterListener extends EventListener {
    void onCounterIncrement(CounterEvent e);
    void onCounterReset(CounterEvent e);
}
