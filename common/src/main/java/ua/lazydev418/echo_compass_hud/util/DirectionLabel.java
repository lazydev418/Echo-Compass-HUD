package ua.lazydev418.echo_compass_hud.util;

import net.minecraft.network.chat.Component;

public enum DirectionLabel {
    NORTH(0, "north", "north"),
    NORTHEAST(45, "northeast", "ne"),
    EAST(90, "east", "east"),
    SOUTHEAST(135, "southeast", "se"),
    SOUTH(180, "south", "south"),
    SOUTHWEST(225, "southwest", "sw"),
    WEST(270, "west", "west"),
    NORTHWEST(315, "northwest", "nw");

    private final int degree;
    private final String fullKey;
    private final String shortKey;

    DirectionLabel(int degree, String fullKey, String shortKey) {
        this.degree = degree;
        this.fullKey = fullKey;
        this.shortKey = shortKey;
    }

    public int getDegree() {
        return degree;
    }

    public static DirectionLabel getFromYaw(float yaw) {
        yaw = (yaw % 360 + 360) % 360;
        int index = Math.round(yaw / 45f) % 8;
        return values()[index];
    }

    public Component getFull() {
        return Component.translatable("gui.echo_compass_hud.direction." + fullKey);
    }

    public Component getShortName() {
        return Component.translatable("gui.echo_compass_hud.direction.short." + shortKey);
    }

    public boolean isCardinal() {
        return degree % 90 == 0;
    }
}
