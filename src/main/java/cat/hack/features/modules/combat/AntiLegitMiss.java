package cat.hack.features.modules.combat;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.util.hit.EntityHitResult;
import cat.hack.events.impl.EventAttack;
import cat.hack.events.impl.EventHandleBlockBreaking;
import cat.hack.features.modules.Module;

public class AntiLegitMiss extends Module {
    public AntiLegitMiss() {
        super("AntiLegitMiss", Category.COMBAT);
    }

    @EventHandler
    public void onAttack(EventAttack e) {
        if (!(mc.crosshairTarget instanceof EntityHitResult) && e.isPre())
            e.cancel();
    }

    @EventHandler
    public void onBlockBreaking(EventHandleBlockBreaking e) {
        if (!(mc.crosshairTarget instanceof EntityHitResult))
            e.cancel();
    }
}
