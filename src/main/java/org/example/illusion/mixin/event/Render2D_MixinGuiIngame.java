package org.example.illusion.mixin.event;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.Render2DEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiIngame.class)
public class Render2D_MixinGuiIngame {
    @Inject(
            method = "renderGameOverlay",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/GlStateManager;enableBlend()V",
                    shift = At.Shift.AFTER,
                    ordinal = 0
            )
    )
    private void illusion$publishRender2D(float partialTicks, CallbackInfo ci, @Local ScaledResolution scaledResolution) {
        IllusionClient.getInstance().getEventBus().publish(new Render2DEvent(partialTicks, scaledResolution));
    }
}
