package org.example.illusion.event.impl;

import org.example.illusion.event.Event;


public final class Render3DEvent implements Event {
    private final float partialTicks;

    public Render3DEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
