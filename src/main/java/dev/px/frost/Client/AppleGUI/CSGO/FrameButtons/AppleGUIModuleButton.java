package dev.px.frost.Client.AppleGUI.CSGO.FrameButtons;

import dev.px.frost.API.Util.Math.Mathutil;
import dev.px.frost.API.Util.Render.Animation.Animation;
import dev.px.frost.API.Util.Render.Animation.Easing;
import dev.px.frost.API.Util.Render.Font.Fontutil;
import dev.px.frost.API.Util.Render.Render2Dutil;
import dev.px.frost.Client.AppleGUI.CSGO.Button;
import dev.px.frost.Client.AppleGUI.CSGO.MasterFrame;
import dev.px.frost.Client.Frost;
import dev.px.frost.Client.Module.Module;
import io.netty.util.internal.MathUtil;

import java.awt.*;
import java.io.IOException;

public class AppleGUIModuleButton {

    public String text;
    public double x;
    public double y;
    public double width;
    public double height;
    public MasterFrame frame;
    public Module module;
    private boolean hovered;
    private double scrollY;
    private double scrollAnimation;
    private double prevY;

    private Animation toggleAnimation = new Animation(250, false, Easing.LINEAR);

    public AppleGUIModuleButton(String text, double x, double y, double width, double height, Module module, MasterFrame frame) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.module = module;
        this.frame = frame;
        this.hovered = false;
        this.scrollY = y;
        this.scrollAnimation = 0;
    }

    public void render(int mouseX, int mouseY) {
        Color toggleColor = module.isToggled() ? new Color(0,136,204, 200) : new Color(208, 204, 204, 230);
        Color hoverColor = module.isToggled() ? new Color(0,136,204, 130) : new Color(255, 255, 255, 130);
        this.toggleAnimation.setState(module.isToggled());

        if(scrollY != y) {
            scrollAnimation = fast((float) scrollAnimation, 1, 15);
            y = interpolate(scrollY, prevY, scrollAnimation);
        }
        Render2Dutil.drawRoundedRect(x, y - 0.8, width, (height + 2), 1, new Color(102,102,102));
        Render2Dutil.drawRoundedRect(x, y, width, height, 1, new Color(151,151,151, 230));
        Render2Dutil.drawRoundedRect(x, y, width * toggleAnimation.getAnimationFactor(), height, module.isToggled() ? 1 : 0, new Color(0,136,204, 200));
        Fontutil.drawStringWithShadow(text, x + 3, y + (height/2 - (Frost.getHandler().cFontRenderer.getFontHeight() / 2)), -1);
        if(this.frame.getSelectedModule() == this) {
            Fontutil.drawString(">", (float) x + (float) width - (float) (Frost.getHandler().cFontRenderer.getStringWidth(">") + 3), (float) y + (float) (height / 2 - (Frost.getHandler().cFontRenderer.getFontHeight() / 2)), new Color(255, 255, 255, 240).getRGB());
        }
    }

    public void onClick(int mouseX, int mouseY, int button) throws IOException {
        if(isHovered(x, y + 1, width, height - 2, mouseX, mouseY)) {
            if(button == 0) {
                module.toggle();
            }
        }
    }

    public void scroll(double scrollY) {
        this.scrollAnimation = 0;
        this.prevY = y;
        this.scrollY += scrollY;
    }

    private boolean isHovered(double x, double y, double width, double height, int mouseX, int mouseY) {
        return mouseX >= x && mouseY >= y && mouseX <= (x + width) && mouseY <= (y + height);
    }

    public double interpolate(double oldValue, double newValue, double interpolationValue) {
        return (oldValue + (newValue - oldValue) * interpolationValue);
    }

    public float fast(float end, float start, float multiple) {
        return (1 - Mathutil.clamp((float) (Mathutil.deltaTime() * multiple), 0, 1)) * end + Mathutil.clamp((float) (Mathutil.deltaTime() * multiple), 0, 1) * start;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public MasterFrame getFrame() {
        return frame;
    }

    public void setFrame(MasterFrame frame) {
        this.frame = frame;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
