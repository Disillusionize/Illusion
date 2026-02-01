package org.example.illusion.module.client;

import org.example.illusion.api.setting.impl.CheckSetting;
import org.example.illusion.api.setting.impl.ComboSetting;
import org.example.illusion.api.setting.impl.SliderSetting;
import org.example.illusion.api.module.Category;
import org.example.illusion.api.module.Module;
import org.example.illusion.api.module.ModuleInfo;
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
