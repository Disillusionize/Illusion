package org.example.illusion;

import io.github.nevalackin.radbus.PubSub;
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
    private ClickGui clickGui;

    public void initialize() {
        INSTANCE = this;

        eventBus = PubSub.newInstance(System.err::println);
        moduleManager = new ModuleManager();
        clickGui = new ClickGui();
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

    public ClickGui getClickGui() {
        return clickGui;
    }
}
