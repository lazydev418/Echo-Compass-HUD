package ua.lazydev418.echo_compass_hud.util;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

	public static final String MOD_ID = "echo_compass_hud";
	public static final String MOD_NAME = "Echo Compass HUD";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ResourceLocation COMPASS_BACKGROUND = new ResourceLocation(MOD_ID, "textures/gui/compass_background.png");
}