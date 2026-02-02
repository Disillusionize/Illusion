package org.example.illusion.mixin.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.WorldLoadEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class WorldLoad_MixinMinecraft {
    @Inject(
            method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/WorldClient;spawnEntityInWorld(Lnet/minecraft/entity/Entity;)Z",
                    shift = At.Shift.AFTER
            )
    )
    private void illusion$publishWorldLoad(WorldClient worldClientIn, String loadingMessage, CallbackInfo ci) {
        IllusionClient.getInstance().getEventBus().publish(new WorldLoadEvent());
    }
}
