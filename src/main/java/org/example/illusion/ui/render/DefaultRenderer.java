package org.example.illusion.ui.render;

import org.example.illusion.util.FontUtils;
import org.example.illusion.util.RenderUtils;
import org.lwjgl.opengl.GL11;

public class DefaultRenderer {
    public static DefaultRenderer INSTANCE = new DefaultRenderer();

    public void drawRect(int x, int y, int width, int height, int color) {
        RenderUtils.drawRect(x, y, x + width, y + height, color);
    }

    public void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
        RenderUtils.drawGradientRect(left, top, right, bottom, startColor, endColor);
    }

    public void drawScaledString(String text, float x, float y, float scale, int color) {
        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
        FontUtils.drawString(text, x / scale, y / scale, color);
        GL11.glPopMatrix();
    }
}
