package org.example.illusion.util;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import static org.lwjgl.opengl.GL11.*;

public final class RenderUtils {
    private static final Tessellator TESSELLATOR = Tessellator.getInstance();
    private static final WorldRenderer RENDERER = TESSELLATOR.getWorldRenderer();

    private RenderUtils() {}

    public static void drawRect(int left, int top, int right, int bottom, int color) {
        int x1 = Math.min(left, right);
        int x2 = Math.max(left, right);
        int y1 = Math.min(top, bottom);
        int y2 = Math.max(top, bottom);

        float[] rgba = unpackColor(color);

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);

        RENDERER.begin(GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        RENDERER.pos(x1, y2, 0).color(rgba[0], rgba[1], rgba[2], rgba[3]).endVertex();
        RENDERER.pos(x2, y2, 0).color(rgba[0], rgba[1], rgba[2], rgba[3]).endVertex();
        RENDERER.pos(x2, y1, 0).color(rgba[0], rgba[1], rgba[2], rgba[3]).endVertex();
        RENDERER.pos(x1, y1, 0).color(rgba[0], rgba[1], rgba[2], rgba[3]).endVertex();
        TESSELLATOR.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
        int x1 = Math.min(left, right);
        int x2 = Math.max(left, right);
        int y1 = Math.min(top, bottom);
        int y2 = Math.max(top, bottom);

        float[] star = unpackColor(startColor);
        float[] endc = unpackColor(endColor);

        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
        GlStateManager.shadeModel(GL_SMOOTH);

        RENDERER.begin(GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        RENDERER.pos(x2, y1, 0).color(endc[0], endc[1], endc[2], endc[3]).endVertex();
        RENDERER.pos(x1, y1, 0).color(endc[0], endc[1], endc[2], endc[3]).endVertex();
        RENDERER.pos(x1, y2, 0).color(star[0], star[1], star[2], star[3]).endVertex();
        RENDERER.pos(x2, y2, 0).color(star[0], star[1], star[2], star[3]).endVertex();
        TESSELLATOR.draw();

        GlStateManager.shadeModel(GL_FLAT);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    private static float[] unpackColor(int color) {
        float a = (color >> 24 & 255) / 255.0F;
        float r = (color >> 16 & 255) / 255.0F;
        float g = (color >> 8 & 255) / 255.0F;
        float b = (color & 255) / 255.0F;
        return new float[]{r, g, b, a};
    }
}
