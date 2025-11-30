package org.jawbts.morebrick.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jawbts.morebrick.MoreBrick;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "wakeUp(ZZ)V", at = @At("HEAD"), cancellable = true)
    private void wakeUp(boolean skipSleepTimer, boolean updateSleepingPlayers, CallbackInfo ci) {
        if (((LivingEntity) (Object) this).getStatusEffect(MoreBrick.BLUDGEONED) != null) {
            ci.cancel();
        }
    }
}
