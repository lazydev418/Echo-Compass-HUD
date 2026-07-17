package ua.lazydev418.echo_compass_hud.util;

public class CompassMath {
    public static final float MARKER_SPACING = 50.0f;
    public static final float SCALE_FACTOR = 1.5f;
    public static final int MAX_RENDER_WIDTH = 125;

    public static float normalizeYaw(float yaw) {
        return (yaw % 360 + 360) % 360;
    }

    public static float getDeltaAngle(float yaw, float bearing) {
        float delta = bearing - yaw;
        while (delta < -180) delta += 360;
        while (delta > 180) delta -= 360;
        return delta;
    }

    public static float calculateScreenX(int centerX, float delta) {
        float pixelsPerDegree = (MARKER_SPACING * SCALE_FACTOR) / 45.0f;
        return centerX + delta * pixelsPerDegree;
    }

}
