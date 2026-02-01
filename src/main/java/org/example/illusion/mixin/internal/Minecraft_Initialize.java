package org.example.illusion.mixin.internal;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import org.example.illusion.internal.Initializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class Minecraft_Initialize {

    @Inject(
            method = "startGame", at = @At("TAIL")
    )
    private void initialize(CallbackInfo ci) {
        FabricLoader.getInstance().invokeEntrypoints(
                Initializer.KEY,
                Initializer.class,
                Initializer::initialize
        );
    }

}
