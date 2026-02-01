package org.example.illusion.mixin.event.player;

import net.minecraft.client.entity.EntityPlayerSP;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.player.SprintEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityPlayerSP.class)
public class EntityPlayerSP_Sprint {
    @Redirect(method = "onUpdateWalkingPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isSprinting()Z", ordinal = 0))
    private boolean illusion$publishSprint(EntityPlayerSP player) {
        SprintEvent event = new SprintEvent(player.isSprinting());
        IllusionClient.getInstance().getEventBus().publish(event);
        return event.isSprinting();
    }
}
