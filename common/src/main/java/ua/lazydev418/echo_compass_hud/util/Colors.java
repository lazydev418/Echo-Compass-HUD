package ua.lazydev418.echo_compass_hud.util;

import net.minecraft.network.chat.Component;

public enum Colors {
    RED("red", 0xFFFF0000),
    ORANGE("orange", 0xFFFF8800),
    YELLOW("yellow", 0xFFFFCC00),
    LIME("lime", 0xFF88FF00),
    GREEN("green", 0xFF00FF00),
    CYAN("cyan", 0xFF00FFFF),
    LIGHT_BLUE("light_blue", 0xFF00CCFF),
    BLUE("blue", 0xFF0088FF),
    PURPLE("purple", 0xFF8800FF),
    MAGENTA("magenta", 0xFFFF00FF),
    PINK("pink", 0xFFFF88FF),
    WHITE("white", 0xFFFFFFFF),
    DARK_RED("dark_red", 0xFF880000),
    BROWN("brown", 0xFF884400),
    DARK_GREEN("dark_green", 0xFF008800),
    DARK_CYAN("dark_cyan", 0xFF008888),
    DARK_BLUE("dark_blue", 0xFF000088),
    DARK_PURPLE("dark_purple", 0xFF440088),
    GRAY("gray", 0xFF888888),
    DARK_GRAY("dark_gray", 0xFF444444),
    BLACK("black", 0xFF000000),
    LIGHT_PINK("light_pink", 0xFFFFCCEE),
    PEACH("peach", 0xFFFFCC88),
    LIGHT_YELLOW("light_yellow", 0xFFFFFF88),
    LIGHT_GREEN("light_green", 0xFFCCFFCC),
    SKY_BLUE("sky_blue", 0xFFCCEEFF),
    LAVENDER("lavender", 0xFFCCCCFF),
    NEON_RED("neon_red", 0xFFFF0044),
    NEON_ORANGE("neon_orange", 0xFFFF4400),
    NEON_YELLOW("neon_yellow", 0xFFFFFF00),
    NEON_GREEN("neon_green", 0xFF00FF44),
    NEON_CYAN("neon_cyan", 0xFF00FFFF),
    NEON_BLUE("neon_blue", 0xFF0044FF),
    NEON_PURPLE("neon_purple", 0xFF8800FF),
    NEON_PINK("neon_pink", 0xFFFF00AA),
    GOLD("gold", 0xFFFFD700),
    SILVER("silver", 0xFFC0C0C0),
    BRONZE("bronze", 0xFFCD7F32),
    TEAL("teal", 0xFF008080),
    OLIVE("olive", 0xFF808000),
    MAROON("maroon", 0xFF800000);

    public final String key;
    public final int argb;

    Colors(String key, int argb) {
        this.key = key;
        this.argb = argb;
    }

    public int argb() {
        return argb;
    }

    public Component getName() {
        return Component.translatable("gui.echoesofadventure_compass.color." + key);
    }

    public static Colors getByIndex(int index) {
        Colors[] values = values();
        if (index >= 0 && index < values.length) {
            return values[index];
        }
        return WHITE;
    }

    public static int getIndexOf(Colors color) {
        Colors[] values = values();
        for (int i = 0; i < values.length; i++) {
            if (values[i] == color) {
                return i;
            }
        }
        return getIndexOf(WHITE);
    }

    public static Colors getByArgb(int argb) {
        for (Colors color : values()) {
            if (color.argb == argb) {
                return color;
            }
        }
        return WHITE;
    }

    public int getIndex() {
        return getIndexOf(this);
    }
}
