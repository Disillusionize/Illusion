package org.example.illusion.feature.module.impl.combat;

import io.github.nevalackin.radbus.Listen;
import net.minecraft.client.settings.KeyBinding;
import org.example.illusion.event.impl.player.PlayerTickEvent;
import org.example.illusion.feature.screen.api.setting.impl.CheckSetting;
import org.example.illusion.feature.module.api.Category;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.api.ModuleInfo;
import org.example.illusion.feature.module.impl.misc.Weapons;
import org.example.illusion.util.Wrapper;
import org.lwjgl.input.Mouse;

@ModuleInfo(name = "AutoClicker", category = Category.COMBAT)
public class AutoClicker extends Module {
    private CheckSetting weaponsOnly = new CheckSetting("Weapons Only", this, true);

    public AutoClicker() {
        addSetting(weaponsOnly);
    }

    private long lastClick = 0;
    private boolean pressed = true;
    private boolean shouldUpdate = false;

    @Listen
    public void onPlayerTick(PlayerTickEvent event) {
        if (Wrapper.getPlayer() == null || Wrapper.getWorld() == null) return;
        if (weaponsOnly.isEnabled() && Weapons.isWeapon(Wrapper.getPlayer().getHeldItem())) return;

        int lmb = Wrapper.getSettings().keyBindAttack.getKeyCode();

        if (Mouse.isButtonDown(0)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClick >= 50) {
                lastClick = currentTime;
                pressed = true;
                shouldUpdate = true;
            } else if (currentTime - lastClick > 15) {
                if (pressed) {
                    pressed = false;
                    shouldUpdate = true;
                }
            }
        }

        if (shouldUpdate) {
            KeyBinding.setKeyBindState(lmb, pressed);
            KeyBinding.onTick(lmb);
            shouldUpdate = false;
        }
    }
}
