package org.example.illusion.ui.component.impl;

import net.minecraft.client.gui.Gui;
import org.example.illusion.module.Module;
import org.example.illusion.setting.CheckSetting;
import org.example.illusion.setting.ComboSetting;
import org.example.illusion.setting.Setting;
import org.example.illusion.setting.SliderSetting;
import org.example.illusion.ui.component.Component;
import org.example.illusion.ui.component.Frame;
import org.example.illusion.ui.component.impl.setting.CheckComponent;
import org.example.illusion.ui.component.impl.setting.ComboComponent;
import org.example.illusion.ui.component.impl.setting.KeybindComponent;
import org.example.illusion.ui.component.impl.setting.SliderComponent;
import org.example.illusion.ui.menu.Theme;
import org.example.illusion.util.FontUtils;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class ModuleComponent extends Component {
    private final ArrayList<Component> subcomponents;
    private final int height;
    public Module mod;
    public Frame parent;
    public int offset;
    public boolean open;
    private boolean isHovered;

    public ModuleComponent(Module mod, Frame parent, int offset) {
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList<>();
        this.open = false;
        height = 12;

        int y = offset + height;

        for (Setting setting : mod.getSettings()) {
            if (setting instanceof ComboSetting) {
                this.subcomponents.add(new ComboComponent(this, (ComboSetting) setting, y));
                y += height;
            }

            if (setting instanceof SliderSetting) {
                this.subcomponents.add(new SliderComponent(this, (SliderSetting) setting, y));
                y += height;
            }

            if (setting instanceof CheckSetting) {
                this.subcomponents.add(new CheckComponent(this, (CheckSetting) setting, y));
                y += height;
            }
        }

        this.subcomponents.add(new KeybindComponent(this, mod, y));
    }

    @Override
    public void setOffset(int offset) {
        this.offset = offset;
        int opY = this.offset + height;
        for (Component comp : this.subcomponents) {
            comp.setOffset(opY);
            opY += height;
        }
    }

    @Override
    public void renderComponent() {
        Gui.drawRect(
                parent.getX(),
                this.parent.getY() + this.offset,
                parent.getX() + parent.getWidth(),
                this.parent.getY() + height + this.offset,
                this.isHovered ?
                        (this.mod.isEnabled() ? Theme.getBackColor().darker().getRGB() : Theme.getBackColor().getRGB()) :
                        (this.mod.isEnabled() ? Theme.getBackColor().darker().darker().getRGB() : Theme.getBackColor().darker().getRGB())
        );

        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);

        FontUtils.drawString(
                this.mod.getName(),
                (parent.getX() + 2) * 2,
                (parent.getY() + offset + 2) * 2 + 4,
                this.mod.isEnabled() ? Theme.getMainColor().getRGB() : -1
        );

        if (!this.subcomponents.isEmpty()) {
            FontUtils.drawString(
                    this.open ? "-" : "+",
                    (parent.getX() + parent.getWidth() - 10) * 2,
                    (parent.getY() + offset + 2) * 2 + 4,
                    isHovered ? Theme.getMainColor().getRGB() : -1
            );
        }

        GL11.glPopMatrix();

        if (this.open) {
            if (!this.subcomponents.isEmpty()) {
                for (Component comp : this.subcomponents) {
                    comp.renderComponent();
                }
            }
        }
    }

    @Override
    public int getHeight() {
        if (this.open) {
            return (height * (this.subcomponents.size() + 1));
        }
        return height;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.isHovered = isHovering(mouseX, mouseY, parent, offset);
        if (!this.subcomponents.isEmpty()) {
            for (Component comp : this.subcomponents) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isHovering(mouseX, mouseY, parent, offset) && button == 0) {
            this.mod.toggle();
        }
        if (isHovering(mouseX, mouseY, parent, offset) && button == 1) {
            this.open = !this.open;
            this.parent.refresh();
        }
        for (Component comp : this.subcomponents) {
            comp.mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        for (Component comp : this.subcomponents) {
            comp.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void keyTyped(char typedChar, int key) {
        for (Component comp : this.subcomponents) {
            comp.keyTyped(typedChar, key);
        }
    }
}
