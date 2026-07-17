package ua.lazydev418.echo_compass_hud;

import net.fabricmc.api.ModInitializer;
import ua.lazydev418.echo_compass_hud.config.ClientConfig;

public class EchoCompassHUD implements ModInitializer {

    @Override
    public void onInitialize() {
        ClientConfig.load();
    }
}
