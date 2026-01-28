package org.example.illusion.mixin;

import net.minecraft.client.Minecraft;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.player.PlayerTickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class Minecraft_PlayerTick {
    @Inject(method = "runTick", at = @At("RETURN"))
    private void illusion$publishPlayerTick(CallbackInfo ci) {
        IllusionClient.getInstance().getEventBus().publish(new PlayerTickEvent());
    }
}
