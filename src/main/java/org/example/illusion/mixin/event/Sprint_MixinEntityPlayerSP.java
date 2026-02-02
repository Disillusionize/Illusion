package org.example.illusion.mixin.event;

import net.minecraft.client.entity.EntityPlayerSP;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.SprintEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityPlayerSP.class)
public class Sprint_MixinEntityPlayerSP {
    @Redirect(method = "onUpdateWalkingPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isSprinting()Z", ordinal = 0))
    private boolean illusion$publishSprint(EntityPlayerSP player) {
        SprintEvent event = new SprintEvent(player.isSprinting());
        IllusionClient.getInstance().getEventBus().publish(event);
        return event.isSprinting();
    }
}
