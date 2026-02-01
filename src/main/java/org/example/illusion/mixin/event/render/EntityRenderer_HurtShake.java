package org.example.illusion.mixin.event.render;

import net.minecraft.client.renderer.EntityRenderer;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.render.HurtShakeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRenderer_HurtShake {
    @Inject(method = "hurtCameraEffect", at = @At("HEAD"), cancellable = true)
    private void illusion$publishHurtShake(float partialTicks, CallbackInfo ci) {
        HurtShakeEvent event = new HurtShakeEvent();
        IllusionClient.getInstance().getEventBus().publish(event);
        if (event.isCancelled()) ci.cancel();
    }
}
