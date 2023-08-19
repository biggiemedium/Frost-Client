package dev.px.frost.Client.Manager;

import dev.px.frost.Client.Module.Module;
import dev.px.frost.Client.Module.Modules.Client.ClickGUIModule;
import dev.px.frost.Client.Module.Modules.Client.CustomFontModule;
import dev.px.frost.Client.Module.Modules.Combat.CrystalAura;
import dev.px.frost.Client.Module.Modules.Combat.SkidAura;
import dev.px.frost.Client.Module.Modules.Misc.TestModule;
import dev.px.frost.API.Manager.Manager;
import dev.px.frost.API.Module.Type;

import java.util.ArrayList;

public class ModuleManager extends Manager {

    private ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        Add(new TestModule());

        // Client
        Add(new ClickGUIModule());
        Add(new CustomFontModule());

        // Combat
        Add(new CrystalAura());
        Add(new SkidAura());
    }

    private void Add(Module module) {
        this.modules.add(module);
    }

    public Module getModuleByName(String name) {
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public ArrayList<Module> getModuleByType(Type type) {
        ArrayList<Module> module = new ArrayList<>();
        modules.stream().forEach(m -> {
            if(m.getType() == type) {
                module.add(m);
            }
        });
        return module;
    }

    public <T extends Module> Module getModule(Class<T> clazz) {
        return modules.stream().filter(module -> module.getClass() == clazz).map(module -> module).findFirst().orElse(null);
    }

    public boolean isModuleToggled(Module module) {
        Module mod = modules.stream().filter(m -> m == module).findFirst().orElse(null);
        return mod != null && mod.isToggled();
    }

    public ArrayList<Module> getModules() {
        return modules;
    }
}
