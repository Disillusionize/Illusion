package org.example.illusion.feature.module.api;

import org.apache.commons.lang3.Validate;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.client.ModuleDisabledEvent;
import org.example.illusion.event.impl.client.ModuleEnabledEvent;
import org.example.illusion.feature.api.Feature;
import org.example.illusion.feature.screen.api.setting.api.Setting;

import java.util.ArrayList;

public class Module extends Toggleable implements Feature {
    private final String name;
    private final String description;
    private final Category category;
    private int bind;

    private final ArrayList<Setting> settings;

    public Module() {
        ModuleInfo info = Validate.notNull(this.getClass().getAnnotation(ModuleInfo.class), "Missing Annotation");

        this.name = info.name();
        this.description = info.description();
        this.category = info.category();
        this.bind = info.bind();

        this.settings = new ArrayList<>();
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String getDescription() {
        return description;
    }

    @Override
    public final void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            IllusionClient.getInstance().getEventBus().publish(new ModuleEnabledEvent(this));
        } else {
            IllusionClient.getInstance().getEventBus().publish(new ModuleDisabledEvent(this));
        }
    }

    public final Category getCategory() {
        return category;
    }

    public final int getBind() {
        return bind;
    }

    public final void setBind(int bind) {
        this.bind = bind;
    }

    public final ArrayList<Setting> getSettings() {
        return settings;
    }

    public final void addSetting(Setting setting) {
        settings.add(setting);
    }

    public void onUpdate() {
    }
}
