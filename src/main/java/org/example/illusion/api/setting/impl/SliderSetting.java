package org.example.illusion.api.setting.impl;

import org.example.illusion.api.setting.api.Setting;
import org.example.illusion.api.module.Module;

public class SliderSetting extends Setting {
    private final float min, max;

    private float value;

    public SliderSetting(String name, Module parent, float min, float max, float value) {
        super(name, parent);

        this.min = min;
        this.max = max;
        this.value = value;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
        this.getParent().onUpdate();
        if (org.example.illusion.IllusionClient.getInstance().getConfigManager() != null) {
            org.example.illusion.IllusionClient.getInstance().getConfigManager().saveConfig();
        }
    }
}
