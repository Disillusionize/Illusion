package org.example.illusion.feature.screen.api.setting.impl;

import org.example.illusion.feature.screen.api.setting.api.Setting;
import org.example.illusion.feature.module.api.Module;

public class SliderSetting extends Setting {
    private final float min, max;

    private float value;

    public SliderSetting(String name, Module parent, float min, float max) {
        this(name, parent, min, max, max/min);
    }

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
