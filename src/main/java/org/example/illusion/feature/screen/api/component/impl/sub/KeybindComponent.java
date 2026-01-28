package org.example.illusion.feature.screen.api.component.impl.sub;

import net.minecraft.client.gui.Gui;
import org.example.illusion.feature.screen.api.component.api.Component;
import org.example.illusion.feature.screen.api.component.impl.ButtonComponent;
import org.example.illusion.feature.screen.impl.Theme;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.impl.misc.ClickGuiModule;
import org.example.illusion.util.FontUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class KeybindComponent extends Component {
    private final ButtonComponent parent;
    private final Module module;

    private int x;
    private int y;
    private int offset;

    private double renderWidth;

    private boolean binding;
    private boolean hovered;

    public KeybindComponent(ButtonComponent parent, Module module, int offset) {
        this.parent = parent;
        this.module = module;
        this.offset = offset;

        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
    }

    @Override
    public void setOff(int newOff) {
        offset = newOff;
    }

    @Override
    public void renderComponent() {
        Gui.drawRect(
                parent.parent.getX() + 2,
                parent.parent.getY() + offset,
                parent.parent.getX() + parent.parent.getWidth(),
                parent.parent.getY() + offset + 12,
                this.hovered ? Theme.getBackColor().darker().getRGB() : Theme.getBackColor().getRGB()
        );

        Gui.drawRect(
                parent.parent.getX(),
                parent.parent.getY() + offset,
                parent.parent.getX() + 2,
                parent.parent.getY() + offset + 12,
                Theme.getBackColor().getRGB()
        );

        GL11.glPushMatrix();
        GL11.glScalef(0.5f,0.5f, 0.5f);

        FontUtils.drawString(
                binding ? "Press a key..." : ("Key: " + Keyboard.getKeyName(module.getBind())),
                (parent.parent.getX() + 7) * 2,
                (parent.parent.getY() + offset + 2) * 2 + 5
        );

        GL11.glPopMatrix();
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.hovered = isHovering(mouseX, mouseY, parent.parent, offset);
        this.y = parent.parent.getY() + offset;
        this.x = parent.parent.getX();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isHovering(mouseX, mouseY, parent.parent, offset) && button == 0 && this.parent.open) {
            this.binding = !this.binding;
        }
    }

    @Override
    public void keyTyped(char typedChar, int key) {
        if (this.binding) {
            if (key == Keyboard.KEY_ESCAPE) {
                if (module instanceof ClickGuiModule) {
                    key = Keyboard.KEY_RSHIFT;
                } else {
                    key = Keyboard.KEY_NONE;
                }
            }

            this.module.setBind(key);
            this.binding = false;
        }
    }
}