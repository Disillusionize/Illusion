package org.example.illusion.event.render;

import org.example.illusion.api.event.Event;

public final class Render2DEvent implements Event {
    private final float partialTicks;

    public Render2DEvent(final float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
