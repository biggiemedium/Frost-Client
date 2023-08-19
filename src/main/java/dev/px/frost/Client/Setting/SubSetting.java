package dev.px.frost.Client.Setting;

import dev.px.frost.Client.Module.Module;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class SubSetting<T> {

    private String name;
    private String description;

    private T value;
    private T min;
    private T max;

    private ArrayList<T> comboBox;
    private Color color;

    private Module module;
    private Setting parent;
    private Predicate<T> visible;

    public SubSetting(String name, T value, Setting parent) {
        this.name = name;
        this.description = "";
        this.value = value;
        this.parent = parent;
        this.module = parent.getModule();
        parent.addSubSetting(this);
    }

    public SubSetting(String name, T value, T min, T max, Setting parent) {
        this.name = name;
        this.description = "";
        this.value = value;
        this.min = min;
        this.max = max;
        this.parent = parent;
        this.module = parent.getModule();
        parent.addSubSetting(this);
    }

    public SubSetting(String name, T value, Setting parent, Predicate<T> visible) {
        this.name = name;
        this.value = value;
        this.visible = visible;
        this.parent = parent;
        this.module = parent.getModule();
        parent.addSubSetting(this);
    }

    public SubSetting(String name, T value, T min, T max, Setting parent, Predicate<T> visible) {
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.visible = visible;
        this.parent = parent;
        this.module = parent.getModule();
        parent.addSubSetting(this);
    }

    public SubSetting(String name, T value, Color color, Setting parent) {
        this.name = name;
        this.value = value;
        this.color = color;
        this.parent = parent;
        this.module = parent.getModule();
        parent.addSubSetting(this);
    }

    public SubSetting(String name, T value, Color color, Setting parent, Predicate<T> predicate) {
        this.name = name;
        this.value = value;
        this.color = color;
        this.visible = predicate;
        this.parent = parent;
        this.module = parent.getModule();
        parent.addSubSetting(this);
    }

    public SubSetting(String name, T value, T[] values, Setting parent) {
        this.name = name;
        this.description = "";
        this.value = value;
        this.comboBox = new ArrayList<>(Arrays.asList(values));
        this.parent = parent;
        this.module = parent.getModule();
        parent.addSubSetting(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        //MinecraftForge.EVENT_BUS.post(new ValueChangeEvent(this));
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Predicate<T> getVisible() {
        return visible;
    }

    public void setVisible(Predicate<T> visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        if (this.visible == null) {
            return true;
        }
        return this.visible.test(this.getValue());
    }

}
