package org.example.illusion.feature.module.impl.misc;

import org.example.illusion.IllusionClient;
import org.example.illusion.feature.screen.api.setting.impl.ComboSetting;
import org.example.illusion.feature.module.api.Category;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.api.ModuleInfo;
import org.example.illusion.feature.screen.impl.ClickGui;
import org.example.illusion.util.Wrapper;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "ClickGui", category = Category.MISC, bind = Keyboard.KEY_RSHIFT)
public class ClickGuiModule extends Module {
    public ComboSetting theme = new ComboSetting("Theme", this, new String[]{"Illusion", "Amethyst", "Orange", "White"});

    public ClickGuiModule() {
        addSetting(theme);
    }

    @Override
    public void onEnable() {
        Wrapper.getClient().displayGuiScreen(IllusionClient.getInstance().getClickGui(Wrapper.getClient().currentScreen));
    }

    @Override
    public void onDisable() {
        if (Wrapper.getClient().currentScreen instanceof ClickGui) {
            ClickGui gui = (ClickGui) Wrapper.getClient().currentScreen;
            Wrapper.getClient().displayGuiScreen(gui.getParent());
        }
    }
}

