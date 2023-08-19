package dev.px.frost.Client.Manager;

import dev.px.frost.Client.Frost;
import dev.px.frost.Client.Module.Module;
import dev.px.frost.API.Manager.Manager;
import dev.px.frost.API.Util.Util;
import me.zero.alpine.fork.listener.Listenable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class EventManager extends Manager implements Listenable, Util {

    public EventManager() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            for(Module m : Frost.getHandler().getModuleManager().getModules()) {
                if(m.getKeyBind() == 0) return;
                if(m.getKeyBind() == Keyboard.getEventKey()) {
                    m.toggle();
                }
            }
        }

    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        for(Manager manager : Frost.getHandler().managers) {
            if(mc.player != null && mc.world != null) {
                try {
                    manager.onTick();
                } catch (Exception e) {
                }
            }
        }
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        for(Manager manager : Frost.getHandler().managers) {
            if(mc.player != null && mc.world != null) {
                try {
                    manager.onUpdate();
                } catch (Exception e) {
                }
            }
        }
    }

}
