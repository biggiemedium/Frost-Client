package dev.px.frost.API.Util.Math;

import net.minecraft.client.Minecraft;

import java.util.Date;

public class Mathutil {

    public static int clamp(int num, int min, int max) {
        return num < min ? min : Math.min(num, max);
    }

    public static float clamp(float num, float min, float max) {
        return num < min ? min : Math.min(num, max);
    }

    public static double clamp(double num, double min, double max) {
        return num < min ? min : Math.min(num, max);
    }

    public static double deltaTime() {
        return Minecraft.getDebugFPS() > 0 ? (1.0000 / Minecraft.getDebugFPS()) : 1;
    }

    private <T extends Number> int toInt(T value) {
        return value.intValue();
    }

    private <T extends Number> double toDouble(T value) {
        return value.doubleValue();
    }

    private <T extends Number> float toFloat(T value) {
        return value.floatValue();
    }


}
