package dev.px.frost.Client.GUI;

import dev.px.frost.API.Module.Type;
import dev.px.frost.Client.GUI.Base.Frame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClickGUI extends GuiScreen {

    public static int WIDTH = 95;
    public static int HEIGHT = 196;
    public static int TITLE = 18;
    private ArrayList<Frame> frames;

    public ClickGUI() {
        this.frames = new ArrayList<>();

        int x = 20;
        for(Type type : Type.values()) {
            this.frames.add(new Frame(type, x, 20, WIDTH, TITLE));
            x += 120; // gap between frames
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        frames.forEach(frame -> frame.render(mouseX, mouseY));
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {

    }

    public static ClickGUI INSTANCE = new ClickGUI();
}
