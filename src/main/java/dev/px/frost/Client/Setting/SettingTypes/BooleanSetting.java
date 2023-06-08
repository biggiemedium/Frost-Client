package dev.px.frost.Client.Setting.SettingTypes;

import dev.px.frost.Client.Module.Module;
import dev.px.frost.Client.Setting.Setting;

import java.util.function.Predicate;

public class BooleanSetting extends Setting<Boolean> {

    public BooleanSetting(String name, Boolean value, Module module) {
        super(name, value, module);
    }

    public BooleanSetting(String name, Boolean value, Module module, Predicate visible) {
        super(name, value, module, visible);
    }
}
