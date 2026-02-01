package org.example.illusion.api.setting.api;

import org.example.illusion.api.Feature;
import org.example.illusion.api.module.Module;

public class Setting implements Feature {
    private final String name;
    private final Module parent;

    public Setting(String name, Module parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String getDescription() {
        return "UNUSED";
    }

    public final Module getParent() {
        return parent;
    }
}
