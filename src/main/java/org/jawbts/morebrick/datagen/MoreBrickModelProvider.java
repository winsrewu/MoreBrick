package org.jawbts.morebrick.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.client.data.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.jawbts.morebrick.BrickBlocks;
import org.jawbts.morebrick.BrickItems;

public class MoreBrickModelProvider extends FabricModelProvider {
    public MoreBrickModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        for (BrickBlocks.BlockItemPair pair : BrickBlocks.BLOCK_ITEM_PAIRS) {
            blockStateModelGenerator.registerSimpleCubeAll(pair.BLOCK);
        }

        for (BrickBlocks.BlockGroup group : BrickBlocks.BLOCK_GROUPS) {
            blockStateModelGenerator.registerSimpleCubeAll(group.CUBE);

            final Identifier cubeIdentifier = Identifier.of("morebrick", "block/" + group.name + "s");
            final TextureMap textureMap = TextureMap.all(cubeIdentifier);

            final Identifier stairsModelId = Models.STAIRS.upload(group.STAIRS, textureMap, blockStateModelGenerator.modelCollector);
            final Identifier innerStairsModelId = Models.INNER_STAIRS.upload(group.STAIRS, textureMap, blockStateModelGenerator.modelCollector);
            final Identifier outerStairsModelId = Models.OUTER_STAIRS.upload(group.STAIRS, textureMap, blockStateModelGenerator.modelCollector);
            blockStateModelGenerator.blockStateCollector.accept(
                    BlockStateModelGenerator.createStairsBlockState(
                            group.STAIRS,
                            BlockStateModelGenerator.createWeightedVariant(innerStairsModelId),
                            BlockStateModelGenerator.createWeightedVariant(outerStairsModelId),
                            BlockStateModelGenerator.createWeightedVariant(stairsModelId)
                    )
            );
            blockStateModelGenerator.registerParentedItemModel(group.STAIRS, stairsModelId);

            final Identifier slabBottomModelId = Models.SLAB.upload(group.SLAB, textureMap, blockStateModelGenerator.modelCollector);
            final Identifier slabTopModelId = Models.SLAB_TOP.upload(group.SLAB, textureMap, blockStateModelGenerator.modelCollector);
            blockStateModelGenerator.blockStateCollector.accept(
                    BlockStateModelGenerator.createSlabBlockState(
                            group.SLAB,
                            BlockStateModelGenerator.createWeightedVariant(slabBottomModelId),
                            BlockStateModelGenerator.createWeightedVariant(slabTopModelId),
                            BlockStateModelGenerator.createWeightedVariant(cubeIdentifier)
                    )
            );
            blockStateModelGenerator.registerParentedItemModel(group.SLAB, slabBottomModelId);

            if (group.hasWall) {
                final Identifier wallInventoryModelId = Models.WALL_INVENTORY.upload(group.WALL, textureMap, blockStateModelGenerator.modelCollector);
                final Identifier wallPostModelId = Models.TEMPLATE_WALL_POST.upload(group.WALL, textureMap, blockStateModelGenerator.modelCollector);
                final Identifier wallSideModelId = Models.TEMPLATE_WALL_SIDE.upload(group.WALL, textureMap, blockStateModelGenerator.modelCollector);
                final Identifier wallSideTallModelId = Models.TEMPLATE_WALL_SIDE_TALL.upload(group.WALL, textureMap, blockStateModelGenerator.modelCollector);
                blockStateModelGenerator.blockStateCollector.accept(
                        BlockStateModelGenerator.createWallBlockState(
                                group.WALL,
                                BlockStateModelGenerator.createWeightedVariant(wallPostModelId),
                                BlockStateModelGenerator.createWeightedVariant(wallSideModelId),
                                BlockStateModelGenerator.createWeightedVariant(wallSideTallModelId)
                        )
                );
                blockStateModelGenerator.registerParentedItemModel(group.WALL, wallInventoryModelId);
            }

            if (group.hasFence) {
                final Identifier fencePostModelId = Models.FENCE_POST.upload(group.FENCE, textureMap, blockStateModelGenerator.modelCollector);
                final Identifier fenceSideModelId = Models.FENCE_SIDE.upload(group.FENCE, textureMap, blockStateModelGenerator.modelCollector);
                final Identifier fenceInventoryModelId = Models.FENCE_INVENTORY.upload(group.FENCE, textureMap, blockStateModelGenerator.modelCollector);
                blockStateModelGenerator.blockStateCollector.accept(
                        BlockStateModelGenerator.createFenceBlockState(
                                group.FENCE,
                                BlockStateModelGenerator.createWeightedVariant(fencePostModelId),
                                BlockStateModelGenerator.createWeightedVariant(fenceSideModelId)
                        )
                );
                blockStateModelGenerator.registerParentedItemModel(group.FENCE, fenceInventoryModelId);
            }
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (Item item : BrickItems.DATAGEN_NEEDED_ITEMS) {
            itemModelGenerator.register(item, Models.GENERATED);
        }
    }
}
