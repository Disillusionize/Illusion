package org.example.illusion.feature.screen.api.component.impl.sub;

import net.minecraft.client.gui.Gui;
import org.example.illusion.feature.screen.api.component.api.Component;
import org.example.illusion.feature.screen.api.component.impl.ButtonComponent;
import org.example.illusion.feature.screen.api.setting.impl.ComboSetting;
import org.example.illusion.feature.screen.impl.Theme;
import org.example.illusion.util.FontUtils;
import org.lwjgl.opengl.GL11;

public class ComboComponent extends Component {
    private final ButtonComponent parent;
    private final ComboSetting setting;

    private int x;
    private int y;
    private int offset;

    private double renderWidth;

    private boolean hovered;

    public ComboComponent(ButtonComponent parent, ComboSetting setting, int offset) {
        this.parent = parent;
        this.setting = setting;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
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
                "Mode: " + setting.getValue(),
                (parent.parent.getX() + 7) * 2,
                (parent.parent.getY() + offset + 2) * 2 + 5,
                hovered ? Theme.getMainColor().getRGB() : -1
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
        if (isHovering(mouseX, mouseY, parent.parent, offset) && this.parent.open) {
            if (button == 0) {
                setting.next();
            } else if (button == 1) {
                setting.previous();
            }
        }
    }
}
