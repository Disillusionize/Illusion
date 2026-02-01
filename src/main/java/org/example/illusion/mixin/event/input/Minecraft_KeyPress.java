package org.example.illusion.mixin.event.input;

import net.minecraft.client.Minecraft;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.input.KeyPressEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class Minecraft_KeyPress {

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V"))
    private void illusion$publishKeyPress(CallbackInfo ci) {
        IllusionClient.getInstance().getEventBus().publish(new KeyPressEvent());
    }

}
