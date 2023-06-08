package dev.px.frost.Client.Module;

import dev.px.frost.Client.Frost;
import dev.px.frost.api.Module.Type;
import me.zero.alpine.fork.listener.Listenable;
import net.minecraftforge.common.MinecraftForge;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Module implements Listenable {

    private String name;
    private String description;
    private int keyBind;
    private boolean toggled;
    private boolean drawn;
    private Type type;

    public Module() {
        this.name = getModule().name();
        this.description = getModule().description();
        this.keyBind = getModule().keyBind();
        this.toggled = getModule().toggled();
        this.drawn = getModule().drawn();
        this.type = getModule().type();
    }

    protected ModuleInterface getModule() {
        return getClass().getAnnotation(ModuleInterface.class);
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;

        if(this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public void toggle() {
        this.toggled = !this.toggled;

        if(this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }

    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
        Frost.EVENT_BUS.subscribe(this);
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        Frost.EVENT_BUS.unsubscribe(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKeyBind() {
        return keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    public boolean isToggled() {
        return toggled;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface ModuleInterface {

        String name();

        String description() default "";

        int keyBind() default -1;

        boolean toggled() default false;

        Type type();

        boolean drawn() default false;

    }
}
