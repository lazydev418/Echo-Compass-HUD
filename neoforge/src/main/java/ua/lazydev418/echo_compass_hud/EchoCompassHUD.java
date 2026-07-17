package ua.lazydev418.echo_compass_hud;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import ua.lazydev418.echo_compass_hud.config.ClientConfig;
import ua.lazydev418.echo_compass_hud.util.Constants;

@Mod(Constants.MOD_ID)
public class EchoCompassHUD {
    public EchoCompassHUD(IEventBus eventBus) {
        ClientConfig.load();
    }
}