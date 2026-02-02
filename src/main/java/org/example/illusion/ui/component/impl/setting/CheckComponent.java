package org.example.illusion.ui.component.impl.setting;

import net.minecraft.client.gui.Gui;
import org.example.illusion.setting.CheckSetting;
import org.example.illusion.ui.component.impl.ModuleComponent;
import org.example.illusion.ui.component.Widget;
import org.example.illusion.ui.menu.Theme;

public class CheckComponent extends Widget {
    private final CheckSetting setting;

    public CheckComponent(ModuleComponent parent, CheckSetting setting, int offset) {
        super(parent, offset);
        this.setting = setting;
    }

    @Override
    public void renderComponent() {
        drawBackground();
        drawLabel(setting.getName(), 20, hovered ? Theme.getMainColor().getRGB() : -1);

        Gui.drawRect(x + 7, y + 3, x + 13, y + 9, -1);

        if (setting.isEnabled()) {
            Gui.drawRect(x + 8, y + 4, x + 12, y + 8, Theme.getMainColor().darker().getRGB());
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (hovered && button == 0 && parent.open) {
            setting.setEnabled(!setting.isEnabled());
        }
    }
}
