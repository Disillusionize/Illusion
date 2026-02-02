package org.example.illusion.ui.component;

import net.minecraft.client.gui.Gui;
import org.example.illusion.IllusionClient;
import org.example.illusion.module.Category;
import org.example.illusion.module.Module;
import org.example.illusion.ui.menu.Theme;
import org.example.illusion.util.FontUtils;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class Frame {

    public ArrayList<Component> components;
    public Category category;
    public int dragX;
    public int dragY;
    private boolean open;
    private final int width;
    private int y;
    private int x;
    private final int barHeight;
    private boolean isDragging;

    public Frame(Category category) {
        this.components = new ArrayList<>();
        this.category = category;
        this.width = 88;
        this.x = 5;
        this.y = 5;
        this.barHeight = 13;
        this.dragX = 0;
        this.open = false;
        this.isDragging = false;
        int tY = this.barHeight;
        for (Module mod : IllusionClient.getInstance().getModuleManager().getElements(category)) {
            ModuleComponent modButton = new ModuleComponent(mod, this, tY);
            this.components.add(modButton);
            tY += 12;
        }
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setDrag(boolean drag) {
        this.isDragging = drag;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void renderFrame() {
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.barHeight, Theme.getMainColor().getRGB());
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        FontUtils.drawString(this.category.name(), (this.x + 2) * 2 + 5, (this.y + 2.5f) * 2 + 5);
        FontUtils.drawString(this.open ? "-" : "+", (this.x + this.width - 10) * 2 + 5, (this.y + 2.5f) * 2 + 5);
        GL11.glPopMatrix();
        if (this.open) {
            if (!this.components.isEmpty()) {
                for (Component component : components) {
                    component.renderComponent();
                }
            }
        }
    }

    public void refresh() {
        int off = this.barHeight;
        for (Component comp : components) {
            comp.setOffset(off);
            off += comp.getHeight();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getWidth() {
        return width;
    }

    public void updatePosition(int mouseX, int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - dragX);
            this.setY(mouseY - dragY);
        }
    }

    public boolean isWithinHeader(int x, int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }

}
