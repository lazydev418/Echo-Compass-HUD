package ua.lazydev418.echo_compass_hud.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import ua.lazydev418.echo_compass_hud.config.ClientConfig;
import ua.lazydev418.echo_compass_hud.util.CompassMath;

public class CompassHud {
    public static void render(GuiGraphics gg) {
        Minecraft mc = Minecraft.getInstance();
        float yaw = CompassMath.normalizeYaw(mc.player.getYRot() + 180f);

        int scaledWidth = mc.getWindow().getGuiScaledWidth();
        float scale = ClientConfig.compassScale;
        float pos = ClientConfig.compassPositionX;

        float halfWidth = 128f * scale;
        float minX = halfWidth;
        float maxX = scaledWidth - halfWidth;

        if (maxX < minX) {
            minX = scaledWidth / 2f;
            maxX = scaledWidth / 2f;
        }

        float actualCenterX;
        if (pos <= 1.0f) {
            actualCenterX = minX + (scaledWidth / 2f - minX) * pos;
        } else {
            actualCenterX = scaledWidth / 2f + (maxX - scaledWidth / 2f) * (pos - 1.0f);
        }

        gg.pose().pushPose();
        gg.pose().translate(actualCenterX, 0, 0);
        gg.pose().scale(scale, scale, 1.0f);

        ThemeRenderer.renderBackground(gg, 0);
        ThemeRenderer.renderCompassStrip(gg, mc.font, 0, yaw);
        ThemeRenderer.renderInfoText(gg, mc.font, mc.player, 0, yaw);

        gg.pose().popPose();
    }
}
