package cat.hack.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.block.*;
import cat.hack.events.impl.EventCollision;
import cat.hack.features.modules.Module;
import cat.hack.setting.Setting;

public class Jesus extends Module {
    public Jesus() {
        super("Jesus", Category.MOVEMENT);
    }

    public final Setting<Mode> mode = new Setting<>("Mode", Mode.SOLID);

    @EventHandler
    public void onCollide(EventCollision e) {
        if (e.getState().getBlock() instanceof FluidBlock) {
            e.setState(mode.is(Mode.SOLID) ? Blocks.ENDER_CHEST.getDefaultState() : Blocks.OBSIDIAN.getDefaultState());
        }
    }

    public enum Mode {
        SOLID, SOLID2
    }
}
