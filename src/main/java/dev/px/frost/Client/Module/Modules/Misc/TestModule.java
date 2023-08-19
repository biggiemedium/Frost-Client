package dev.px.frost.Client.Module.Modules.Misc;

import dev.px.frost.Client.AppleGUI.AppleGUI;
import dev.px.frost.Client.GUI.ClickGUI;
import dev.px.frost.Client.Module.Module;
import dev.px.frost.API.Module.Type;
import dev.px.frost.Client.Setting.Setting;
import dev.px.frost.Client.Setting.SubSetting;
import org.lwjgl.input.Keyboard;

@Module.ModuleInterface(name = "Test", type = Type.MISC, keyBind = Keyboard.KEY_RSHIFT)
public class TestModule extends Module {

    public TestModule() {
    }

    public Setting<Boolean> rotate = new Setting<Boolean>("Rotate", this, true);
    public SubSetting<Boolean> counterRotate = new SubSetting<>("CounterRotate", true, rotate);

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new AppleGUI());
        System.out.println("TOGGLE");
    }

}
