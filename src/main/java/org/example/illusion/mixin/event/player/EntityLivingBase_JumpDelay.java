package org.example.illusion.mixin.event.player;

import net.minecraft.entity.EntityLivingBase;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.player.JumpDelayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityLivingBase.class)
public class EntityLivingBase_JumpDelay {

    @ModifyConstant(
            method = "onLivingUpdate",
            constant = @Constant(intValue = 10)
    )
    private int illusion$publishJumpDelay(int constant) {
        JumpDelayEvent event = new JumpDelayEvent();
        IllusionClient.getInstance().getEventBus().publish(event);
        return event.getJumpTicks();
    }

}
