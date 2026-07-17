package ua.lazydev418.echo_compass_hud.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ClientConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File FILE = new File("config/echo_compass_hud.json");

    public static float compassPositionX = 1.0f;
    public static float compassScale = 1.0f;

    public static void load() {
        if (!FILE.exists()) {
            save();
            return;
        }
        try (FileReader reader = new FileReader(FILE)) {
            CompassConfigData data = GSON.fromJson(reader, CompassConfigData.class);
            if (data != null) {
                float loadedX = data.compassPositionX;
                float loadedScale = data.compassScale;

                float clampedX = clamp(loadedX, 0.0f, 2.0f);
                float clampedScale = clamp(loadedScale, 0.5f, 1.5f);

                compassPositionX = clampedX;
                compassScale = clampedScale;

                if (loadedX != clampedX || loadedScale != clampedScale) {
                    save();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            File parent = FILE.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            try (FileWriter writer = new FileWriter(FILE)) {
                CompassConfigData data = new CompassConfigData();
                data.compassPositionX = clamp(compassPositionX, 0.0f, 2.0f);
                data.compassScale = clamp(compassScale, 0.5f, 1.5f);
                GSON.toJson(data, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    private static class CompassConfigData {

        @SerializedName("// compassPositionX (UA)")
        String commentPositionUa = "Позиція компаса по осі X. 1.0 центр екрану. Межі: від 0.0 до 2.0.";

        @SerializedName("// compassPositionX (EN)")
        String commentPositionEn = "Compass position on the X axis. 1.0 center of the screen. Limits: from 0.0 to 2.0.";

        float compassPositionX = ClientConfig.compassPositionX;

        @SerializedName("// compassScale (UA)")
        String commentScaleUa = "Масштаб (розмір) компаса. 1.0 стандарт. Межі: від 0.5 до 1.5.";

        @SerializedName("// compassScale (EN)")
        String commentScaleEn = "Compass scale (size). 1.0 default. Limits: from 0.5 to 1.5.";

        float compassScale = ClientConfig.compassScale;
    }
}
