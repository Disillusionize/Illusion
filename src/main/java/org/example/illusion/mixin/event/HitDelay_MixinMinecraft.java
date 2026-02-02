package org.example.illusion.mixin.event;

import net.minecraft.client.Minecraft;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.HitDelayEvent;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Debug(export = true)
@Mixin(Minecraft.class)
public class HitDelay_MixinMinecraft {
    @Shadow
    private int leftClickCounter;

    @Redirect(
            method = "clickMouse",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Minecraft;leftClickCounter:I",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void illusion$publishHitDelay(Minecraft instance, int value) {
        HitDelayEvent event = new HitDelayEvent();
        IllusionClient.getInstance().getEventBus().publish(event);
        leftClickCounter = event.getHitDelay();
    }
}
