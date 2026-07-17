package ua.lazydev418.echo_compass_hud.gui;

import net.minecraft.ChatFormatting;
import ua.lazydev418.echo_compass_hud.util.DirectionLabel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import ua.lazydev418.echo_compass_hud.util.Constants;

import java.util.EnumSet;

public class CompassRenderer {
    private static final ResourceLocation COMPASS_BACKGROUND = new ResourceLocation(Constants.MOD_ID, "textures/gui/compass_background.png");
    private static final DirectionLabel[] DIRECTIONS = DirectionLabel.values();

    private static final int DIRECTION_COUNT = DIRECTIONS.length;
    private static final int MARKER_SPACING = 50;
    private static final float SCALE = 1.5F;
    private static final int TOTAL_SPAN = DIRECTION_COUNT * MARKER_SPACING;
    private static final int BACKGROUND_WIDTH = 250;

    public static void renderBackgroundCompass(GuiGraphics graphics, int centerX) {
        filledOverlayBackground(graphics, centerX - 127, 28, centerX + 127, 42);
        filledOverlayBackground(graphics, centerX - 127, 0, centerX + 127, 23);
        graphics.blit(COMPASS_BACKGROUND, centerX - 128, 0, 0, 0, 256, 45, 256, 45);
    }

    public static void renderCompass(GuiGraphics graphics, Minecraft mc, int centerX, float yaw) {
        Font font = mc.font;
        int yPos = 5;
        EnumSet<DirectionLabel> visible = EnumSet.noneOf(DirectionLabel.class);

        float adjustedYaw = (yaw % 360 + 360) % 360 / 360.0F * TOTAL_SPAN;

        for (int i = -25; i <= 75; i += 5) {
            float pos = (adjustedYaw + i + TOTAL_SPAN) % TOTAL_SPAN;
            int index = (int) (pos / MARKER_SPACING) % DIRECTION_COUNT;
            float offset = pos % MARKER_SPACING;
            float xPos = centerX - offset * SCALE + i * SCALE;

            DirectionLabel label = DIRECTIONS[index];
            if (visible.contains(label)) continue;

            String shortStr = ChatFormatting.BOLD + label.getShortName().getString();
            drawCenteredString(graphics, font, shortStr, xPos, yPos);

            int baseAngle = (index * 45 + 360) % 360;
            visible.add(label);

            for (int j = -2; j <= 2; j++) {
                if (j == 0) continue;

                float markerX = xPos + j * SCALE * MARKER_SPACING / 3.0F;
                if (markerX >= centerX - BACKGROUND_WIDTH / 2.0F && markerX <= centerX + BACKGROUND_WIDTH / 2.0F) {
                    String tick = "∣";
                    String angleStr = String.valueOf((baseAngle + 15 * j + 360) % 360);

                    drawCenteredString(graphics, font, tick, markerX, yPos);
                    drawCenteredString(graphics, font, angleStr, markerX, yPos + 9);
                }
            }
        }
    }

    public static void renderInformation(GuiGraphics graphics, Minecraft mc, Player player, int centerX, float yaw) {
        Font font = mc.font;
        int index = Math.round(yaw / 45.0F) % DIRECTION_COUNT;

        int currentAngle = (int) ((yaw % 360 + 361) % 360);
        String angleStr = String.valueOf(currentAngle);
        String directionName = DIRECTIONS[index].getFull().getString();
        String coordinates = (int) player.getX() + ", " + (int) player.getY() + ", " + (int) player.getZ();

        drawCenteredString(graphics, font, directionName, centerX + 67, 31);
        drawCenteredString(graphics, font, coordinates, centerX - 67, 31);
        drawCenteredString(graphics, font, angleStr, centerX, 31);
    }

    private static void drawCenteredString(GuiGraphics graphics, Font font, String text, float x, int y) {
        graphics.drawString(font, text, (int) (x - font.width(text) / 2.0F), y, 0xFFFFFF);
    }

    private static void filledOverlayBackground(GuiGraphics graphics, int pMinX, int pMinY, int pMaxX, int pMaxY) {
        graphics.fill(pMinX, pMinY, pMaxX, pMaxY, 0x40000000);
    }
}
