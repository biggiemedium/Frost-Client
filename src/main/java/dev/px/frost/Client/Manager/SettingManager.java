package dev.px.frost.Client.Manager;

import dev.px.frost.API.Manager.Manager;
import dev.px.frost.Client.Module.Module;
import dev.px.frost.Client.Setting.Setting;

import java.util.ArrayList;
import java.util.List;

public class SettingManager extends Manager {

    public ArrayList<Setting> values;

    public SettingManager() {
        this.values = new ArrayList<>();
    }

    public Setting Add(Setting value) {
        this.values.add(value);
        return value;
    }

    public List<Setting> getValueForMod(Module module) {
        List<Setting> valueList = new ArrayList<>();
        for(Setting<?> v : values) {
            if(v.getModule() == module) {
                valueList.add(v);
            }
        }
        return valueList;
    }

    public ArrayList<Setting> getValues() {
        return this.values;
    }

}
