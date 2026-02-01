package org.example.illusion.feature.screen.api.setting.impl;

import org.example.illusion.feature.screen.api.setting.api.Setting;
import org.example.illusion.feature.module.api.Module;

public class CheckSetting extends Setting {
    private boolean enabled;

    public CheckSetting(String name, Module parent, boolean enabled) {
        super(name, parent);

        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        this.getParent().onUpdate();
        if (org.example.illusion.IllusionClient.getInstance().getConfigManager() != null) {
            org.example.illusion.IllusionClient.getInstance().getConfigManager().saveConfig();
        }
    }
}

