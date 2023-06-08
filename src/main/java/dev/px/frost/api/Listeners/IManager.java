package dev.px.frost.api.Listeners;

public interface IManager {

    void load();

    void unload();

    void onUpdate();

    void onTick();

    void onThread();

}
