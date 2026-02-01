package org.example.illusion.api.setting.impl;

import org.example.illusion.api.setting.api.Setting;
import org.example.illusion.api.module.Module;

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

