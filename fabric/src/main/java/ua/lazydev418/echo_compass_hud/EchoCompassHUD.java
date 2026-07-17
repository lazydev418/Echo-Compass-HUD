package ua.lazydev418.echo_compass_hud;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import ua.lazydev418.echo_compass_hud.config.ClientConfig;
import ua.lazydev418.echo_compass_hud.gui.CompassHud;
import ua.lazydev418.echo_compass_hud.util.IPlayerTabOverlay;

public class EchoCompassHUD implements ModInitializer {

    @Override
    public void onInitialize() {

        ClientConfig.load();

        HudRenderCallback.EVENT.register((gg, tickDelta) -> {
            Minecraft mc = Minecraft.getInstance();

            if (mc.player == null || mc.gui.getDebugOverlay().showDebugScreen()) {
                return;
            }

            IPlayerTabOverlay tab = (IPlayerTabOverlay) mc.gui.getTabList();
            if (tab.isVisible() || mc.options.keyPlayerList.isDown()) {
                return;
            }

            CompassHud.render(gg);
        });
    }
}
