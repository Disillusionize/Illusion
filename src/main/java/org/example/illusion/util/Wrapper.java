package org.example.illusion.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Wrapper {
    public static Minecraft getClient() {
        return Minecraft.getMinecraft();
    }

    public static FontRenderer getFontRenderer() {
        return getClient().fontRendererObj;
    }
}
