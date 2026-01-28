package org.example.illusion.feature.module.impl.misc;

import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import org.example.illusion.IllusionClient;
import org.example.illusion.feature.screen.api.setting.impl.CheckSetting;
import org.example.illusion.feature.module.api.Category;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.api.ModuleInfo;

@ModuleInfo(name = "Weapons", category = Category.MISC)
public class Weapons extends Module {
    private CheckSetting swords = new CheckSetting("Swords", this, true);
    private CheckSetting axes = new CheckSetting("Axes", this, false);
    private CheckSetting sticks = new CheckSetting("Sticks", this, true);
    private CheckSetting fists = new CheckSetting("Fists", this, true);

    public Weapons() {
        addSetting(swords);
        addSetting(axes);
        addSetting(sticks);
        addSetting(fists);
    }

    public static boolean isWeapon(ItemStack stack) {
        Weapons module = (Weapons) IllusionClient.getInstance().getModuleManager().getElement("Weapons");
        if (module == null) return false;
        if (!module.isEnabled()) return false;

        if (stack == null) {
            return module.fists.isEnabled();
        }

        return (module.swords.isEnabled() && stack.getItem() instanceof ItemSword) ||
                (module.axes.isEnabled() && stack.getItem() instanceof ItemAxe) ||
                (module.sticks.isEnabled() && stack.getItem() == Items.stick);
    }
}
