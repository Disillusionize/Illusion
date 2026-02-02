package org.example.illusion.ui.component.impl.setting;

import org.example.illusion.setting.CheckSetting;
import org.example.illusion.ui.component.impl.ModuleComponent;
import org.example.illusion.ui.component.Widget;
import org.example.illusion.ui.menu.Theme;
import org.example.illusion.ui.render.DefaultRenderer;

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

        DefaultRenderer.INSTANCE.drawRect(x + 7, y + 3, 6, 6, -1);

        if (setting.isEnabled()) {
            DefaultRenderer.INSTANCE.drawRect(x + 8, y + 4, 4, 4, Theme.getMainColor().darker().getRGB());
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (hovered && button == 0 && parent.open) {
            setting.setEnabled(!setting.isEnabled());
        }
    }
}
