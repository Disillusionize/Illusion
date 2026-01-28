package org.example.illusion.feature.screen.api.component.api;

public abstract class Component {
    public void renderComponent() {}

    public void updateComponent(int mouseX, int mouseY) {}

    public void mouseClicked(int mouseX, int mouseY, int button) {}

    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {}

    public int getParentHeight() {
        return 0;
    }

    public void keyTyped(char typedChar, int key) {}

    public void setOff(int newOff) {}

    public int getHeight() {
        return 0;
    }

    public boolean isHovering(int x, int y, Frame frame, int offset) {
        return x > frame.getX() &&
                x < frame.getX() + frame.getWidth() &&
                y > frame.getY() + offset &&
                y < frame.getY() + 12 + offset;
    }
}
