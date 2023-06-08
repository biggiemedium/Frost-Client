package dev.px.frost.api.Manager;

import dev.px.frost.Client.Manager.EventManager;
import dev.px.frost.Client.Manager.ModuleManager;
import dev.px.frost.Client.Manager.SettingManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerHandler {

    private ModuleManager moduleManager;
    private SettingManager settingManager;
    private EventManager eventManager;

    public ArrayList<Manager> managers = new ArrayList<>(Arrays.asList(
            new SettingManager(),
            new ModuleManager()
    ));

    public ManagerHandler() {
        settingManager = new SettingManager();
        moduleManager = new ModuleManager();
        eventManager = new EventManager();
    }

    public void load() {
        managers.forEach(manager -> {
            manager.load();
        });
    }

    public void unLoad() {
        managers.forEach(Manager::unload);
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public SettingManager getSettingManager() {
        return settingManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
