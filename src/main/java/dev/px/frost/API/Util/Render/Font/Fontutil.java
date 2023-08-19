package dev.px.frost.API.Util.Render.Font;

import dev.px.frost.API.Util.Util;
import dev.px.frost.Client.Frost;

public class Fontutil implements Util {

    public static float getFontHeight(boolean customFont) {
        return customFont ? Frost.getHandler().cFontRenderer.getFontHeight() : mc.fontRenderer.FONT_HEIGHT;
    }

    public static float drawString(String text, float x, float y, int color) {
        return drawString(text, (int) x, (int) y, color);
    }

    public static float drawStringWithShadow(String text, double x, double y, int color) {
        return Frost.getHandler().cFontRenderer.drawStringWithShadow(text, x, y, color);
    }

    public static float drawString(String text, int x, int y, int color) {
        return Frost.getHandler().cFontRenderer.drawString(text, x, y, color);
    }
}
