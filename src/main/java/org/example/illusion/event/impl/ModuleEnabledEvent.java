package org.example.illusion.event.impl;

import org.example.illusion.event.Event;
import org.example.illusion.module.Module;


public class ModuleEnabledEvent implements Event {
    private final Module module;

    public ModuleEnabledEvent(final Module module) {
        this.module = module;
    }

    public Module getModule() {
        return module;
    }
}
