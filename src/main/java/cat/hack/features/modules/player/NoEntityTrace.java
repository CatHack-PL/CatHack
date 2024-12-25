package cat.hack.features.modules.player;

import cat.hack.features.modules.Module;
import cat.hack.setting.Setting;

public final class NoEntityTrace extends Module {
    public NoEntityTrace() {
        super("NoEntityTrace", Category.PLAYER);
    }

    public static final Setting<Boolean> ponly = new Setting<>("Pickaxe Only", true);
    public static final Setting<Boolean> noSword = new Setting<>("No Sword", true);
}