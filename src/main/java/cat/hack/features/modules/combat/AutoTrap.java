package cat.hack.features.modules.combat;

import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import cat.hack.core.Managers;
import cat.hack.core.manager.player.CombatManager;
import cat.hack.features.modules.base.TrapModule;
import cat.hack.setting.Setting;

public final class AutoTrap extends TrapModule {
    private final Setting<CombatManager.TargetBy> targetBy = new Setting<>("Target By", CombatManager.TargetBy.Distance);
    private final Setting<Boolean> targetMovingPlayers = new Setting<>("MovingPlayers", false);

    public AutoTrap() {
        super("AutoTrap", Category.COMBAT);
    }

    @Override
    protected boolean needNewTarget() {
        return target == null
                || target.distanceTo(mc.player) > range.getValue()
                || target.getHealth() + target.getAbsorptionAmount() <= 0
                || target.isDead();
    }

    @Override
    protected @Nullable PlayerEntity getTarget() {
        return Managers.COMBAT.getTarget(range.getValue(), targetBy.getValue(), p -> p.getVelocity().lengthSquared() < 0.08 || targetMovingPlayers.getValue());
    }
}