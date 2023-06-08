package dev.px.frost.Client.Module.Modules.Misc;

import dev.px.frost.Client.Module.Module;
import dev.px.frost.api.Module.Type;
import org.lwjgl.input.Keyboard;

@Module.ModuleInterface(name = "Test", type = Type.MISC, keyBind = Keyboard.KEY_RSHIFT)
public class TestModule extends Module {

    public TestModule() {
    }

    @Override
    public void onEnable() {
        System.out.println("TOGGLED");
    }

}
