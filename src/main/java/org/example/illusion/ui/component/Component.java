package org.example.illusion.ui.component;

public abstract class Component {
    public abstract void renderComponent();

    public abstract void updateComponent(int mouseX, int mouseY);

    public abstract void mouseClicked(int mouseX, int mouseY, int button);

    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
    }

    public void keyTyped(char typedChar, int key) {
    }

    public abstract void setOffset(int offset);

    public int getHeight() {
        return 0;
    }

    public final boolean isHovering(int x, int y, Frame frame, int offset) {
        return x > frame.getX() &&
                x < frame.getX() + frame.getWidth() &&
                y > frame.getY() + offset &&
                y < frame.getY() + 12 + offset;
    }
}
