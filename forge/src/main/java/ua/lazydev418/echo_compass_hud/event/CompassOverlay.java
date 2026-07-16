package ua.lazydev418.echo_compass_hud.event;

import ua.lazydev418.echo_compass_hud.gui.CompassHud;
import ua.lazydev418.echo_compass_hud.util.IPlayerTabOverlay;
import ua.lazydev418.echo_compass_hud.util.Constants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = Constants.MOD_ID,
        value = Dist.CLIENT
)
public class CompassOverlay {

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGuiOverlayEvent.Pre event) {
        if (!event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id())) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.renderDebug) return;

        IPlayerTabOverlay tab = (IPlayerTabOverlay) mc.gui.getTabList();
        if (tab.isVisible() || mc.options.keyPlayerList.isDown()) return;

        CompassHud.render(event.getGuiGraphics());
    }
}
