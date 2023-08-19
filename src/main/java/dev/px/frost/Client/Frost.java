package dev.px.frost.Client;

import dev.px.frost.API.Manager.ManagerHandler;
import me.zero.alpine.fork.bus.EventBus;
import me.zero.alpine.fork.bus.EventManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.lwjgl.opengl.Display;

@Mod(modid = Frost.MODID, name = "Frost Client", version = Frost.VERSION)
public class Frost {

    public static final String MODID = "frost";
    public static final String VERSION = "1.0";
    public static final String NAME = "Frost Client";

    @Mod.Instance
    public Frost INSTANCE;

    public static final EventBus EVENT_BUS = new EventManager();

    public static ManagerHandler handler;

    public Frost() {
        INSTANCE = this;
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        handler = new ManagerHandler();
        handler.load();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Display.setTitle(NAME + " | Version: " + VERSION);
    }

    public static ManagerHandler getHandler() {
        return handler;
    }
}
