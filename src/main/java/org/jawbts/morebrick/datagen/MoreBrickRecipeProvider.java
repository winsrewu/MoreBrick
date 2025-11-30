package org.jawbts.morebrick.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.*;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import org.jawbts.morebrick.BrickBlockItemMap;
import org.jawbts.morebrick.BrickItems;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MoreBrickRecipeProvider extends FabricRecipeProvider {
    public MoreBrickRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                for (Map.Entry<Block, Item> entry : BrickBlockItemMap.BLOCK_ITEM_MAP.entrySet()) {
                    Block block = entry.getKey();
                    Item brick = entry.getValue();

                    switch (block) {
                        case StairsBlock stairsBlock -> createShaped(
                                RecipeCategory.BUILDING_BLOCKS,
                                block,
                                1
                        ).pattern("# ").pattern("##")
                                .input('#', brick)
                                .criterion(hasItem(brick), conditionsFromItem(brick))
                                .offerTo(exporter);
                        case WallBlock wallBlock -> createShaped(
                                RecipeCategory.BUILDING_BLOCKS,
                                block,
                                2
                        ).pattern("###").pattern("###")
                                .input('#', brick)
                                .criterion(hasItem(brick), conditionsFromItem(brick))
                                .offerTo(exporter);
                        case FenceBlock fenceBlock -> createShaped(
                                RecipeCategory.DECORATIONS,
                                block,
                                2
                        ).pattern("###").pattern("# #")
                                .input('#', brick)
                                .criterion(hasItem(brick), conditionsFromItem(brick))
                                .offerTo(exporter);
                        case SlabBlock slabBlock -> createShaped(
                                RecipeCategory.BUILDING_BLOCKS,
                                block,
                                1
                        ).pattern("##")
                                .input('#', brick)
                                .criterion(hasItem(brick), conditionsFromItem(brick))
                                .offerTo(exporter);
                        case null, default -> createShaped(
                                RecipeCategory.BUILDING_BLOCKS,
                                block,
                                1
                        ).pattern("##").pattern("##")
                                .input('#', brick)
                                .criterion(hasItem(brick), conditionsFromItem(brick))
                                .offerTo(exporter);
                    }
                }

                for (Map.Entry<Block, Item> entry : BrickBlockItemMap.BROKEN_BLOCK_ITEM_MAP.entrySet()) {
                    Block block = entry.getKey();
                    Item brokenBrick = BrickItems.INTACT_TO_BROKEN_ITEMS.get(entry.getValue());

                    switch (block) {
                        case StairsBlock stairsBlock -> createShaped(
                                RecipeCategory.BUILDING_BLOCKS,
                                block,
                                1
                        ).pattern("# ").pattern("##")
                                .input('#', brokenBrick)
                                .criterion(hasItem(brokenBrick), conditionsFromItem(brokenBrick))
                                .offerTo(exporter);
                        case WallBlock wallBlock -> createShaped(
                                RecipeCategory.BUILDING_BLOCKS,
                                block,
                                2
                        ).pattern("###").pattern("###")
                                .input('#', brokenBrick)
                                .criterion(hasItem(brokenBrick), conditionsFromItem(brokenBrick))
                                .offerTo(exporter);
                        case FenceBlock fenceBlock -> createShaped(
                                RecipeCategory.DECORATIONS,
                                block,
                                2
                        ).pattern("###").pattern("# #")
                                .input('#', brokenBrick)
                                .criterion(hasItem(brokenBrick), conditionsFromItem(brokenBrick))
                                .offerTo(exporter);
                        case SlabBlock slabBlock -> createShaped(
                                RecipeCategory.BUILDING_BLOCKS,
                                block,
                                1
                        ).pattern("##")
                                .input('#', brokenBrick)
                                .criterion(hasItem(brokenBrick), conditionsFromItem(brokenBrick))
                                .offerTo(exporter);
                        case null, default -> createShaped(
                                RecipeCategory.BUILDING_BLOCKS,
                                block,
                                1
                        ).pattern("##").pattern("##")
                                .input('#', brokenBrick)
                                .criterion(hasItem(brokenBrick), conditionsFromItem(brokenBrick))
                                .offerTo(exporter);
                    }
                }
            }
        };
    }

    @Override
    public String getName() {
        return "MoreBrickRecipeProvider";
    }
}
