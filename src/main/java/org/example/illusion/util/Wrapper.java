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
    private static final Frustum FRUSTUM = new Frustum();

    public static Minecraft getClient() {
        return Minecraft.getMinecraft();
    }

    public static EntityPlayerSP getPlayer() {
        return getClient().thePlayer;
    }

    public static World getWorld() {
        return getClient().theWorld;
    }

    public static List<EntityPlayer> getLoadedPlayers() {
        return getWorld().playerEntities;
    }

    public static boolean isSinglePlayer() {
        return getClient().isIntegratedServerRunning();
    }

    public static boolean isInFirstPerson() {
        return getSettings().thirdPersonView == 0;
    }

    public static void addChatMessage(String message) {
        String prefix = Colors.DARK_GRAY + "[" + Colors.DARK_PURPLE + "Illusion" + Colors.DARK_GRAY + "]" + Colors.GRAY + " ";
        getPlayer().addChatMessage(new ChatComponentText(prefix + message));
    }

    public static FontRenderer getFontRenderer() {
        return getClient().fontRendererObj;
    }

    public static GuiScreen getScreen() {
        return getClient().currentScreen;
    }

    public static GameSettings getSettings() {
        return getClient().gameSettings;
    }

    public static int getScale() {
        return new ScaledResolution(getClient()).getScaleFactor();
    }

    public static RenderManager getRenderManager() {
        return getClient().getRenderManager();
    }

    public static boolean isBBInFrustum(AxisAlignedBB aabb) {
        EntityPlayerSP player = Wrapper.getPlayer();
        FRUSTUM.setPosition(player.posX, player.posY, player.posZ);
        return FRUSTUM.isBoundingBoxInFrustum(aabb);
    }
}
