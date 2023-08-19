package dev.px.frost.Client.AppleGUI.CSGO;

import dev.px.frost.API.Listeners.IComponent;
import dev.px.frost.API.Listeners.IScrollable;
import dev.px.frost.API.Module.Type;
import dev.px.frost.API.Util.Math.Dimension;
import dev.px.frost.API.Util.Render.Animation.Animation;
import dev.px.frost.API.Util.Render.Animation.Easing;
import dev.px.frost.API.Util.Render.Font.Fontutil;
import dev.px.frost.API.Util.Render.Render2Dutil;
import dev.px.frost.API.Util.Util;
import dev.px.frost.Client.AppleGUI.CSGO.FrameButtons.AppleGUIModuleButton;
import dev.px.frost.Client.Frost;
import dev.px.frost.Client.Module.Module;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.*;

/*
#000000	(0,0,0) | Black
#666666	(102,102,102) | Dark grey
#979797	(151,151,151) | Grey (Primary background)
#eeeeee	(238,238,238) | White (Buttons)
#0088cc	(0,136,204) | Blue

GUI Reference
https://www.google.com/search?q=apple+settings+menu+gui&tbm=isch&ved=2ahUKEwjK0t2atbKAAxUQCVkFHa7jBzoQ2-cCegQIABAA&oq=apple+settings+menu+gui&gs_lcp=CgNpbWcQAzoECCMQJzoGCAAQCBAeOgQIABAeUJMHWP4LYI0NaABwAHgAgAFgiAHjApIBATWYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=cjzEZMrRE5CS5NoPrsef0AM&bih=831&biw=1751#imgrc=er-BDpzEjM3quM

 */
public class MasterFrame implements IComponent, Util {

    private double x, y, width, height;
    private Dimension<Double> searchBar;
    private Dimension<Double> outlineSize;
    private Dimension<Double> buttonPos;

    private ArrayList<AppleGUIModuleButton> moduleButtons;
    private ArrayList<Button> otherButtons;

    private boolean searchInFocus, dragging, scrolling;
    private AppleGUIModuleButton selectedModule;

    private int prevScrollProgress, scrollProgress, scrollY;
    private int scrollMaxHeight = 50;

    private Animation openAnimation = new Animation(250, false, Easing.LINEAR);
    private Animation searchAnimation = new Animation(115, false, Easing.EXPO_OUT);
    private Animation scrollAnimation = new Animation(250, false, Easing.LINEAR);

    public MasterFrame(double width, double height) {
        this.width = width;
        this.height = height;
        this.x = 150;
        this.y = 75;
        this.outlineSize = new Dimension<>(x - 1, y - 0.8, width + 2, height + 2);
        this.searchBar = new Dimension<Double>(x + 5, y + 32, 90d, 12d);
        this.buttonPos = new Dimension<>(0d, 0d, 0d, 0d);
        this.searchInFocus = false;
        this.moduleButtons = new ArrayList<>();
        this.otherButtons = new ArrayList<>();
        assignValues();
    }

    private void assignValues() {
        double yOffset = searchBar.getY().intValue() + 45;
        for(Module m : Frost.getHandler().getModuleManager().getModules()) {
            this.moduleButtons.add(new AppleGUIModuleButton(m.getName(), x + 4, yOffset, 115 - 10, Frost.getHandler().cFontRenderer.getHeight() + 6, m, this));
            buttonPos.setX(x + 4);
            buttonPos.setY(searchBar.getY() + 45);
            buttonPos.setWidth(115d - 10d);
            buttonPos.setHeight((double) Frost.getHandler().cFontRenderer.getHeight() + 6);
            yOffset += Frost.getHandler().cFontRenderer.getHeight() + 6;
        }
    }

    @Override
    public void render(int mouseX, int mouseY) {
        //Render2Dutil.drawRoundedRect(toInt(x), toInt(y), toInt(width), toInt(height * openAnimation.getAnimationFactor()), 15, new Color(238,238,238).getRGB(), 255);
        //Render2Dutil.drawRoundedRect(toInt(x - 1), toInt(y - 1), toInt(width + 1), toInt(height + 1 * openAnimation.getAnimationFactor()), 15, new Color(151,151,151).getRGB(), 255);

        this.openAnimation.setState(true);
        Render2Dutil.drawRoundedRect(x - 1, y - 0.8, width + 2, (height + 2) * openAnimation.getAnimationFactor(), 10, new Color(102, 102, 102));
        Render2Dutil.drawRoundedRect(x, y, width, height * openAnimation.getAnimationFactor(), 10, new Color(238, 238, 238));

        // left side grey bar
        Render2Dutil.drawRoundedRect(x, y, 115, (height) * openAnimation.getAnimationFactor(), 10, new Color(151, 151, 151));
        Render2Dutil.drawRect((float) x + 10, (float) y, (float) 115 - 10, (float) height * (float) openAnimation.getAnimationFactor(), new Color(151, 151, 151));

        // Top left circles
        Render2Dutil.drawCircle((int) x + 5, (int) y + 5, 3f * (float) openAnimation.getAnimationFactor(), new Color(255, 19, 19, 200).getRGB());
        Render2Dutil.drawCircle((int) x + 13, (int) y + 5, 3f * (float) openAnimation.getAnimationFactor(), new Color(241, 203, 10, 200).getRGB());
        // Render2Dutil.drawCircle((int) x + 20, (int) y + 5, 3f * (float) openAnimation.getAnimationFactor(), new Color(82, 217, 0, 200).getRGB());

        // Search bar
        this.searchAnimation.setState(searchInFocus);
        Render2Dutil.drawRoundedRect(searchBar.x, searchBar.y, 90, 12, 6, new Color(102, 102, 102));
        Render2Dutil.drawRoundedRect(searchBar.x, searchBar.y, searchAnimation.getState() ? 100 * searchAnimation.getAnimationFactor() : 90, 12, 6, new Color(102, 102, 102));
        Fontutil.drawStringWithShadow(searchInFocus ? "" : "Search", searchBar.x.floatValue() + 1, searchBar.y.floatValue() + (Frost.getHandler().cFontRenderer.getFontHeight() / 2), new Color(255, 255, 255, 230).getRGB());
        // User settings

        // Title bar
        Render2Dutil.drawRect((float) x + 114, (float) y + 15, (float) width - 113, 2, new Color(151, 151, 151));
        Fontutil.drawString(selectedModule == null ? "Frost Client" : selectedModule.getText(), (float) x + 118, (float) (y + 13) - (Frost.getHandler().cFontRenderer.getFontHeight()), new Color(151, 151, 151).getRGB());
        //Fontutil.drawString("Frost Client!", searchBar.x.floatValue(), searchBar.y.floatValue() + 3 - (searchBar.height.floatValue()), new Color(102,102,102).getRGB());

        // Others
        Fontutil.drawString(mc.player.getName(), (float) searchBar.getX().floatValue(), (float) searchBar.y.floatValue() + 3 - (searchBar.height.floatValue() + 3), new Color(102, 102, 102).getRGB());
        Fontutil.drawString("Frost User", (float) searchBar.getX().floatValue(), (float) searchBar.y.floatValue() + 3 - (searchBar.height.floatValue() + 3) - Frost.getHandler().cFontRenderer.getFontHeight(), new Color(102, 102, 102).getRGB());


        // Module buttons
        Collections.sort(moduleButtons, new Comparator<AppleGUIModuleButton>() {
            @Override
            public int compare(AppleGUIModuleButton o1, AppleGUIModuleButton o2) {
                return o1.getText().compareTo(o2.getText());
            }
        });

        this.moduleButtons.forEach(button -> {
            if(!(button.y > buttonPos.y + 45)) {
                button.render(mouseX, mouseY);
            }
        });
    }

    @Override
    public void onClick(int mouseX, int mouseY, int button) throws IOException {
        if(isHovered(searchBar.x, searchBar.y, searchBar.width, 12, mouseX, mouseY)) {
            if(button == 0) {
                this.searchInFocus = true;
                mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, mc.gameSettings.getSoundLevel(SoundCategory.MASTER), 1.0f);
            }
        }
        if(this.searchInFocus) {
            if(!isHovered(searchBar.x, searchBar.y,searchBar.width, 12, mouseX, mouseY)) {
                if(button == 1 || button == 0 || button == 2) {
                    this.searchInFocus = false;
                    mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, mc.gameSettings.getSoundLevel(SoundCategory.MASTER), 1.0f);
                }
            }
        }

        if(isHovered(x + 5, y + 5, 5, 5, mouseX, mouseY)) {
            if(button == 0) {
                mc.player.closeScreen();
                mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, mc.gameSettings.getSoundLevel(SoundCategory.MASTER), 1.0f);
            }
        }
        if(isHovered(x + 13, y + 5, 5, 5, mouseX, mouseY)) {
            if(button == 0) {
                mc.player.closeScreen();
                mc.player.playSound(SoundEvents.UI_BUTTON_CLICK, mc.gameSettings.getSoundLevel(SoundCategory.MASTER), 1.0f);
            }
        }

        for(AppleGUIModuleButton b : this.moduleButtons) {
            b.onClick(mouseX, mouseY, button);
            if(isHovered(b.getX(), b.getY(), b.getWidth(), b.getHeight(), mouseX, mouseY)) {
                if (button == 1) {
                    this.selectedModule = b;
                }
            }
        }
    }

    @Override
    public void onRelease(int mouseX, int mouseY, int button) {

    }

    @Override
    public void onType(char typedChar, int keyCode) throws IOException {

    }

    public void onScroll(int mouseX, int mouseY) {
        this.scrollY = searchBar.getY().intValue() + 45;
        this.moduleButtons.forEach(button -> {
            button.scroll(Mouse.getDWheel() * 0.05);
        });
    }

    private <T extends Number> int toInt(T value) {
        return value.intValue();
    }

    private boolean isHovered(double x, double y, double width, double height, int mouseX, int mouseY) {
        return mouseX >= x && mouseY >= y && mouseX <= (x + width) && mouseY <= (y + height);
    }

    private double getScrollProgress() {
        return prevScrollProgress + (scrollProgress - prevScrollProgress) * mc.getRenderPartialTicks();
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

    public Dimension<Double> getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(Dimension<Double> searchBar) {
        this.searchBar = searchBar;
    }

    public Dimension<Double> getOutlineSize() {
        return outlineSize;
    }

    public void setOutlineSize(Dimension<Double> outlineSize) {
        this.outlineSize = outlineSize;
    }

    public ArrayList<AppleGUIModuleButton> getModuleButtons() {
        return moduleButtons;
    }

    public void setModuleButtons(ArrayList<AppleGUIModuleButton> moduleButtons) {
        this.moduleButtons = moduleButtons;
    }

    public boolean isSearchInFocus() {
        return searchInFocus;
    }

    public void setSearchInFocus(boolean searchInFocus) {
        this.searchInFocus = searchInFocus;
    }

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public boolean isScrolling() {
        return scrolling;
    }

    public void setScrolling(boolean scrolling) {
        this.scrolling = scrolling;
    }

    public AppleGUIModuleButton getSelectedModule() {
        return selectedModule;
    }

    public Animation getOpenAnimation() {
        return openAnimation;
    }

    public void setOpenAnimation(Animation openAnimation) {
        this.openAnimation = openAnimation;
    }

    public Animation getSearchAnimation() {
        return searchAnimation;
    }

    public void setSearchAnimation(Animation searchAnimation) {
        this.searchAnimation = searchAnimation;
    }

    public Animation getScrollAnimation() {
        return scrollAnimation;
    }

    public void setScrollAnimation(Animation scrollAnimation) {
        this.scrollAnimation = scrollAnimation;
    }
}
