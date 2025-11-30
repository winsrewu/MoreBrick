package org.jawbts.morebrick.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.InvertedLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.component.ComponentPredicateTypes;
import net.minecraft.predicate.component.ComponentsPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import org.jawbts.morebrick.BrickBlockItemMap;
import org.jawbts.morebrick.BrickItems;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MoreBrickBlockLootTableProvider extends FabricBlockLootTableProvider {
    public MoreBrickBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    private void addDropForBricks(Block block, Item brick, Item brokenBrick) {
        addDropIfSilkTouchOrNo(block, brick, brokenBrick, 4);
    }

    private void addDropForStairs(Block block, Item brick, Item brokenBrick) {
        addDropIfSilkTouchOrNo(block, brick, brokenBrick, 3);
    }

    private void addDropForWalls(Block block, Item brick, Item brokenBrick) {
        addDropIfSilkTouchOrNo(block, brick, brokenBrick, 3);
    }

    private void addDropForFences(Block block, Item brick, Item brokenBrick) {
        addDropIfSilkTouchOrNo(block, brick, brokenBrick, 2);
    }

    private void addDropForSlabs(Block block, Item brick, Item brokenBrick) {
        addDropIfSilkTouchOrNoForSlabs(block, brick, brokenBrick);
    }

    /**
     * A block will always drop the amount of bricks specified by bricksPerBlock.
     * If a block is mined with silk touch, it drops only the perfect brick items,
     * otherwise it drops 50% of the brick items and 50% of the broken brick items.
     * @param block the block to add drops to
     * @param brick the perfect brick item
     * @param brokenBrick the broken brick item
     * @param bricksPerBlock the number of bricks per block
     */
    private void addDropIfSilkTouchOrNo(Block block, Item brick, Item brokenBrick, int bricksPerBlock) {
        addDrop(
                block,
                LootTable.builder().pool(
                        LootPool.builder().conditionally(
                                MatchToolLootCondition.builder(ItemPredicate.Builder.create().components(ComponentsPredicate.Builder.create().partial(ComponentPredicateTypes.ENCHANTMENTS, EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(this.registries.getOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH), NumberRange.IntRange.atLeast(1))))).build()))
                        )
                                .conditionally(SurvivesExplosionLootCondition.builder())
                                .rolls(ConstantLootNumberProvider.create(bricksPerBlock))
                                .with(ItemEntry.builder(brick))
                ).pool(
                        LootPool.builder().conditionally(InvertedLootCondition.builder(
                                        MatchToolLootCondition.builder(ItemPredicate.Builder.create().components(ComponentsPredicate.Builder.create().partial(ComponentPredicateTypes.ENCHANTMENTS, EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(this.registries.getOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH), NumberRange.IntRange.atLeast(1))))).build()))
                                ))
                                .conditionally(SurvivesExplosionLootCondition.builder())
                                .rolls(ConstantLootNumberProvider.create(bricksPerBlock))
                                .with(ItemEntry.builder(brokenBrick))
                                .with(ItemEntry.builder(brick))
                )
        );
    }

    private void addDropIfSilkTouchOrNoForSlabs(Block block, Item brick, Item brokenBrick) {
        addDrop(
                block,
                LootTable.builder().pool(
                        LootPool.builder().conditionally(
                                        MatchToolLootCondition.builder(ItemPredicate.Builder.create().components(ComponentsPredicate.Builder.create().partial(ComponentPredicateTypes.ENCHANTMENTS, EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(this.registries.getOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH), NumberRange.IntRange.atLeast(1))))).build()))
                                )
                                .conditionally(SurvivesExplosionLootCondition.builder())
                                .conditionally(BlockStatePropertyLootCondition.builder(block)
                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))
                                )
                                .rolls(ConstantLootNumberProvider.create(4))
                                .with(ItemEntry.builder(brick))
                ).pool(
                        LootPool.builder().conditionally(InvertedLootCondition.builder(
                                        MatchToolLootCondition.builder(ItemPredicate.Builder.create().components(ComponentsPredicate.Builder.create().partial(ComponentPredicateTypes.ENCHANTMENTS, EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(this.registries.getOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH), NumberRange.IntRange.atLeast(1))))).build()))
                                ))
                                .conditionally(SurvivesExplosionLootCondition.builder())
                                .conditionally(BlockStatePropertyLootCondition.builder(block)
                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))
                                )
                                .rolls(ConstantLootNumberProvider.create(4))
                                .with(ItemEntry.builder(brokenBrick))
                                .with(ItemEntry.builder(brick))
                ).pool(
                        LootPool.builder().conditionally(
                                        MatchToolLootCondition.builder(ItemPredicate.Builder.create().components(ComponentsPredicate.Builder.create().partial(ComponentPredicateTypes.ENCHANTMENTS, EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(this.registries.getOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH), NumberRange.IntRange.atLeast(1))))).build()))
                                )
                                .conditionally(SurvivesExplosionLootCondition.builder())
                                .conditionally(InvertedLootCondition.builder(BlockStatePropertyLootCondition.builder(block)
                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))
                                ))
                                .rolls(ConstantLootNumberProvider.create(4))
                                .with(ItemEntry.builder(brick))
                ).pool(
                        LootPool.builder().conditionally(InvertedLootCondition.builder(
                                        MatchToolLootCondition.builder(ItemPredicate.Builder.create().components(ComponentsPredicate.Builder.create().partial(ComponentPredicateTypes.ENCHANTMENTS, EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(this.registries.getOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH), NumberRange.IntRange.atLeast(1))))).build()))
                                ))
                                .conditionally(SurvivesExplosionLootCondition.builder())
                                .conditionally(InvertedLootCondition.builder(BlockStatePropertyLootCondition.builder(block)
                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))
                                ))
                                .rolls(ConstantLootNumberProvider.create(4))
                                .with(ItemEntry.builder(brokenBrick))
                                .with(ItemEntry.builder(brick))
                )
        );
    }

    @Override
    public void generate() {
        for (Map.Entry<Block, Item> entry : BrickBlockItemMap.BLOCK_ITEM_MAP.entrySet()) {
            Block block = entry.getKey();
            Item brick = entry.getValue();
            Item brokenBrick = BrickItems.INTACT_TO_BROKEN_ITEMS.get(brick);

            switch (block) {
                case StairsBlock stairsBlock -> addDropForStairs(block, brick, brokenBrick);
                case WallBlock wallBlock -> addDropForWalls(block, brick, brokenBrick);
                case FenceBlock fenceBlock -> addDropForFences(block, brick, brokenBrick);
                case SlabBlock slabBlock -> addDropForSlabs(block, brick, brokenBrick);
                case null, default -> addDropForBricks(block, brick, brokenBrick);
            }
        }

        for (Map.Entry<Block, Item> entry : BrickBlockItemMap.BROKEN_BLOCK_ITEM_MAP.entrySet()) {
            Block block = entry.getKey();
            Item brokenBrick = BrickItems.INTACT_TO_BROKEN_ITEMS.get(entry.getValue());

            switch (block) {
                case StairsBlock stairsBlock -> addDropForStairs(block, brokenBrick, brokenBrick);
                case WallBlock wallBlock -> addDropForWalls(block, brokenBrick, brokenBrick);
                case FenceBlock fenceBlock -> addDropForFences(block, brokenBrick, brokenBrick);
                case SlabBlock slabBlock -> addDropForSlabs(block, brokenBrick, brokenBrick);
                case null, default -> addDropForBricks(block, brokenBrick, brokenBrick);
            }
        }
    }
}
