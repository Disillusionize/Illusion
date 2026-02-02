package org.example.illusion.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.illusion.core.Feature;
import org.example.illusion.module.Module;

public abstract class Setting implements Feature {
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

    public final Module getParent() {
        return parent;
    }

    public abstract JsonObject toJson();

    public abstract void fromJson(JsonElement value);
}
