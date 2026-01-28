package org.example.illusion.util;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.example.illusion.feature.screen.impl.Theme;

import java.awt.Color;

import static org.lwjgl.opengl.GL11.*;
import static net.minecraft.client.renderer.GlStateManager.*;

public class RenderUtils {
    private static final Tessellator tessellator = Tessellator.getInstance();
    private static final WorldRenderer renderer = tessellator.getWorldRenderer();

    public static void drawBox(float[] vertices, float inset, float thickness, int color) {
        float left = vertices[0] + inset;
        float leftInner = left + thickness;

        float right = vertices[2] - inset;
        float rightInner = right - thickness;

        float top = vertices[1] + inset;
        float topInner = top + thickness;

        float bottom = vertices[3] - inset;
        float bottomInner = bottom - thickness;

        disableTexture2D();
        enableBlend();
        blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GLUtils.color(color);

        renderer.begin(GL_QUADS, DefaultVertexFormats.POSITION);

        submitQuad(left, top, leftInner, bottom);
        submitQuad(rightInner, top, right, bottom);
        submitQuad(leftInner, top, rightInner, topInner);
        submitQuad(leftInner, bottomInner, rightInner, bottom);

        tessellator.draw();

        disableBlend();
        enableTexture2D();
    }

    public static void drawIllusionBackground(float height, float width) {
        float[] vertices = new float[] { 0, 0, height, width };
        drawGradientRect(vertices, new Color(0, 0,0, 20).getRGB(), Theme.getMainColor(20).getRGB());
    }

    public static void drawGradientRect(float[] vertices, int startColor, int endColor) {
        float r = (startColor >> 16 & 0xFF) / 255.0f;
        float g = (startColor >> 8 & 0xFF) / 255.0f;
        float b = (startColor & 0xFF) / 255.0f;
        float a = (startColor >> 24 & 0xFF) / 255.0f;

        float r2 = (endColor >> 16 & 0xFF) / 255.0f;
        float g2 = (endColor >> 8 & 0xFF) / 255.0f;
        float b2 = (endColor & 0xFF) / 255.0f;
        float a2 = (endColor >> 24 & 0xFF) / 255.0f;

        disableTexture2D();
        enableBlend();
        shadeModel(GL_SMOOTH);
        blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        renderer.begin(GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        renderer.pos(vertices[0], vertices[1], 0).color(r, g, b, a).endVertex();
        renderer.pos(vertices[0], vertices[3], 0).color(r2, g2, b2, a2).endVertex();
        renderer.pos(vertices[2], vertices[3], 0).color(r2, g2, b2, a2).endVertex();
        renderer.pos(vertices[2], vertices[1], 0).color(r, g, b, a).endVertex();
        tessellator.draw();

        disableBlend();
        shadeModel(GL_FLAT);
        enableTexture2D();
    }

    private static void submitQuad(float x1, float y1, float x2, float y2) {
        renderer.pos(x1, y1, 0).endVertex();
        renderer.pos(x1, y2, 0).endVertex();
        renderer.pos(x2, y2, 0).endVertex();
        renderer.pos(x2, y1, 0).endVertex();
    }
}