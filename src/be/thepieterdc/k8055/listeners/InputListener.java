package be.thepieterdc.k8055.listeners;

import be.thepieterdc.k8055.events.InputEvent;

import java.util.EventListener;

public interface InputListener extends EventListener {
    void onInputChange(InputEvent e);
}
