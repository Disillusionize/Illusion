package org.example.illusion.util;

public class FontUtils {
    public static void drawString(String text, float x, float y) {
        drawString(text, x, y, -1);
    }

    public static void drawString(String text, float x, float y, int color) {
        Wrapper.getFontRenderer().drawStringWithShadow(text, x, y, color);
    }
}
