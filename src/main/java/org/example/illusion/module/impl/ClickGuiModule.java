package org.example.illusion.module.impl;

import org.example.illusion.IllusionClient;
import org.example.illusion.module.Category;
import org.example.illusion.module.Module;
import org.example.illusion.module.ModuleInfo;
import org.example.illusion.setting.ComboSetting;
import org.example.illusion.ui.menu.ClickGui;
import org.example.illusion.util.Wrapper;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "ClickGui", category = Category.CLIENT, bind = Keyboard.KEY_RSHIFT)
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

