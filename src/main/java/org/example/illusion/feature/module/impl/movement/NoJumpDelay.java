package org.example.illusion.feature.module.impl.movement;

import io.github.nevalackin.radbus.Listen;
import org.example.illusion.event.impl.player.JumpDelayEvent;
import org.example.illusion.feature.module.api.Category;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.api.ModuleInfo;

@ModuleInfo(name = "NoJumpDelay", category = Category.MOVEMENT)
public class NoJumpDelay extends Module {
    @Listen
    public void onJump(JumpDelayEvent event) {
        event.setJumpTicks(0);
    }
}
