package dev.px.frost.Client.Setting.SettingTypes;

import dev.px.frost.Client.Module.Module;
import dev.px.frost.Client.Setting.Setting;

import java.util.function.Predicate;

public class IntegerSetting extends Setting<Integer> {

    private int max, min;

    public IntegerSetting(String name, Integer value, Integer min, Integer max, Module module) {
        super(name, value, min, max, module);
    }

    public IntegerSetting(String name, Integer value, Integer min, Integer max, Module module, Predicate visible) {
        super(name, value, min, max, module, visible);
    }

    public Integer getValue() {
        java.lang.Number number = super.getValue();
        return number.intValue();
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
