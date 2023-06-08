package dev.px.frost.Client.Manager;

import dev.px.frost.Client.Module.Module;
import dev.px.frost.Client.Setting.Setting;
import dev.px.frost.api.Manager.Manager;

import java.util.ArrayList;
import java.util.HashMap;

public class SettingManager extends Manager {

    private ArrayList<Setting> settings = new ArrayList<>();

    public SettingManager() {

    }

    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }

    public ArrayList<Setting> getSettingsByMod(Module mod) {
        ArrayList<Setting> out = new ArrayList<>();
        for (Setting s : getSettings()) {
            if (s.getModule().equals(mod)) {
                out.add(s);
            }
        }
        return out.isEmpty() ? null : out;
    }

    public Setting getSettingByMod(Module module, String name) {
        return settings.stream().filter(m -> m.getModule() == module).filter(n -> n.getModule().getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public ArrayList<Setting> getSettings() {
        return settings;
    }
}
