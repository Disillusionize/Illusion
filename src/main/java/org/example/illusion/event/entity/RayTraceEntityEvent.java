package org.example.illusion.event.entity;

import org.example.illusion.api.event.Event;

public final class RayTraceEntityEvent implements Event {
    private float reach = 3.0F;
    private float borderMultiplier = 1.0F;

    public float getReach() {
        return reach;
    }

    public void setReach(float reach) {
        this.reach = reach;
    }

    public float getBorderMultiplier() {
        return borderMultiplier;
    }

    public void setBorderMultiplier(float borderMultiplier) {
        this.borderMultiplier = borderMultiplier;
    }
}
