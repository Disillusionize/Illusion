package org.example.illusion.util;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

public class GLUtils {
    private static final FloatBuffer windowPosition = GLAllocation.createDirectFloatBuffer(4);
    private static final IntBuffer viewport = GLAllocation.createDirectIntBuffer(16);
    private static final FloatBuffer modelMatrix = GLAllocation.createDirectFloatBuffer(16);
    private static final FloatBuffer projectionMatrix = GLAllocation.createDirectFloatBuffer(16);
    private static final float[] BUFFER = new float[3];

    public static void color(int color) {
        GlStateManager.color(
                (color >> 16 & 0xFF) / 255.0f,
                (color >> 8 & 0xFF) / 255.0f,
                (color & 0xFF) / 255.0f,
                (color >> 24 & 0xFF) / 255.0f
        );
    }

    public static float[] project2D(float x, float y, float z, int scaleFactor) {
        glGetFloat(GL_MODELVIEW_MATRIX, modelMatrix);
        glGetFloat(GL_PROJECTION_MATRIX, projectionMatrix);
        glGetInteger(GL_VIEWPORT, viewport);

        if (GLU.gluProject(x, y, z, modelMatrix, projectionMatrix, viewport, windowPosition)) {
            BUFFER[0] = windowPosition.get(0) / scaleFactor;
            BUFFER[1] = (Display.getHeight() - windowPosition.get(1)) / scaleFactor;
            BUFFER[2] = windowPosition.get(2);
            return BUFFER;
        }

        return null;
    }
}
