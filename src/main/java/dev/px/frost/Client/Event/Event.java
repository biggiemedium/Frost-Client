package dev.px.frost.Client.Event;

import dev.px.frost.Client.Frost;
import dev.px.frost.API.Util.Util;
import me.zero.alpine.fork.event.type.Cancellable;
import me.zero.alpine.fork.listener.Listenable;

public class Event extends Cancellable implements Listenable, Util {

    private Era era = Era.PRE;
    private float partialTicks;

    public Event() {
        partialTicks = mc.getRenderPartialTicks();
    }

    public Event(Era era) {
        Frost.EVENT_BUS.post(this);
        partialTicks = mc.getRenderPartialTicks();
        this.era = era;
    }

    public Era getEra() {
        return era;
    }

    public void setEra(Era era) {
        this.era = era;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public boolean isPre() {
        if(era == null) {
            return false;
        }

        return era == Era.PRE;
    }

    public boolean isPost() {
        if(era == null) {
            return false;
        }

        return era == Era.POST;
    }

    public enum Era {
        PRE,
        POST
    }
}
