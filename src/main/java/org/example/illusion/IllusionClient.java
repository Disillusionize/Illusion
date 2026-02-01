package org.example.illusion;

import io.github.nevalackin.radbus.PubSub;
import net.minecraft.client.gui.GuiScreen;
import org.example.illusion.feature.config.ConfigManager;
import org.example.illusion.event.api.Event;
import org.example.illusion.feature.Initializer;
import org.example.illusion.feature.screen.impl.ClickGui;
import org.example.illusion.feature.module.api.ModuleManager;

public final class IllusionClient implements Initializer {
    public static final String ID = "illusion";
    public static final String NAME = "Illusion Client";
    public static final String VERSION = "0.1.0";

    private static IllusionClient INSTANCE;

    private PubSub<Event> eventBus;
    private ModuleManager moduleManager;
    private ConfigManager configManager;

    public void initialize() {
        INSTANCE = this;

        eventBus = PubSub.newInstance(System.err::println);
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();

        configManager.loadConfig();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> configManager.saveConfig()));
    }

    public static IllusionClient getInstance() {
        return INSTANCE;
    }

    public PubSub<Event> getEventBus() {
        return eventBus;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public ClickGui getClickGui(GuiScreen screen) {
        return new ClickGui(screen);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
