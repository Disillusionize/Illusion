package org.example.illusion.api.setting.impl;

import org.example.illusion.api.setting.api.Setting;
import org.example.illusion.api.module.Module;

public class ComboSetting extends Setting {
    private final String[] options;
    private String value;
    private int index;

    public ComboSetting(String name, Module parent, String[] options) {
        this(name, parent, options, 0);
    }

    public ComboSetting(String name, Module parent, String[] options, int defaultIndex) {
        super(name, parent);
        this.options = options;
        this.index = defaultIndex >= 0 && defaultIndex < options.length ? defaultIndex : 0;
        this.value = options[this.index];
    }

    public String[] getOptions() {
        return options;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public void next() {
        index = (index + 1) % options.length;
        setValue(options[index]);
    }

    public void previous() {
        index = (index - 1 + options.length) % options.length;
        setValue(options[index]);
    }

    public void setValue(String newValue) {
        for (int i = 0; i < options.length; i++) {
            if (options[i].equalsIgnoreCase(newValue)) {
                this.value = options[i];
                this.index = i;
                this.getParent().onUpdate();
                if (org.example.illusion.IllusionClient.getInstance().getConfigManager() != null) {
                    org.example.illusion.IllusionClient.getInstance().getConfigManager().saveConfig();
                }
                return;
            }
        }
    }

    public void setIndex(int newIndex) {
        if (newIndex >= 0 && newIndex < options.length) {
            this.index = newIndex;
            setValue(options[newIndex]);
        }
    }
}
