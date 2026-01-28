package org.example.illusion.util;

import java.awt.*;

// Actually made by Syz
// Shocking, right?
public class ColorUtils {
    public static Color gradient(Color color, int y) {
        final double percent = Math.sin(System.currentTimeMillis() / 600.0D + y * 0.06D) * 0.5D + 0.5D;
        return getColor(color, percent);
    }

    private static Color getColor(Color color, double percent) {
        final double inverse_percent = 1.0 - percent;
        final int redPart = (int) (color.getRed() * percent + 255 * inverse_percent);
        final int greenPart = (int) (color.getGreen() * percent + 255 * inverse_percent);
        final int bluePart = (int) (color.getBlue() * percent + 255 * inverse_percent);
        return new Color(redPart, greenPart, bluePart);
    }
}
