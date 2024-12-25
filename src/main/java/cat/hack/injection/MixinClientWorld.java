package cat.hack.injection;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import cat.hack.CatHack;
import cat.hack.core.manager.client.ModuleManager;
import cat.hack.events.impl.EventEntityRemoved;
import cat.hack.events.impl.EventEntitySpawn;
import cat.hack.events.impl.EventEntitySpawnPost;
import cat.hack.features.modules.Module;
import cat.hack.features.modules.render.WorldTweaks;
import cat.hack.setting.impl.ColorSetting;

import static cat.hack.features.modules.Module.mc;

@Mixin(ClientWorld.class)
public class MixinClientWorld {
    @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
    public void addEntityHook(Entity entity, CallbackInfo ci) {
        if(Module.fullNullCheck()) return;
        EventEntitySpawn ees = new EventEntitySpawn(entity);
        CatHack.EVENT_BUS.post(ees);
        if (ees.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method = "addEntity", at = @At("RETURN"), cancellable = true)
    public void addEntityHookPost(Entity entity, CallbackInfo ci) {
        if(Module.fullNullCheck()) return;
        EventEntitySpawnPost ees = new EventEntitySpawnPost(entity);
        CatHack.EVENT_BUS.post(ees);
        if (ees.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method = "removeEntity", at = @At("HEAD"))
    public void removeEntityHook(int entityId, Entity.RemovalReason removalReason, CallbackInfo ci) {
        if(Module.fullNullCheck()) return;
        EventEntityRemoved eer = new EventEntityRemoved(mc.world.getEntityById(entityId));
        CatHack.EVENT_BUS.post(eer);
    }

    @Inject(method = "getSkyColor", at = @At("HEAD"), cancellable = true)
    private void getSkyColorHook(Vec3d cameraPos, float tickDelta, CallbackInfoReturnable<Vec3d> cir) {
        if (ModuleManager.worldTweaks.isEnabled() && WorldTweaks.fogModify.getValue().isEnabled()) {
            ColorSetting c = WorldTweaks.fogColor.getValue();
            cir.setReturnValue(new Vec3d(c.getGlRed(), c.getGlGreen(), c.getGlBlue()));
        }
    }

    @Inject(method = "playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZJ)V", at = @At("HEAD"))
    private void playSoundHoof(double x, double y, double z, SoundEvent event, SoundCategory category, float volume, float pitch, boolean useDistance, long seed, CallbackInfo ci) {
        if(ModuleManager.soundESP.isEnabled())
            ModuleManager.soundESP.add(x, y, z, event.getId().toTranslationKey());
    }
}
