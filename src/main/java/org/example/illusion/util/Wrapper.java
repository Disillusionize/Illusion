package org.example.illusion.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.List;

public class Wrapper {
    public static Minecraft getClient() {
        return Minecraft.getMinecraft();
    }

    public static EntityPlayerSP getPlayer() {
        return getClient().thePlayer;
    }

    public static void addChatMessage(String message) {
        getPlayer().addChatMessage(new ChatComponentText(message));
    }

    public static FontRenderer getFontRenderer() {
        return getClient().fontRendererObj;
    }
}
