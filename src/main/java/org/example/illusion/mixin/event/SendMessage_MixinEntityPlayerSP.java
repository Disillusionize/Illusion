package org.example.illusion.mixin.event;

import net.minecraft.client.entity.EntityPlayerSP;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.SendMessageEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class SendMessage_MixinEntityPlayerSP {
    @Unique
    private SendMessageEvent illusion$sendMessageEvent;

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void illusion$publishSendMessage(String message, CallbackInfo ci) {
        IllusionClient.getInstance().getEventBus().publish(illusion$sendMessageEvent = new SendMessageEvent(message));

        if (illusion$sendMessageEvent.isCancelled()) ci.cancel();
    }

    @ModifyVariable(method = "sendChatMessage", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private String illusion$setMessage(String value) {
        return illusion$sendMessageEvent.getMessage();
    }
}
