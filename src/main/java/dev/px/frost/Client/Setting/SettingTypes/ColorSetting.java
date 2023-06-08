package dev.px.frost.Client.Setting.SettingTypes;

import dev.px.frost.Client.Module.Module;
import dev.px.frost.Client.Setting.Setting;

import java.awt.*;
import java.util.function.Predicate;

public class ColorSetting extends Setting<Color> {

    private boolean rainbow;

    public ColorSetting(String name, Color value, Module module) {
        super(name, value, module);
    }

    public ColorSetting(String name, Color value, Module module, Predicate visible) {
        super(name, value, module, visible);
    }

    public boolean isRainbow() {
        return rainbow;
    }

    public void setRainbow(boolean rainbow) {
        this.rainbow = rainbow;
    }
}
