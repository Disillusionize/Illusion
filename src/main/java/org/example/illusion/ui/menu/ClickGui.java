package org.example.illusion.ui.menu;

import net.minecraft.client.gui.GuiScreen;
import org.example.illusion.IllusionClient;
import org.example.illusion.module.Category;
import org.example.illusion.module.impl.ClickGuiModule;
import org.example.illusion.ui.component.Component;
import org.example.illusion.ui.component.Frame;
import org.example.illusion.ui.render.DefaultRenderer;

import java.awt.*;
import java.util.ArrayList;

public class ClickGui extends GuiScreen {

    public static ArrayList<Frame> frames;

    private final GuiScreen parent;

    public ClickGui(GuiScreen parent) {
        this.parent = parent;
        frames = new ArrayList<>();
        int frameX = 5;
        for (Category category : Category.values()) {
            Frame frame = new Frame(category);
            frame.setX(frameX);
            frames.add(frame);
            frameX += frame.getWidth() + 1;
        }
    }

    @Override
    public void initGui() {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        DefaultRenderer.INSTANCE.drawGradientRect(0, 0, this.width, this.height, new Color(0, 0, 0, 30).getRGB(), Theme.getMainColor(30).getRGB());
        for (Frame frame : frames) {
            frame.renderFrame();
            frame.updatePosition(mouseX, mouseY);
            for (Component comp : frame.getComponents()) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }

    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        for (Frame frame : frames) {
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
                frame.setDrag(true);
                frame.dragX = mouseX - frame.getX();
                frame.dragY = mouseY - frame.getY();
            }
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
                frame.setOpen(!frame.isOpen());
            }
            if (frame.isOpen()) {
                if (!frame.getComponents().isEmpty()) {
                    for (Component component : frame.getComponents()) {
                        component.mouseClicked(mouseX, mouseY, mouseButton);
                    }
                }
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        for (Frame frame : frames) {
            if (frame.isOpen() && keyCode != 1) {
                if (!frame.getComponents().isEmpty()) {
                    for (Component component : frame.getComponents()) {
                        component.keyTyped(typedChar, keyCode);
                    }
                }
            }
        }
        if (keyCode == 1) {
            this.mc.displayGuiScreen(null);
        }
    }


    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (Frame frame : frames) {
            frame.setDrag(false);
        }
        for (Frame frame : frames) {
            if (frame.isOpen()) {
                if (!frame.getComponents().isEmpty()) {
                    for (Component component : frame.getComponents()) {
                        component.mouseReleased(mouseX, mouseY, state);
                    }
                }
            }
        }
    }

    public GuiScreen getParent() {
        return parent;
    }

    @Override
    public void onGuiClosed() {
        ClickGuiModule module = (ClickGuiModule) IllusionClient.getInstance().getModuleManager().getElement("ClickGui");
        if (module != null && module.isEnabled()) {
            module.setEnabled(false);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
