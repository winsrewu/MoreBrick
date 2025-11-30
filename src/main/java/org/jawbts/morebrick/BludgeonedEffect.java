package org.jawbts.morebrick;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;

public class BludgeonedEffect extends StatusEffect {
    protected BludgeonedEffect() {
        super(StatusEffectCategory.HARMFUL, 0xb40000);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        switch (amplifier) {
            case 0 -> {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 209, 0, false, false, false));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 209, 3, false, false, false));
            }
            case 1 -> {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 209, 1, false, false, false));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 209, 5, false, false, false));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 209, 0, false, false, false));
            }
            default -> {
                if (!entity.isSleeping()) {
                    entity.sleep(entity.getSteppingPos());
                    entity.setPos(entity.getX(), entity.getY() + 0.5, entity.getZ());
                }
            }
        }

        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
