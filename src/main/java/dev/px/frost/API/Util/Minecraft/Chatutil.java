package dev.px.frost.API.Util.Minecraft;

import com.mojang.realmsclient.gui.ChatFormatting;
import dev.px.frost.API.Util.Util;
import dev.px.frost.Client.Frost;
import net.minecraft.util.text.TextComponentString;

public class Chatutil implements Util {

    public static String WATERMARK = ChatFormatting.AQUA + Frost.MODID + ChatFormatting.RESET;

    public static void sendClientSideMessage(String message) {
        if(mc.player != null && mc.world != null) {
            if(mc.ingameGUI != null) {
                mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(message));
            }
        }
    }

}
