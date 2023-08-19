package dev.px.frost.Client.GUI.Base;

import dev.px.frost.API.Listeners.IComponent;
import dev.px.frost.API.Module.Type;
import dev.px.frost.API.Util.Render.Animation.Animation;
import dev.px.frost.API.Util.Render.Animation.Easing;
import dev.px.frost.API.Util.Render.Render2Dutil;

import java.awt.*;
import java.io.IOException;

public class Frame implements IComponent {

    private float x;
    private float y;
    private float width;
    private float height;
    private Type type;
    private Animation openAnimation = new Animation(300, false, Easing.BOUNCE_OUT);

    public Frame(Type type, int x, int y, int width, int height) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /*
    Plan:
    - Going to have border around frame & module buttons
    - fixed height going to be scrollable
    - going to be bar at bottom of frame to separate frame from buttons
    - Scroll bar

    On open:
    - panes scroll down
    - toggled modules scroll to right
     */

    @Override
    public void render(int mouseX, int mouseY) {
        Color frame = new Color(22, 22, 22, 255);
        Color moduleButtonToggle = new Color(120, 0, 170, 255);
        Color moduleButtonOff = new Color(27, 27,27, 255);
        this.openAnimation.setState(true);
        Render2Dutil.drawRect(getX(), getY(), getWidth(), getHeight() * (float) (openAnimation.getAnimationFactor()), frame);
    }

    @Override
    public void onClick(int mouseX, int mouseY, int button) throws IOException {

    }

    @Override
    public void onRelease(int mouseX, int mouseY, int button) {

    }

    @Override
    public void onType(char typedChar, int keyCode) throws IOException {

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
