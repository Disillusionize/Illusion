package org.example.illusion.mixin.core;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import org.example.illusion.core.Initializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class Initialize_MixinMinecraft {

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
