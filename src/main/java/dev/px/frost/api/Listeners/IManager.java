package dev.px.frost.API.Listeners;

public interface IManager {

    void load();

    void unload();

    void onUpdate();

    void onTick();

    void onThread();

}
