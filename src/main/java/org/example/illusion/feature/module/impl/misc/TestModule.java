package org.example.illusion.feature.module.impl.misc;

import org.example.illusion.feature.screen.api.setting.impl.CheckSetting;
import org.example.illusion.feature.screen.api.setting.impl.ComboSetting;
import org.example.illusion.feature.screen.api.setting.impl.SliderSetting;
import org.example.illusion.feature.module.api.Category;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.api.ModuleInfo;
import org.example.illusion.util.Wrapper;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Test", category = Category.MISC, bind = Keyboard.KEY_R)
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
