package org.example.illusion.ui.component;

import org.example.illusion.setting.ComboSetting;
import org.example.illusion.ui.menu.Theme;


public class ComboComponent extends Widget {
    private final ComboSetting setting;

    public ComboComponent(ModuleComponent parent, ComboSetting setting, int offset) {
        super(parent, offset);
        this.setting = setting;
    }

    @Override
    public void renderComponent() {
        drawBackground();
        drawLabel("Mode: " + setting.getValue(), 7, hovered ? Theme.getMainColor().getRGB() : -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (hovered && parent.open) {
            if (button == 0) setting.next();
            else if (button == 1) setting.previous();
        }
    }
}

