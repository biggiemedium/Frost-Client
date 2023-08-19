package dev.px.frost.API.Util.Render;

import dev.px.frost.API.Util.Util;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

import java.awt.*;

public class Render2Dutil implements Util {

    public static Tessellator tessellator = Tessellator.getInstance();
    public static BufferBuilder bufferBuilder = tessellator.getBuffer();
    private static ScaledResolution resolution = new ScaledResolution(mc);

    public static void drawRect(float x, float y, float w, float h, int color) {
        float alpha = (color >> 24 & 0xFF) / 255.0f;
        float red = (color >> 16 & 0xFF) / 255.0f;
        float green = (color >> 8 & 0xFF) / 255.0f;
        float blue = (color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(x, h, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(w, h, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(w, y, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(x, y, 0.0).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawRect(float x, float y, float width, float height, Color color) {
        glPushMatrix();
        glDisable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glShadeModel(GL_SMOOTH);
        glBegin(GL_QUADS);
        glColor4f((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255, (float) color.getAlpha() / 255);
        glVertex2f(x, y);
        glVertex2f(x, y + height);
        glVertex2f(x + width, y + height);
        glVertex2f(x + width, y);
        glColor4f(0, 0, 0, 1);
        glEnd();
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
        glPopMatrix();
    }

    public static void drawGradient(double x, double y, double x2, double y2, int col1, int col2) {
        float f = (col1 >> 24 & 0xFF) / 255.0f;
        float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        float f4 = (col1 & 0xFF) / 255.0f;
        float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
        GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
    }

    public static void drawRoundedRect(int x, int y, int width, int height, int radius, int color, int p_CustomAlpha) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        float newX = Math.abs(x + radius);
        float newY = Math.abs(y + radius);
        float newX1 = Math.abs(width - radius);
        float newY1 = Math.abs(height - radius);

        drawRect(newX, newY, newX1, newY1, color);
        drawRect(x, newY, newX, newY1, color);
        drawRect(newX1, newY, width, newY1, color);
        drawRect(newX, y, newX1, newY, color);
        drawRect(newX, newY1, newX1, height, color);

        // Draw curves
        drawQuarterCircle((int) newX, (int) newY, radius, 0, color, p_CustomAlpha);
        drawQuarterCircle((int) newX1, (int) newY, radius, 1, color, p_CustomAlpha);
        drawQuarterCircle((int) newX, (int) newY1, radius, 2, color, p_CustomAlpha);
        drawQuarterCircle((int) newX1, (int) newY1, radius, 3, color, p_CustomAlpha);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    // Modes:
    // 0 = Top Left
    // 1 = Top Right
    // 2 = Bottom Left
    // 3 = Bottom Right
    public static void drawQuarterCircle(int x, int y, int radius, int mode, int color, int p_CustomAlpha) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4d((color >> 16 & 0xff) / 255F, (color >> 8 & 0xff) / 255F, (color & 0xff) / 255F, p_CustomAlpha);
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glVertex2d(x, y);
        if (mode == 0) {
            for (int i = 0; i <= 90; i++) {
                GL11.glVertex2d(x + (Math.sin((i * 3.141526D / 180)) * (radius * -1)), y + (Math.cos((i * 3.141526D / 180)) * (radius * -1)));
            }
        }
        else if (mode == 1) {
            for (int i = 90; i <= 180; i++) {
                GL11.glVertex2d(x + (Math.sin((i * 3.141526D / 180)) * radius), y + (Math.cos((i * 3.141526D / 180)) * radius));
            }
        }
        else if (mode == 2) {
            for (int i = 90; i <= 180; i++) {
                GL11.glVertex2d(x + (Math.sin((i * 3.141526D / 180)) * (radius * -1)), y + (Math.cos((i * 3.141526D / 180)) * (radius * -1)));
            }
        }
        else if (mode == 3) {
            for (int i = 0; i <= 90; i++) {
                GL11.glVertex2d(x + (Math.sin((i * 3.141526D / 180)) * radius), y + (Math.cos((i * 3.141526D / 180)) * radius));
            }
        }
        else {
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    public static void drawRoundedRect(double x, double y, double width, double height, double radius, Color color) {
        glPushAttrib(GL_POINTS);

        glScaled(0.5, 0.5, 0.5); {
            x *= 2;
            y *= 2;
            width *= 2;
            height *= 2;

            width += x;
            height += y;

            glEnable(GL_BLEND);
            glDisable(GL_TEXTURE_2D);
            glColor4f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
            glEnable(GL_LINE_SMOOTH);
            glBegin(GL_POLYGON);

            int i;
            for (i = 0; i <= 90; i++) {
                glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D);
            }

            for (i = 90; i <= 180; i++) {
                glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, height - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D);
            }

            for (i = 0; i <= 90; i++) {
                glVertex2d(width - radius + Math.sin(i * Math.PI / 180.0D) * radius, height - radius + Math.cos(i * Math.PI / 180.0D) * radius);
            }

            for (i = 90; i <= 180; i++) {
                glVertex2d(width - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius);
            }

            glEnd();
            glEnable(GL_TEXTURE_2D);
            glDisable(GL_BLEND);
            glDisable(GL_LINE_SMOOTH);
            glDisable(GL_BLEND);
            glEnable(GL_TEXTURE_2D);
        }

        glScaled(2, 2, 2);
        glPopAttrib();
    }

    public static void drawCircle(float x, float y, float radius, int color) {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(GL_SMOOTH);
        GL11.glColor4d((color >> 16 & 0xff) / 255F, (color >> 8 & 0xff) / 255F, (color & 0xff) / 255F, (color >> 24 & 0xff) / 255F);
        GL11.glBegin(GL11.GL_POLYGON);
        for (int i = 0; i <= 360; i++) {
            GL11.glVertex2d(x + (MathHelper.sin((i * 3.141526f / 180)) * radius), y + (MathHelper.cos((i * 3.141526f / 180)) * radius));
        }
        GL11.glColor4d(1f, 1f, 1f, 1f);
        GL11.glEnd();
        GlStateManager.shadeModel(GL_FLAT);
        glDisable(GL_LINE_SMOOTH);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void drawTexture(ResourceLocation texture, double x, double y, double width, double height, Color color) {
        Util.mc.getTextureManager().bindTexture(texture);
        GL11.glColor4f((color.getRed() >> 16 & 0xFF) / 255.0F, (color.getGreen() >> 8 & 0xFF) / 255.0F, (color.getBlue() & 0xFF) / 255.0F, (color.getAlpha() >> 24 & 0xFF) / 255.0F);
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferBuilder.pos(x + width, y, 0).tex(1, 0).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(x, y, 0).tex(0, 0).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(x, y + height, 0).tex(0, 1).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(x, y + height, 0).tex(0, 1).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(x + width, y + height, 0).tex(1, 1).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(x + width, y, 0).tex(1, 0).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        draw(true);
        GlStateManager.resetColor();

    }

    public static void draw(boolean texture) {
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableAlpha();
        GlStateManager.disableLighting();

        GlStateManager.disableCull();
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GlStateManager.shadeModel(GL11.GL_SMOOTH);

        if (texture)
            GlStateManager.enableTexture2D();
        else
            GlStateManager.disableTexture2D();

        // actually draw
        Tessellator.getInstance().draw();

        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }

}
