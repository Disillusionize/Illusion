package org.example.illusion.event.impl;

import net.minecraft.client.gui.ScaledResolution;
import org.example.illusion.event.Event;

public final class Render2DEvent implements Event {
    private final float partialTicks;
    private final ScaledResolution scaledResolution;

    public Render2DEvent(final float partialTicks, ScaledResolution scaledResolution) {
        this.partialTicks = partialTicks;
        this.scaledResolution = scaledResolution;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public ScaledResolution getScaledResolution() {
        return scaledResolution;
    }
}
