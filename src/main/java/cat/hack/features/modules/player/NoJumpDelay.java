package cat.hack.features.modules.player;

import cat.hack.injection.accesors.ILivingEntity;
import cat.hack.features.modules.Module;
import cat.hack.setting.Setting;

public class NoJumpDelay extends Module {
    public NoJumpDelay() {
        super("NoJumpDelay", Category.PLAYER);
    }

    private final Setting<Integer> delay = new Setting<>("Delay", 1, 0, 4);

    @Override
    public void onUpdate() {
        if (((ILivingEntity)mc.player).getLastJumpCooldown() > delay.getValue()) {
            ((ILivingEntity)mc.player).setLastJumpCooldown(delay.getValue());
        }
    }
}
