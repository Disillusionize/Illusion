package org.example.illusion.feature.module.impl.combat;

import io.github.nevalackin.radbus.Listen;
import org.example.illusion.event.impl.entity.RayTraceEntityEvent;
import org.example.illusion.feature.screen.api.setting.impl.SliderSetting;
import org.example.illusion.feature.module.api.Category;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.api.ModuleInfo;

@ModuleInfo(name = "Reach", category = Category.COMBAT)
public class Reach extends Module {
    private final SliderSetting distance = new SliderSetting("Distance", this, 3f, 6f, 3.5f);

    public Reach() {
        addSetting(distance);
    }

    @Listen
    public void onRayTrace(RayTraceEntityEvent event) {
        event.setReach(distance.getValue());
    }
}
