package dev.px.frost.Client.Setting;

import dev.px.frost.Client.Frost;
import dev.px.frost.Client.Module.Module;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Setting<T> {
    private String name;
    private String description;

    private T value;
    private T min;
    private T max;

    private ArrayList<T> comboBox;
    private Color color;

    private Module module;
    private Predicate<T> visible;

    private List<SubSetting<?>> subSettings = new ArrayList<>();

    public Setting(String name, Module module, T value) {
        this.name = name;
        this.description = "";
        this.module = module;
        this.value = value;
    }

    public Setting(String name, Module module, T value, T min, T max) {
        this.name = name;
        this.description = "";
        this.module = module;
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public Setting(String name, Module module, T value, Predicate<T> visible) {
        this.name = name;
        this.value = value;
        this.module = module;
        this.visible = visible;
    }

    public Setting(String name, Module module, T value, T min, T max, Predicate<T> visible) {
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.module = module;
        this.visible = visible;
    }

    public Setting(String name, Module module, T value, Color color) {
        this.name = name;
        this.value = value;
        this.module = module;
        this.color = color;
    }

    public Setting(String name, Module module, T value, Color color, Predicate<T> predicate) {
        this.name = name;
        this.value = value;
        this.module = module;
        this.color = color;
        this.visible = predicate;
    }

    public Setting(String name, Module module, T value, T... values) {
        this.name = name;
        this.description = "";
        this.module = module;
        this.value = value;
        this.comboBox = new ArrayList<>(Arrays.asList(values));
    }

    public void addSubSetting(SubSetting<?> subSetting) {
        this.subSettings.add(subSetting);
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

    public ArrayList<T> getComboBox() {
        return comboBox;
    }

    public void setComboBox(ArrayList<T> comboBox) {
        this.comboBox = comboBox;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<SubSetting<?>> getSubSettings() {
        return subSettings;
    }

    public void setSubSettings(List<SubSetting<?>> subSettings) {
        this.subSettings = subSettings;
    }

    public boolean isVisible() {
        if (this.visible == null) {
            return true;
        }
        return this.visible.test(this.getValue());
    }
}
