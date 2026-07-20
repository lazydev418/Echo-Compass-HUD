package ua.lazydev418.echo_compass_hud.gui;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.world.entity.player.Player;
import ua.lazydev418.echo_compass_hud.util.Colors;
import ua.lazydev418.echo_compass_hud.util.Constants;
import ua.lazydev418.echo_compass_hud.util.DirectionLabel;
import ua.lazydev418.echo_compass_hud.util.CompassMath;

public class ThemeRenderer {

    public static void renderBackground(GuiGraphics gg, int centerX) {
        gg.fill(centerX - 127, 27, centerX + 127, 43, 0x40000000);
        gg.fill(centerX - 127, 0, centerX + 127, 24, 0x40000000);
        gg.pose().pushMatrix();
        gg.pose().translate(0, 0);
        gg.blit(RenderPipelines.GUI_TEXTURED, Constants.COMPASS_BACKGROUND, centerX - 128, 0, 0, 0, 256, 45, 256, 45, -1);
        gg.pose().popMatrix();
    }

    public static void renderCompassStrip(GuiGraphics gg, Font font, int centerX, float yaw) {
        int startAngle = ((int) yaw / 15) * 15 - 120;
        int endAngle = ((int) yaw / 15) * 15 + 120;

        for (int i = startAngle; i <= endAngle; i += 15) {
            float angleLoop = CompassMath.normalizeYaw(i);
            float delta = CompassMath.getDeltaAngle(yaw, angleLoop);
            float xPos = CompassMath.calculateScreenX(centerX, delta);

            if (Math.abs(xPos - centerX) > 120) continue;
            boolean isMajor = (Math.round((angleLoop) % 45) == 0);

            DirectionLabel dir = DirectionLabel.getFromYaw(angleLoop);
            if (isMajor) {
                String text = ChatFormatting.BOLD + dir.getShortName().getString();
                int color = (dir == DirectionLabel.NORTH) ? Colors.RED.argb : Colors.WHITE.argb;

                gg.drawString(font, text, (int) (xPos - font.width(text) / 2f), 4, color, false);
            } else {
                gg.fill((int) xPos, 4, (int) xPos + 1, 8, Colors.WHITE.argb);

                String numText = String.valueOf((int) angleLoop);

                gg.pose().pushMatrix();
                float scale = 0.9f;
                gg.pose().translate(xPos + 1, 10);
                gg.pose().scale(scale, scale);
                drawCenteredString(gg, font, numText, 0, 0);
                gg.pose().popMatrix();
            }
        }
    }

    public static void renderInfoText(GuiGraphics gg, Font font, Player player, int centerX, float yaw) {
        DirectionLabel currentDir = DirectionLabel.getFromYaw(yaw);

        String directionName = currentDir.getFull().getString();
        int displayYaw = (int) CompassMath.normalizeYaw(yaw);
        if (displayYaw == 360) displayYaw = 0;
        String angleText = String.valueOf(displayYaw);
        String coordinates = String.format("%d, %d, %d", player.getBlockX(), player.getBlockY(), player.getBlockZ());

        drawCenteredString(gg, font, directionName, centerX + 66, 31);
        drawCenteredString(gg, font, coordinates, centerX - 68, 31);
        drawCenteredString(gg, font, angleText, centerX, 31);
    }

    private static void drawCenteredString(GuiGraphics gg, Font font, String text, float x, int y) {
        gg.drawString(font, text, (int) (x - font.width(text) / 2.0F), y, Colors.WHITE.argb);
    }
}
