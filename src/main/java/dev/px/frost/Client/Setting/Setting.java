package dev.px.frost.Client.Setting;

import dev.px.frost.Client.Module.Module;

import java.util.function.Predicate;

public class Setting<T> {

    private String name;

    private T value;
    private T min;
    private T max;

    // parent
    private Module module;

    private Predicate<T> visibility;

    public Setting(String name, T value, Module module) {
        this.name = name;
        this.value = value;
        this.module = module;
    }

    public Setting(String name, T value, Module module, Predicate<T> visibility) {
        this.name = name;
        this.value = value;
        this.module = module;
        this.visibility = visibility;
    }

    public Setting(String name, T value, T min, T max, Module module) {
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.module = module;
    }

    public Setting(String name, T value, T min, T max, Module module, Predicate<T> visibility) {
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.module = module;
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Predicate<T> getVisibility() {
        return visibility;
    }

    public void setVisibility(Predicate<T> visibility) {
        this.visibility = visibility;
    }
}
