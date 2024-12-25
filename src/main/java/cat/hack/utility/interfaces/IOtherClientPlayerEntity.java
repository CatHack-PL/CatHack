package cat.hack.utility.interfaces;

import cat.hack.features.modules.combat.Aura;

public interface IOtherClientPlayerEntity {
    void resolve(Aura.Resolver mode);

    void releaseResolver();
}
