package dev.px.frost.Client.Module.Modules.Client;

import dev.px.frost.Client.GUI.ClickGUI;
import dev.px.frost.Client.Module.Module;
import dev.px.frost.API.Module.Type;
import dev.px.frost.Client.Setting.Setting;
import org.lwjgl.input.Keyboard;

import java.awt.*;

@Module.ModuleInterface(name = "ClickGUI", type = Type.CLIENT, keyBind = Keyboard.KEY_RCONTROL)
public class ClickGUIModule extends Module {

    public ClickGUIModule() {

    }

    @Override
    public void onEnable() {
        System.out.println("TOGGLE GUI 1");
        mc.displayGuiScreen(ClickGUI.INSTANCE);
        System.out.println("TOGGLE GUI");
    }
}
