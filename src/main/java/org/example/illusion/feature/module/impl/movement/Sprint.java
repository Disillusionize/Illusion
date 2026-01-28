package org.example.illusion.feature.module.impl.movement;

import io.github.nevalackin.radbus.Listen;
import net.minecraft.client.settings.KeyBinding;
import org.example.illusion.event.impl.player.MoveEntityEvent;
import org.example.illusion.feature.module.api.Category;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.api.ModuleInfo;
import org.example.illusion.util.Wrapper;

@ModuleInfo(name = "Sprint", category = Category.MOVEMENT)
public class Sprint extends Module {

    // TODO: Use SprintEvent after I figure out why it flags.
    @Listen
    public final void onMoveEntity(MoveEntityEvent event) {
        KeyBinding.setKeyBindState(Wrapper.getSettings().keyBindSprint.getKeyCode(), true);
    }
}
