package dev.px.frost.Client.Setting.SettingTypes;

import dev.px.frost.Client.Module.Module;
import dev.px.frost.Client.Setting.Setting;

import java.util.function.Predicate;

public class FloatSetting extends Setting<Float> {

    private float max, min;

    public FloatSetting(String name, Float value, Float min, Float max, Module module) {
        super(name, value, min, max, module);
    }

    public FloatSetting(String name, Float value, Float min, Float max, Module module, Predicate visible) {
        super(name, value, min, max, module, visible);
    }

    public Float getValue() {
        java.lang.Number number = super.getValue();
        return number.floatValue();
    }

    public float getMax() {
        return this.max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

}
