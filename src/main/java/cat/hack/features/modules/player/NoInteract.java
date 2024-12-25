package cat.hack.features.modules.player;

import cat.hack.features.modules.Module;
import cat.hack.setting.Setting;

public class NoInteract extends Module {
    public NoInteract() {
        super("NoInteract", Category.PLAYER);
    }

    public static Setting<Boolean> onlyAura = new Setting<>("OnlyAura", false);
}
