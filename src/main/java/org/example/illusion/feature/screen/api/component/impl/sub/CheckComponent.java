package org.example.illusion.feature.screen.api.component.impl.sub;

import net.minecraft.client.gui.Gui;
import org.example.illusion.feature.screen.api.component.api.Component;
import org.example.illusion.feature.screen.api.component.impl.ButtonComponent;
import org.example.illusion.feature.screen.api.setting.impl.CheckSetting;
import org.example.illusion.feature.screen.impl.Theme;
import org.example.illusion.util.FontUtils;
import org.example.illusion.util.Wrapper;
import org.lwjgl.opengl.GL11;

public class CheckComponent extends Component {

    private final ButtonComponent parent;
    private final CheckSetting setting;

    private int offset;
    private int x;
    private int y;

    private boolean hovered;

    public CheckComponent(ButtonComponent parent, CheckSetting setting, int offset) {
        this.parent = parent;
        this.setting = setting;
        this.offset = offset;

        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
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
                this.setting.getName(),
                (parent.parent.getX() + 10 + 4) * 2 + 5,
                (parent.parent.getY() + offset + 2) * 2 + 4,
                hovered ? Theme.getMainColor().getRGB() : -1
        );

        GL11.glPopMatrix();

        Gui.drawRect(
                parent.parent.getX() + 3 + 4,
                parent.parent.getY() + offset + 3,
                parent.parent.getX() + 9 + 4,
                parent.parent.getY() + offset + 9,
                -1
        );

        if (this.setting.isEnabled()) {
            Gui.drawRect(
                    parent.parent.getX() + 4 + 4,
                    parent.parent.getY() + offset + 4,
                    parent.parent.getX() + 8 + 4,
                    parent.parent.getY() + offset + 8,
                    Theme.getMainColor().darker().getRGB()
            );
        }
    }

    @Override
    public void setOff(int newOff) {
        offset = newOff;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.hovered = isHovering(mouseX, mouseY, parent.parent, offset);
        this.y = parent.parent.getY() + offset;
        this.x = parent.parent.getX();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isHovering(mouseX, mouseY, parent.parent, offset) && button == 0 && parent.open) {
            setting.setEnabled(!setting.isEnabled());
        }
    }
}