package be.thepieterdc.k8055.listeners;

import be.thepieterdc.k8055.events.OutputEvent;

import java.util.EventListener;

public interface OutputListener extends EventListener {
    void onOutputChange(OutputEvent e);
}
