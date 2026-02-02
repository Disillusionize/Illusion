package org.example.illusion.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.illusion.module.Module;

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

    @Override
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("name", getName());
        obj.addProperty("value", enabled);
        return obj;
    }

    @Override
    public void fromJson(JsonElement value) {
        if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isBoolean()) {
            this.enabled = value.getAsBoolean();
        }
    }
}

