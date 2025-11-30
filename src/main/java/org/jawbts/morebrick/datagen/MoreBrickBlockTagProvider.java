package org.jawbts.morebrick.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import org.jawbts.morebrick.BrickBlocks;

import java.util.concurrent.CompletableFuture;

public class MoreBrickBlockTagProvider extends FabricTagProvider<Block> {
    public MoreBrickBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        for (BrickBlocks.BlockGroup blockGroup : BrickBlocks.BROKEN_BLOCK_GROUPS) {
            getTagBuilder(BlockTags.SHOVEL_MINEABLE)
                    .add(Registries.BLOCK.getId(blockGroup.CUBE))
                    .add(Registries.BLOCK.getId(blockGroup.STAIRS))
                    .add(Registries.BLOCK.getId(blockGroup.SLAB));

            if (blockGroup.hasWall) {
                getTagBuilder(BlockTags.SHOVEL_MINEABLE)
                        .add(Registries.BLOCK.getId(blockGroup.WALL));
                getTagBuilder(BlockTags.WALLS)
                        .add(Registries.BLOCK.getId(blockGroup.WALL));
            }

            if (blockGroup.hasFence) {
                getTagBuilder(BlockTags.SHOVEL_MINEABLE)
                        .add(Registries.BLOCK.getId(blockGroup.FENCE));
                getTagBuilder(BlockTags.FENCES)
                        .add(Registries.BLOCK.getId(blockGroup.FENCE));
            }
        }

        for (BrickBlocks.BlockItemPair blockItemPair : BrickBlocks.BLOCK_ITEM_PAIRS) {
            getTagBuilder(BlockTags.SHOVEL_MINEABLE)
                    .add(Registries.BLOCK.getId(blockItemPair.BLOCK));
        }
    }
}
