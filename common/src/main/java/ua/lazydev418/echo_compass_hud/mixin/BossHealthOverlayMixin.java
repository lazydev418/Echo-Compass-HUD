package ua.lazydev418.echo_compass_hud.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.lazydev418.echo_compass_hud.config.ClientConfig;
import ua.lazydev418.echo_compass_hud.util.IPlayerTabOverlay;

@Mixin(BossHealthOverlay.class)
public class BossHealthOverlayMixin {

    @Inject(
            method = "render",
            at = @At("HEAD")
    )
    private void BossBarYPosBefore(GuiGraphics gg, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || mc.options.renderDebug) return;

        if (mc.gui.getTabList() instanceof IPlayerTabOverlay tab) {
            if (tab.isVisible() || mc.options.keyPlayerList.isDown()) return;
        }

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

        float bossLeft = (scaledWidth / 2f) - 91f;
        float bossRight = (scaledWidth / 2f) + 91f;

        float compassLeft = actualCenterX - halfWidth;
        float compassRight = actualCenterX + halfWidth;

        float yOffset = 0.0F;
        if (compassLeft < bossRight && compassRight > bossLeft) {
            yOffset = (45.0F * scale) + 3.0F;
        }

        PoseStack ps = gg.pose();
        ps.pushPose();
        ps.translate(0.0F, yOffset, 0.0F);
    }

    @Inject(
            method = "render",
            at = @At("RETURN")
    )
    private void BossBarYPosAfter(GuiGraphics gg, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || mc.options.renderDebug) return;
        if (mc.gui.getTabList() instanceof IPlayerTabOverlay tab) {
            if (tab.isVisible() || mc.options.keyPlayerList.isDown()) return;
        }

        PoseStack ps = gg.pose();
        ps.popPose();
    }
}
