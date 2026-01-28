package org.example.illusion.feature.module.api;

import org.example.illusion.IllusionClient;

public class Toggleable {
    private boolean enabled;

    public final void toggle() {
        setEnabled(!enabled);
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;

            if (enabled) {
                onEnable();
                IllusionClient.getInstance().getEventBus().subscribe(this);
            } else {
                IllusionClient.getInstance().getEventBus().unsubscribe(this);
                onDisable();
            }
        }
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }
}