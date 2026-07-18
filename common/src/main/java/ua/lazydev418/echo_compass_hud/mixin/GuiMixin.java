package ua.lazydev418.echo_compass_hud.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.lazydev418.echo_compass_hud.gui.CompassHud;
import ua.lazydev418.echo_compass_hud.util.IPlayerTabOverlay;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void renderCompassHud(GuiGraphics gg, DeltaTracker dt, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null || mc.gui.getDebugOverlay().showDebugScreen()) return;

        IPlayerTabOverlay tab = (IPlayerTabOverlay) mc.gui.getTabList();
        if (tab != null && (tab.isVisible() || mc.options.keyPlayerList.isDown())) return;

        CompassHud.render(gg);
    }
}
