package org.example.illusion.mixin.event;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.MoveEntityEvent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayerSP.class)
public abstract class MoveEntity_MixinEntityPlayerSP extends Entity {
    public MoveEntity_MixinEntityPlayerSP(World worldIn) {
        super(worldIn);
    }

    @Override
    public void moveEntity(double x, double y, double z) {
        final MoveEntityEvent moveEntityEvent = new MoveEntityEvent(x, y, z);
        IllusionClient.getInstance().getEventBus().publish(moveEntityEvent);
        if (moveEntityEvent.isCancelled()) return;
        super.moveEntity(moveEntityEvent.getX(), moveEntityEvent.getY(), moveEntityEvent.getZ());
    }
}
