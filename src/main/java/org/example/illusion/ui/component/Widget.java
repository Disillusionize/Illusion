package org.example.illusion.ui.component;

import net.minecraft.client.gui.Gui;
import org.example.illusion.ui.component.impl.ModuleComponent;
import org.example.illusion.ui.menu.Theme;
import org.example.illusion.util.FontUtils;
import org.lwjgl.opengl.GL11;

public abstract class Widget extends Component {
    protected static final int COMPONENT_HEIGHT = 12;
    protected static final int SLIDER_WIDTH = 88;
    protected static final float SCALE_FACTOR = 0.5f;

    protected final ModuleComponent parent;
    protected int x, y, offset;
    protected boolean hovered;

    public Widget(ModuleComponent parent, int offset) {
        this.parent = parent;
        this.offset = offset;
        updatePosition();
    }

    protected void updatePosition() {
        this.x = parent.parent.getX();
        this.y = parent.parent.getY() + offset;
    }

    @Override
    public void setOffset(int newOff) {
        this.offset = newOff;
        updatePosition();
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.hovered = isHovering(mouseX, mouseY, parent.parent, offset);
        updatePosition();
    }

    protected void drawBackground() {
        Gui.drawRect(
                x + 2, y,
                x + parent.parent.getWidth(), y + COMPONENT_HEIGHT,
                hovered ? Theme.getBackColor().darker().getRGB() : Theme.getBackColor().getRGB()
        );
//        Gui.drawRect(x, y, x + 2, y + COMPONENT_HEIGHT, Theme.getBackColor().getRGB());
    }

    protected void drawLabel(String text, int xOffset, int color) {
        GL11.glPushMatrix();
        GL11.glScalef(SCALE_FACTOR, SCALE_FACTOR, SCALE_FACTOR);
        FontUtils.drawString(text, (x + xOffset) * 2, (y + 2) * 2 + 5, color);
        GL11.glPopMatrix();
    }

}
