package org.example.illusion.mixin;

import net.minecraft.client.renderer.EntityRenderer;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.render.Render3DEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRenderer_Render3D {
    @Inject(
            method = "renderWorldPass",
            at = @At(
                    value = "CONSTANT",
                    args = "stringValue=hand"
            )
    )
    private void illusion$publishRender3D(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci) {
        IllusionClient.getInstance().getEventBus().publish(new Render3DEvent(partialTicks));
    }
}
