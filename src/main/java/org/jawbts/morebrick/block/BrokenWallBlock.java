package org.jawbts.morebrick.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Falling;
import net.minecraft.block.WallBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

import java.util.List;

public class BrokenWallBlock extends WallBlock implements Falling {
    public BrokenWallBlock(Settings settings) {
        super(settings);
    }

    private static final List<BlockPos> NEIGHBORS = List.of(
            new BlockPos(1, 0, 0),
            new BlockPos(-1, 0, 0),
            new BlockPos(0, 0, 1),
            new BlockPos(0, 0, -1)
    );

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (canFallThrough(neighborState) && direction != Direction.UP) {
            tickView.scheduleBlockTick(pos, this, 0);
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (world.isClient()) {
            return;
        }

        if (entity instanceof LivingEntity
                && world instanceof ServerWorld
                && (world.getTimeOfDay() + entity.getId()) % 20 == 0
                && ((LivingEntity) entity).getEquippedStack(EquipmentSlot.FEET).isEmpty()) {
            int damage = (int) ((entity.isControlledByPlayer() ? entity.getMovement() : entity.getLastRenderPos().subtract(entity.getEntityPos())).lengthSquared() * 100);
            entity.damage((ServerWorld) world, world.getDamageSources().generic(), damage);
        }

        double speed = (entity.isControlledByPlayer() ? entity.getMovement() : entity.getLastRenderPos().subtract(entity.getEntityPos())).lengthSquared();

        if (speed < 0.05) {
            return;
        }

        tryFall(world, pos, speed > 0.09);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        tryFall(world, pos, false);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int supportBlockCount = 0;

        for (BlockPos neighborPos : NEIGHBORS) {
            if (!canFallThrough(world.getBlockState(pos.add(neighborPos)))) {
                supportBlockCount++;
            }
        }

        if (supportBlockCount == 4) {
            return;
        }

        tryFall(world, pos, supportBlockCount <= 2);
    }

    private void tryFall(World world, BlockPos pos, boolean definitely) {
        if (!canFallThrough(world.getBlockState(pos.down())) || pos.getY() < world.getBottomY()
                || (!definitely && world.getRandom().nextInt(20) != 0)
                || world.isClient()) {
            return;
        }
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, world.getBlockState(pos));
    }

    public static boolean canFallThrough(BlockState state) {
        return state.isAir() || state.isIn(BlockTags.FIRE) || state.isLiquid() || state.isReplaceable();
    }
}
