package org.example.illusion.feature.screen.api.component.impl.sub;

import net.minecraft.client.gui.Gui;
import org.example.illusion.feature.screen.api.component.api.Component;
import org.example.illusion.feature.screen.api.component.impl.ButtonComponent;
import org.example.illusion.feature.screen.api.setting.impl.SliderSetting;
import org.example.illusion.feature.screen.impl.Theme;
import org.example.illusion.util.FontUtils;
import org.lwjgl.opengl.GL11;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SliderComponent extends Component {
    private final ButtonComponent parent;
    private final SliderSetting setting;

    private int x;
    private int y;
    private int offset;

    private double renderWidth;

    private boolean hovered;
    private boolean dragging = false;

    public SliderComponent(ButtonComponent parent, SliderSetting setting, int offset) {
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

        final int drag = (int)(setting.getValue() / setting.getMax() * this.parent.parent.getWidth());

        Gui.drawRect(
                parent.parent.getX() + 2,
                parent.parent.getY() + offset,
                parent.parent.getX() + (int) renderWidth,
                parent.parent.getY() + offset + 12,
                hovered ? Theme.getMainColor().darker().getRGB() : Theme.getMainColor().getRGB()
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
                setting.getName() + ": " + setting.getValue() ,
                (parent.parent.getX()* 2 + 15),
                (parent.parent.getY() + offset + 2) * 2 + 5
        );

        GL11.glPopMatrix();
    }

    @Override
    public void setOff(int newOff) {
        offset = newOff;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.hovered = isMouseOnButtonD(mouseX, mouseY) || isMouseOnButtonI(mouseX, mouseY);
        this.y = parent.parent.getY() + offset;
        this.x = parent.parent.getX();

        float diff = Math.min(88, Math.max(0, mouseX - this.x));

        float min = setting.getMin();
        float max = setting.getMax();

        renderWidth = (88) * (setting.getValue() - min) / (max - min);

        if (dragging) {
            if (diff == 0) {
                setting.setValue(setting.getMin());
            }
            else {
                float newValue = roundToPlace(((diff / 88) * (max - min) + min));
                setting.setValue(newValue);
            }
        }
    }

    private static float roundToPlace(float value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if(isMouseOnButtonD(mouseX, mouseY) && button == 0 && this.parent.open) {
            dragging = true;
        }
        if(isMouseOnButtonI(mouseX, mouseY) && button == 0 && this.parent.open) {
            dragging = true;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        dragging = false;
    }

    public boolean isMouseOnButtonD(int x, int y) {
        return x > this.x && x < this.x + (parent.parent.getWidth() / 2 + 1) && y > this.y && y < this.y + 12;
    }

    public boolean isMouseOnButtonI(int x, int y) {
        return x > this.x + parent.parent.getWidth() / 2 && x < this.x + parent.parent.getWidth() && y > this.y && y < this.y + 12;
    }
}
