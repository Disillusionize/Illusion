package org.example.illusion.feature.module.impl.player;

import io.github.nevalackin.radbus.Listen;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.example.illusion.event.impl.player.MoveEntityEvent;
import org.example.illusion.feature.module.api.Category;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.api.ModuleInfo;
import org.example.illusion.util.Wrapper;

@ModuleInfo(name = "InventoryMove", category = Category.PLAYER)
public class InventoryMove extends Module {
    private final KeyBinding[] affectedBindings = new KeyBinding[]{
            Wrapper.getSettings().keyBindForward,
            Wrapper.getSettings().keyBindBack,
            Wrapper.getSettings().keyBindRight,
            Wrapper.getSettings().keyBindLeft,
            Wrapper.getSettings().keyBindJump
    };

    @Listen
    public void onPreMotion(MoveEntityEvent event) {
        if (Wrapper.getScreen() != null && !(Wrapper.getScreen() instanceof GuiChat)) {
            for (final KeyBinding a : affectedBindings) {
                KeyBinding.setKeyBindState(a.getKeyCode(), GameSettings.isKeyDown(a));
            }
        }
    }
}
