package org.example.illusion.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.illusion.module.Module;

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

    @Override
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("name", getName());
        obj.addProperty("value", value);
        return obj;
    }

    @Override
    public void fromJson(JsonElement value) {
        if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isNumber()) {
            this.value = value.getAsFloat();
        }
    }
}
