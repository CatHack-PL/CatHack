package cat.hack.features.modules.player;

import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.EventPriority;
import cat.hack.CatHack;
import cat.hack.core.Managers;
import cat.hack.core.manager.client.ModuleManager;
import cat.hack.events.impl.EventTick;
import cat.hack.features.modules.Module;

public class TpsSync extends Module {
    public TpsSync() {
        super("TpsSync", Module.Category.PLAYER);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onTick(EventTick e) {
        if (ModuleManager.timer.isEnabled()) return;
        if (Managers.SERVER.getTPS() > 1)
            CatHack.TICK_TIMER = Managers.SERVER.getTPS() / 20f;
        else CatHack.TICK_TIMER = 1f;
    }

    @Override
    public void onDisable() {
        CatHack.TICK_TIMER = 1f;
    }
}
