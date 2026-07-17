package ua.lazydev418.echo_compass_hud.event;

import ua.lazydev418.echo_compass_hud.gui.CompassHud;
import ua.lazydev418.echo_compass_hud.util.IPlayerTabOverlay;
import ua.lazydev418.echo_compass_hud.util.Constants;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(
        modid = Constants.MOD_ID,
        value = Dist.CLIENT
)
public class CompassOverlay {

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGuiLayerEvent.Pre event) {
        if (!event.getName().equals(VanillaGuiLayers.HOTBAR)) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.gui.getDebugOverlay().showDebugScreen()) return;

        IPlayerTabOverlay tab = (IPlayerTabOverlay) mc.gui.getTabList();
        if (tab.isVisible() || mc.options.keyPlayerList.isDown()) return;

        CompassHud.render(event.getGuiGraphics());
    }
}
