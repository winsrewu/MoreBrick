package org.jawbts.morebrick.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import org.jawbts.morebrick.BrickBlockItemMap;
import org.jawbts.morebrick.MoreBrick;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin {
    @Shadow public abstract BlockState getBlockState();

    @Shadow private BlockState blockState;

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        FallingBlockEntity entity = (FallingBlockEntity) (Object) this;

        if (!BrickBlockItemMap.isBrokenBricks(getBlockState().getBlock()) || entity.getEntityWorld().isClient()) {
            return;
        }

        double speed = entity.getMovement().lengthSquared();

        HitResult hitResult = ProjectileUtil.getCollision(entity, Entity::canBeHitByProjectile);

        if (hitResult.getType() == HitResult.Type.BLOCK || hitResult.getType() == HitResult.Type.ENTITY) {
            int amplifier = 0;
            float chance = 0.01f;
            if (speed > 0.1) {
                chance = 0.60f;
                amplifier = 1;
            }
            if (speed > 0.4) {
                chance = 0.99f;
                amplifier = 2;
            }
            if (entity.getEntityWorld().random.nextFloat() < chance) {
                ((ServerWorld) entity.getEntityWorld()).spawnParticles(
                        new BlockStateParticleEffect(ParticleTypes.BLOCK, entity.getBlockState()),
                        entity.getX(), entity.getY(), entity.getZ(),
                        10, 0, 0, 0, 0.1
                );
                BlockSoundGroup soundGroup = ((AbstractBlockMixin) blockState.getBlock()).getSoundGroup();
                entity.playSound(
                        soundGroup.getBreakSound(),
                        (soundGroup.getVolume() + 1.0F) / 0.5F,
                        soundGroup.getPitch() * 0.8F
                );

                if (hitResult.getType() == HitResult.Type.ENTITY) {
                    Entity hitEntity = ((EntityHitResult) hitResult).getEntity();

                    if (hitEntity instanceof LivingEntity) {
                        int time = hitEntity.getEntityWorld().random.nextBetween(20 * 5, 20 * 60 * 40);

                        if (!((LivingEntity) hitEntity).getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
                            if (amplifier > 0) {
                                if (hitEntity.getEntityWorld().random.nextFloat() < 0.5f) {
                                    time = time / (20 * 60 * 40) * (20 * 30);
                                } else {
                                    amplifier -= 1;
                                }
                            }
                        }

                        if (time > 20 * 60 * 38) {
                            time = -1;
                        }

                        ((LivingEntity) hitEntity).addStatusEffect(new StatusEffectInstance(
                                MoreBrick.BLUDGEONED,
                                amplifier < 2 ? 200 : time,
                                amplifier,
                                false,
                                false
                        ));
                    }
                }
            }
        }
    }
}
