package dev.px.frost.Client.AppleGUI;

import dev.px.frost.Client.AppleGUI.CSGO.MasterFrame;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class AppleGUI extends GuiScreen {

    private MasterFrame masterFrame;

    private int x, y;
    private int width, height;

    public AppleGUI() {
        this.width = 400;
        this.height = 250;
        this.masterFrame = new MasterFrame(width, height);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        scroll(mouseX, mouseY);
        masterFrame.render(mouseX, mouseY);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        masterFrame.onClick(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        masterFrame.onRelease(mouseX, mouseY, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == Keyboard.KEY_ESCAPE) {
            mc.player.closeScreen();
        }
    }

    private void scroll(int mouseX, int mouseY) {
        if(Mouse.hasWheel()) {
            masterFrame.onScroll(mouseX, mouseY);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
