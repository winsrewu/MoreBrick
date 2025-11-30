package org.jawbts.morebrick.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShapes;
import org.jawbts.morebrick.BrickBlockItemMap;
import org.jawbts.morebrick.BrickItems;
import org.jawbts.morebrick.MoreBrick;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract void endCombat();

    @Inject(method = "wakeUp", at = @At("HEAD"), cancellable = true)
    private void wakeUp(CallbackInfo ci) {
        if (((LivingEntity) (Object) this).getStatusEffect(MoreBrick.BLUDGEONED) != null) {
            ci.cancel();
        }
    }

    @Inject(method = "tickMovement", at = @At("HEAD"), cancellable = true)
    private void tickMovement(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        StatusEffectInstance effect = entity.getStatusEffect(MoreBrick.BLUDGEONED);
        if (effect != null
                && effect.getAmplifier() >= 2
                && entity.getEntityWorld() instanceof ServerWorld) {

            float f = entity.getWidth() * 0.8F;
            Box box = Box.of(entity.getEyePos(), f, 1.0E-6, f);
            boolean insideBlock = BlockPos.stream(box)
                    .anyMatch(
                            pos -> {
                                BlockState blockState = entity.getEntityWorld().getBlockState(pos);
                                return !blockState.isAir()
                                        && blockState.shouldSuffocate(entity.getEntityWorld(), pos)
                                        && VoxelShapes.matchesAnywhere(blockState.getCollisionShape(entity.getEntityWorld(), pos).offset(pos), VoxelShapes.cuboid(box), BooleanBiFunction.AND);
                            }
                    );

            if (insideBlock) {
                entity.setPos(entity.getX(), entity.getY() + 0.5, entity.getZ());
            }

            entity.setVelocity(entity.getVelocity().multiply(0.98));
            if (entity.canMoveVoluntarily() && entity.canActVoluntarily()) {
                entity.travel(entity.getVelocity());
            }

            ci.cancel();
        }
    }

    @Inject(method = "onAttacking", at = @At("HEAD"))
    private void onAttacking(Entity target, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (target instanceof LivingEntity livingEntity
                && BrickBlockItemMap.isIntactBrick(entity.getMainHandStack().getItem())
                && entity.getEntityWorld() instanceof ServerWorld) {
            Random random = target.getEntityWorld().random;
            int amplifierRoll = random.nextBetween(0, 20);
            int amplifier = 0;

            if (amplifierRoll > 10) {
                amplifier = 1;
            } else if (amplifierRoll > 18) {
                amplifier = 2;
            }

            int time = random.nextBetween(20 * 5, 20 * 60 * 40);
            if (time > 20 * 60 * 38) {
                time = -1;
            }

            if (entity instanceof PlayerEntity playerEntity) {
                boolean critic = playerEntity.fallDistance > 0.0
                        && !playerEntity.isOnGround()
                        && !playerEntity.isClimbing()
                        && !playerEntity.isTouchingWater()
                        && !playerEntity.hasBlindnessEffect()
                        && !playerEntity.hasVehicle()
                        && target instanceof LivingEntity
                        && !playerEntity.isSprinting();
                if (critic) {
                    amplifier = 2;
                }
            }

            livingEntity.addStatusEffect(
                    new StatusEffectInstance(
                            MoreBrick.BLUDGEONED,
                            amplifier < 2 ? 200 : time,
                            amplifier,
                            false,
                            amplifier < 2
                    )
            );

            ItemStack is = entity.getMainHandStack();
            Item item = BrickItems.INTACT_TO_BROKEN_ITEMS.get(is.getItem());
            if (is.isEmpty()) {
                entity.setStackInHand(Hand.MAIN_HAND, new ItemStack(item, 1));
            } else {
                entity.giveOrDropStack(new ItemStack(item, 1));
            }
            is.decrement(1);
        }
    }
}
