package org.example.illusion.mixin.event.player;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.player.MoveEntityEvent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSP_MoveEntity extends Entity {
    public EntityPlayerSP_MoveEntity(World worldIn) {
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
