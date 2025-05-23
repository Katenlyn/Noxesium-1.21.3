package com.noxcrew.noxesium.mixin.rules.qib;

import com.noxcrew.noxesium.NoxesiumMod;
import com.noxcrew.noxesium.feature.entity.QibBehaviorModule;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hooks into a player jumping, triggers qib effects.
 */
@Mixin(LivingEntity.class)
public abstract class JumpHookMixin {

    @Inject(
            method = "jumpFromGround",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/entity/LivingEntity;getDeltaMovement()Lnet/minecraft/world/phys/Vec3;"))
    public void onJumpFromGround(CallbackInfo ci) {
        var entity = (LivingEntity) ((Object) this);
        if (entity instanceof LocalPlayer localPlayer) {
            NoxesiumMod.getInstance().getModule(QibBehaviorModule.class).onPlayerJump(localPlayer);
        }
    }
}
