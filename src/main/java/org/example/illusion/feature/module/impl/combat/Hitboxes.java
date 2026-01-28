package org.example.illusion.feature.module.impl.combat;

import io.github.nevalackin.radbus.Listen;
import org.example.illusion.event.impl.entity.RayTraceEntityEvent;
import org.example.illusion.feature.screen.api.setting.impl.SliderSetting;
import org.example.illusion.feature.module.api.Category;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.api.ModuleInfo;

@ModuleInfo(name = "Hitboxes", category = Category.COMBAT)
public class Hitboxes extends Module {
    private final SliderSetting expand = new SliderSetting("Expand", this, 1f, 10f, 2f);

    public Hitboxes() {
        addSetting(expand);
    }

    @Listen
    public void onRayTrace(RayTraceEntityEvent event) {
        event.setBorderMultiplier(expand.getValue());
    }
}
