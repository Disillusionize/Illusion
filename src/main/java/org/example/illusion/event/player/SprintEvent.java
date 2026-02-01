package org.example.illusion.event.player;

import org.example.illusion.api.event.Event;

public final class SprintEvent implements Event {
    private boolean sprinting;

    public SprintEvent(boolean sprinting) {
        this.sprinting = sprinting;
    }

    public boolean isSprinting() {
        return sprinting;
    }

    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }
}
