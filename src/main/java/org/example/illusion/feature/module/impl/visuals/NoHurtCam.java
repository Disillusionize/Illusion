package org.example.illusion.feature.module.impl.visuals;

import io.github.nevalackin.radbus.Listen;
import org.example.illusion.event.impl.render.HurtShakeEvent;
import org.example.illusion.feature.module.api.Category;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.api.ModuleInfo;

@ModuleInfo(name = "NoHurtCam", category = Category.VISUALS)
public class NoHurtCam extends Module {
    @Listen
    public void onHurtShake(HurtShakeEvent event) {
        event.setCancelled();
    }
}
