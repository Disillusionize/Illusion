package org.example.illusion.module.impl;

import org.example.illusion.module.Category;
import org.example.illusion.module.Module;
import org.example.illusion.module.ModuleInfo;
import org.example.illusion.setting.CheckSetting;
import org.example.illusion.setting.ComboSetting;
import org.example.illusion.setting.SliderSetting;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Test", category = Category.CLIENT, bind = Keyboard.KEY_R)
public class TestModule extends Module {

    public TestModule() {
        ComboSetting mode = new ComboSetting("Mode", this, new String[]{"One", "Two", "Three"});
        addSetting(mode);
        SliderSetting slider = new SliderSetting("Slider", this, 1.0f, 10.0f, 5.0f);
        addSetting(slider);
        CheckSetting check = new CheckSetting("Check", this, true);
        addSetting(check);
    }

}
