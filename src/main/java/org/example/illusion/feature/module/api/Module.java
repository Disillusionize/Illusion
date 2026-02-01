package org.example.illusion.feature.module.api;

import org.apache.commons.lang3.Validate;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.client.ModuleDisabledEvent;
import org.example.illusion.event.impl.client.ModuleEnabledEvent;
import org.example.illusion.feature.api.Feature;
import org.example.illusion.feature.screen.api.setting.api.Setting;

import java.util.ArrayList;
import java.util.List;

public abstract class Module implements Feature {

    private final String name, description;
    private final Category category;
    private final List<Setting> settings = new ArrayList<>();

    private int bind;
    private boolean enabled;

    public Module() {
        ModuleInfo info = Validate.notNull(this.getClass().getAnnotation(ModuleInfo.class), "Missing Annotation");

        this.name = info.name();
        this.description = info.description();
        this.category = info.category();
        this.bind = info.bind();
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String getDescription() {
        return description;
    }

    public final Category getCategory() {
        return category;
    }

    public final int getBind() {
        return bind;
    }

    public final void setBind(int bind) {
        this.bind = bind;
        if (IllusionClient.getInstance().getConfigManager() != null) {
            IllusionClient.getInstance().getConfigManager().saveConfig();
        }
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;

            if (enabled) {
                IllusionClient.getInstance().getEventBus().subscribe(this);
                onEnable();
                IllusionClient.getInstance().getEventBus().publish(new ModuleEnabledEvent(this));
            } else {
                IllusionClient.getInstance().getEventBus().unsubscribe(this);
                onDisable();
                IllusionClient.getInstance().getEventBus().publish(new ModuleDisabledEvent(this));
            }

            if (IllusionClient.getInstance().getConfigManager() != null) {
                IllusionClient.getInstance().getConfigManager().saveConfig();
            }
        }
    }

    public final void toggle() {
        setEnabled(!enabled);
    }

    public void onEnable() {}

    public void onDisable() {}

    public void onUpdate() {}

    public final List<Setting> getSettings() {
        return settings;
    }

    public final void addSetting(Setting setting) {
        settings.add(setting);
    }
}
