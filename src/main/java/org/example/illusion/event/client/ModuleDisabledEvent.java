package org.example.illusion.event.client;

import org.example.illusion.api.event.Event;
import org.example.illusion.api.module.Module;

public class ModuleDisabledEvent implements Event {
    private final Module module;

    public ModuleDisabledEvent(final Module module) {
        this.module = module;
    }

    public Module getModule() {
        return module;
    }
}