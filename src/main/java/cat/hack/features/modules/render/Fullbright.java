package cat.hack.features.modules.render;

import cat.hack.features.modules.Module;
import cat.hack.setting.Setting;

public class Fullbright extends Module {
    public Fullbright() {
        super("Fullbright", Category.RENDER);
    }

    public static Setting<Float> minBright = new Setting<>("MinBright", 0.5f, 0f, 1f);
}
