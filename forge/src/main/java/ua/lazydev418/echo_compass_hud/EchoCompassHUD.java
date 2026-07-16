package ua.lazydev418.echo_compass_hud;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import ua.lazydev418.echo_compass_hud.config.ClientConfig;
import ua.lazydev418.echo_compass_hud.util.Constants;

@Mod(Constants.MOD_ID)
public class EchoCompassHUD {
    public EchoCompassHUD() {
        ClientConfig.load();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
