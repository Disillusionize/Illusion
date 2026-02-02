package org.example.illusion.ui.component;

import org.example.illusion.ui.component.impl.ModuleComponent;
import org.example.illusion.ui.menu.Theme;
import org.example.illusion.ui.render.DefaultRenderer;

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
        DefaultRenderer.INSTANCE.drawRect(
                x, y,
                parent.parent.getWidth(), COMPONENT_HEIGHT,
                hovered ? Theme.getBackColor().darker().getRGB() : Theme.getBackColor().getRGB()
        );
    }

    protected void drawLabel(String text, int xOffset, int color) {
        DefaultRenderer.INSTANCE.drawScaledString(text, x + xOffset, y + 4.5f, SCALE_FACTOR, color);
    }

}
