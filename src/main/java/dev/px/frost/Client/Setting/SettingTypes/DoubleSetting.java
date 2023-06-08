package dev.px.frost.Client.Setting.SettingTypes;

import dev.px.frost.Client.Module.Module;
import dev.px.frost.Client.Setting.Setting;

import java.util.function.Predicate;

public class DoubleSetting extends Setting<Double> {

    private double max, min;

    public DoubleSetting(String name, Double value, Double min, Double max, Module module) {
        super(name, value, min, max, module);
    }

    public DoubleSetting(String name, Double value, Double min, Double max, Module module, Predicate visible) {
        super(name, value, min, max, module, visible);
    }

    public Double getValue() {
        java.lang.Number number = super.getValue();
        return number.doubleValue();
    }

    public double getMax() {
        return this.max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

}
