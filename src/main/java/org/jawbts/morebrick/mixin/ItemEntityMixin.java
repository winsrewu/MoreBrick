package org.jawbts.morebrick.mixin;

import net.minecraft.client.sound.Sound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import org.jawbts.morebrick.BrickBlockItemMap;
import org.jawbts.morebrick.BrickItems;
import org.jawbts.morebrick.MoreBrick;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Inject(method = "tick", at = @At("RETURN"))
    private void tick(CallbackInfo ci) {
        ItemEntity itemEntity = (ItemEntity) (Object) this;

        if (!BrickBlockItemMap.isIntactBrick(itemEntity.getStack().getItem())
                || itemEntity.isOnGround()
                || itemEntity.getEntityWorld().isClient()) {
            return;
        }

        double speed = itemEntity.getMovement().lengthSquared();
        HitResult hitResult = ProjectileUtil.getCollision(itemEntity, Entity::canBeHitByProjectile);

        if (hitResult.getType() == HitResult.Type.BLOCK || hitResult.getType() == HitResult.Type.ENTITY) {
            int amplifier = 0;
            float chance = 0.01f;
            if (speed > 0.2) {
                chance = 0.60f;
                amplifier = 1;
            }
            if (speed > 0.7) {
                chance = 0.99f;
                amplifier = 2;
            }
            if (itemEntity.getEntityWorld().random.nextFloat() < chance) {
                itemEntity.setVelocity(Vec3d.ZERO);
                ((ServerWorld) itemEntity.getEntityWorld()).spawnParticles(
                        new ItemStackParticleEffect(ParticleTypes.ITEM, itemEntity.getStack()),
                        itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(),
                        10, 0, 0, 0, 0.1
                );
                BlockSoundGroup soundGroup = ((AbstractBlockMixin) BrickBlockItemMap.ITEM_BLOCK_MAP.get(itemEntity.getStack().getItem())).getSoundGroup();
                itemEntity.playSound(
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

                itemEntity.setStack(new ItemStack(BrickItems.INTACT_TO_BROKEN_ITEMS.get(itemEntity.getStack().getItem()), itemEntity.getStack().getCount()));
            }
        }
    }
}
