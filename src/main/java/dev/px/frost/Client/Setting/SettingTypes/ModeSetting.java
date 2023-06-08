package dev.px.frost.Client.Setting.SettingTypes;

import dev.px.frost.Client.Module.Module;
import dev.px.frost.Client.Setting.Setting;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ModeSetting extends Setting<String> {

    private ArrayList<String> modes;

    public ModeSetting(String name, String value, Module module, ArrayList<String> modes) {
        super(name, value, module);
        this.modes = modes;
    }

    public ModeSetting(String name, String value, Module module, ArrayList<String> modes, Predicate visible) {
        super(name, value, module, visible);
        this.modes = modes;
    }

    public void nextMode() {
        int index = modes.indexOf(getValue());
        index = (index + 1) % modes.size();
        setValue(modes.get(index));
    }

    public boolean is(String name) {
        return name.equalsIgnoreCase(this.getValue());
    }

    public String getValue() {
        return getValue();
    }

    public ArrayList<String> getModes() {
        return modes;
    }
}
