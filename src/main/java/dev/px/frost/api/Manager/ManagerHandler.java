package dev.px.frost.API.Manager;

import dev.px.frost.API.Util.Render.Font.FontRender;
import dev.px.frost.Client.Manager.EventManager;
import dev.px.frost.Client.Manager.ModuleManager;
import dev.px.frost.Client.Manager.SettingManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ManagerHandler {

    private ModuleManager moduleManager;
    private EventManager eventManager;
    private SettingManager settingManager;
    public FontRender cFontRenderer;

    public ArrayList<Manager> managers = new ArrayList<>(Arrays.asList(
            new SettingManager(),
            new ModuleManager()
    ));

    public ManagerHandler() {
        settingManager = new SettingManager(); // must come before modulemanager
        moduleManager = new ModuleManager();
        eventManager = new EventManager();
        cFontRenderer = new FontRender(new Font("Verdana", Font.PLAIN, 18), true,true);
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

    public EventManager getEventManager() {
        return eventManager;
    }

    public SettingManager getSettingManager() { return settingManager; }
}
