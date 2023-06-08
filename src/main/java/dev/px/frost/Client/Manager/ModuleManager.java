package dev.px.frost.Client.Manager;

import com.google.common.reflect.ClassPath;
import dev.px.frost.Client.Module.Module;
import dev.px.frost.Client.Module.Modules.Misc.TestModule;
import dev.px.frost.api.Manager.Manager;
import dev.px.frost.api.Module.Type;
import net.minecraft.launchwrapper.Launch;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ModuleManager extends Manager {

    private ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {

    }

    @Override
    public void load() {
        Add(new TestModule());

        modules.sort(Comparator.comparing(Module::getName));
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
