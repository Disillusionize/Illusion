package org.example.illusion.mixin;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.entity.RayTraceEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRenderer_RayTraceEntity {
    @Unique
    private RayTraceEntityEvent illusion$rayTraceEntityEvent;

    @Inject(method = "getMouseOver", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getPositionEyes(F)Lnet/minecraft/util/Vec3;", shift = At.Shift.AFTER))
    private void illusion$publishRayTraceEntity(float partialTicks, CallbackInfo ci) {
        IllusionClient.getInstance().getEventBus().publish(illusion$rayTraceEntityEvent = new RayTraceEntityEvent());
    }

    @Redirect(method = "getMouseOver", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getCollisionBorderSize()F"))
    private float illusion$multiplier(Entity instance) {
        return instance.getCollisionBorderSize() * illusion$rayTraceEntityEvent.getBorderMultiplier();
    }

    @ModifyConstant(method = "getMouseOver", constant = @Constant(doubleValue = 3.0D))
    private double illusion$setReach(double constant) {
        return illusion$rayTraceEntityEvent.getReach();
    }
}
