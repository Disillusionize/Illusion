package org.example.illusion.ui.component.impl.setting;

import net.minecraft.client.gui.Gui;
import org.example.illusion.setting.SliderSetting;
import org.example.illusion.ui.component.impl.ModuleComponent;
import org.example.illusion.ui.component.Widget;
import org.example.illusion.ui.menu.Theme;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SliderComponent extends Widget {
    private final SliderSetting setting;
    private boolean dragging;
    private double renderWidth;

    public SliderComponent(ModuleComponent parent, SliderSetting setting, int offset) {
        super(parent, offset);
        this.setting = setting;
    }

    private static float roundToPlace(float value) {
        return new BigDecimal(value).setScale(1, RoundingMode.HALF_UP).floatValue();
    }

    @Override
    public void renderComponent() {
        drawBackground();

        Gui.drawRect(x + 2, y, x + (int) renderWidth, y + COMPONENT_HEIGHT,
                hovered ? Theme.getMainColor().darker().getRGB() : Theme.getMainColor().getRGB());

        drawLabel(setting.getName() + ": " + setting.getValue(), 15,
                hovered ? Theme.getMainColor().getRGB() : -1);
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        super.updateComponent(mouseX, mouseY);

        float min = setting.getMin();
        float max = setting.getMax();
        renderWidth = SLIDER_WIDTH * (setting.getValue() - min) / (max - min);

        if (dragging) {
            float diff = Math.min(SLIDER_WIDTH, Math.max(0, mouseX - x));
            float newValue = diff == 0 ? min : roundToPlace(((diff / SLIDER_WIDTH) * (max - min) + min));
            setting.setValue(newValue);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (hovered && button == 0 && parent.open) dragging = true;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int button) {
        dragging = false; // commit value here if needed
    }
}

