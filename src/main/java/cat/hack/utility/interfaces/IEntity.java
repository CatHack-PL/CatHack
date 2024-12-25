package cat.hack.utility.interfaces;

import net.minecraft.util.math.BlockPos;
import cat.hack.features.modules.render.Trails;

import java.util.List;

public interface IEntity {
    List<Trails.Trail> getTrails();

    BlockPos catHack_PL$getVelocityBP();
}
