package org.jawbts.morebrick;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.jawbts.morebrick.block.BrokenBricksBlock;
import org.jawbts.morebrick.block.BrokenFenceBlock;
import org.jawbts.morebrick.block.BrokenStairsBlock;
import org.jawbts.morebrick.block.BrokenWallBlock;
import org.jawbts.morebrick.block.BrokenSlabBlock;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class BrickBlocks {
    public static final BlockItemPair BROKEN_CHISELED_STONE_BRICKS_PAIR = new BlockItemPair("broken_chiseled_stone_bricks", Blocks.CHISELED_STONE_BRICKS, Items.CHISELED_STONE_BRICKS);
    public static final BlockItemPair BROKEN_CRACKED_STONE_BRICKS = new BlockItemPair("broken_cracked_stone_bricks", Blocks.CRACKED_STONE_BRICKS, Items.CRACKED_STONE_BRICKS);
    public static final BlockItemPair BROKEN_CRACKED_POLISHED_BLACKSTONE_BRICKS = new BlockItemPair("broken_cracked_polished_blackstone_bricks", Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, Items.CRACKED_POLISHED_BLACKSTONE_BRICKS);
    public static final BlockItemPair BROKEN_CRACKED_DEEPSLATE_BRICKS = new BlockItemPair("broken_cracked_deepslate_bricks", Blocks.CRACKED_DEEPSLATE_BRICKS, Items.CRACKED_DEEPSLATE_BRICKS);
    public static final BlockItemPair BROKEN_CHISELED_TUFF_BRICKS = new BlockItemPair("broken_chiseled_tuff_bricks", Blocks.CHISELED_TUFF, Items.CHISELED_TUFF_BRICKS);
    public static final BlockItemPair BROKEN_QUARTZ_BRICKS = new BlockItemPair("broken_quartz_bricks", Blocks.QUARTZ_BRICKS, Items.QUARTZ_BRICKS);

    public static final BlockGroup BROKEN_BRICKS_GROUP = new BlockGroup("broken_brick", Blocks.BRICKS, Items.BRICK_WALL);
    public static final BlockGroup BROKEN_NETHER_BRICKS_GROUP = new BlockGroup("broken_nether_brick", Blocks.NETHER_BRICKS, Items.CHISELED_NETHER_BRICKS, true, true);
    public static final BlockGroup BROKEN_RESIN_BRICKS_GROUP = new BlockGroup("broken_resin_brick", Blocks.RESIN_BRICKS, Items.CHISELED_RESIN_BRICKS);
    public static final BlockGroup BROKEN_STONE_BRICKS_GROUP = new BlockGroup("broken_stone_brick", Blocks.STONE_BRICKS, BROKEN_CHISELED_STONE_BRICKS_PAIR.ITEM);
    public static final BlockGroup BROKEN_MOSSY_STONE_BRICKS_GROUP = new BlockGroup("broken_mossy_stone_brick", Blocks.MOSSY_STONE_BRICKS, Items.MOSSY_STONE_BRICK_WALL);
    public static final BlockGroup BROKEN_POLISHED_BLACKSTONE_BRICKS_GROUP = new BlockGroup("broken_polished_blackstone_brick", Blocks.POLISHED_BLACKSTONE_BRICKS, Items.POLISHED_BLACKSTONE_BRICK_WALL);
    public static final BlockGroup BROKEN_DEEPSLATE_BRICKS_GROUP = new BlockGroup("broken_deepslate_brick", Blocks.DEEPSLATE_BRICKS, Items.DEEPSLATE_BRICK_WALL);
    public static final BlockGroup BROKEN_TUFF_BRICKS_GROUP = new BlockGroup("broken_tuff_brick", Blocks.TUFF, BROKEN_CHISELED_TUFF_BRICKS.ITEM);
    public static final BlockGroup BROKEN_END_STONE_BRICKS_GROUP = new BlockGroup("broken_end_stone_brick", Blocks.END_STONE_BRICKS, Items.END_STONE_BRICK_WALL);
    public static final BlockGroup BROKEN_PRISMARINE_BRICKS_GROUP = new BlockGroup("broken_prismarine_brick", Blocks.PRISMARINE_BRICKS, Items.PRISMARINE_BRICK_SLAB, false, false);
    public static final BlockGroup BROKEN_MUD_BRICKS_GROUP = new BlockGroup("broken_mud_brick", Blocks.MUD_BRICKS, Items.MUD_BRICK_WALL);

    public static final List<BlockItemPair> BLOCK_ITEM_PAIRS = List.of(
            BROKEN_CHISELED_STONE_BRICKS_PAIR,
            BROKEN_CRACKED_STONE_BRICKS,
            BROKEN_CRACKED_POLISHED_BLACKSTONE_BRICKS,
            BROKEN_CRACKED_DEEPSLATE_BRICKS,
            BROKEN_CHISELED_TUFF_BRICKS,
            BROKEN_QUARTZ_BRICKS
    );

    public static final List<BlockItemPair> BROKEN_BLOCK_ITEM_PAIRS = List.of(
            BROKEN_CHISELED_STONE_BRICKS_PAIR,
            BROKEN_CRACKED_STONE_BRICKS,
            BROKEN_CRACKED_POLISHED_BLACKSTONE_BRICKS,
            BROKEN_CRACKED_DEEPSLATE_BRICKS,
            BROKEN_CHISELED_TUFF_BRICKS,
            BROKEN_QUARTZ_BRICKS
    );

    public static final List<BlockGroup> BLOCK_GROUPS = List.of(
            BROKEN_BRICKS_GROUP,
            BROKEN_NETHER_BRICKS_GROUP,
            BROKEN_RESIN_BRICKS_GROUP,
            BROKEN_STONE_BRICKS_GROUP,
            BROKEN_MOSSY_STONE_BRICKS_GROUP,
            BROKEN_POLISHED_BLACKSTONE_BRICKS_GROUP,
            BROKEN_DEEPSLATE_BRICKS_GROUP,
            BROKEN_TUFF_BRICKS_GROUP,
            BROKEN_END_STONE_BRICKS_GROUP,
            BROKEN_PRISMARINE_BRICKS_GROUP,
            BROKEN_MUD_BRICKS_GROUP
    );

    public static final List<BlockGroup> BROKEN_BLOCK_GROUPS = List.of(
            BROKEN_BRICKS_GROUP,
            BROKEN_NETHER_BRICKS_GROUP,
            BROKEN_RESIN_BRICKS_GROUP,
            BROKEN_STONE_BRICKS_GROUP,
            BROKEN_MOSSY_STONE_BRICKS_GROUP,
            BROKEN_POLISHED_BLACKSTONE_BRICKS_GROUP,
            BROKEN_DEEPSLATE_BRICKS_GROUP,
            BROKEN_TUFF_BRICKS_GROUP,
            BROKEN_END_STONE_BRICKS_GROUP,
            BROKEN_PRISMARINE_BRICKS_GROUP,
            BROKEN_MUD_BRICKS_GROUP
    );

    private static Pair<Block, Item> register(String name, Function<AbstractBlock.Settings, Block> blockFactory, Block originBlock) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("morebrick", name));

        AbstractBlock.Settings settings = AbstractBlock.Settings.copy(originBlock);
        settings.strength(originBlock.getHardness() / 2, originBlock.getBlastResistance() / 2);

        Block block = blockFactory.apply(settings.registryKey(blockKey));

        return new Pair<>(Registry.register(Registries.BLOCK, blockKey, block), register(name, block));
    }

    private static Item register(String name, Block block) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("morebrick", name));
        BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
        return Registry.register(Registries.ITEM, itemKey, blockItem);
    }

    public static void initialize() {
    }

    public static class BlockItemPair {
        public final Block BLOCK;
        public final Item ITEM;
        public final String name;

        public BlockItemPair(String name, Block originBlock, Item addAfter) {
            this.name = name;

            Pair<Block, Item> pair = register(name, BrokenBricksBlock::new, originBlock);
            BLOCK = pair.getLeft();
            ITEM = pair.getRight();

            ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register((itemGroup) -> itemGroup.addAfter(addAfter, ITEM));
        }
    }

    public static class BlockGroup {
        public final Block CUBE;
        public final Block STAIRS;
        public final Block SLAB;
        @Nullable
        public final Block WALL;
        @Nullable
        public final Block FENCE;
        public final List<Block> BLOCKS;


        public final Item CUBE_ITEM;
        public final Item STAIRS_ITEM;
        public final Item SLAB_ITEM;
        @Nullable
        public final Item WALL_ITEM;
        @Nullable
        public final Item FENCE_ITEM;

        public final boolean hasWall;
        public final boolean hasFence;
        public final String name;

        protected BlockGroup(String name, Block originBlock, Item addAfter) {
            this(name, originBlock, addAfter, true, false);
        }

        protected BlockGroup(String name, Block originBlock, Item addAfter, boolean hasWall, boolean hasFence) {
            assert (!hasFence || hasWall) : "A block group with a fence must also have a wall";

            this.hasWall = hasWall;
            this.hasFence = hasFence;
            this.name = name;

            Pair<Block, Item> cubePair = register(name + "s", BrokenBricksBlock::new, originBlock);
            Pair<Block, Item> stairsPair = register(name + "_stairs", settings -> new BrokenStairsBlock(originBlock.getDefaultState(), settings), originBlock);
            Pair<Block, Item> slabPair = register(name + "_slab", BrokenSlabBlock::new, originBlock);

            CUBE = cubePair.getLeft();
            STAIRS = stairsPair.getLeft();
            SLAB = slabPair.getLeft();

            CUBE_ITEM = cubePair.getRight();
            STAIRS_ITEM = stairsPair.getRight();
            SLAB_ITEM = slabPair.getRight();

            List<Block> blocks = new ArrayList<>();
            blocks.add(CUBE);
            blocks.add(STAIRS);
            blocks.add(SLAB);

            ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                    .register((itemGroup) -> itemGroup.addAfter(addAfter, CUBE_ITEM, STAIRS_ITEM, SLAB_ITEM));

            if (hasWall) {
                Pair<Block, Item> wallPair = register(name + "_wall", BrokenWallBlock::new, originBlock);

                WALL = wallPair.getLeft();
                WALL_ITEM = wallPair.getRight();

                ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                        .register((itemGroup) -> itemGroup.addAfter(SLAB_ITEM, WALL_ITEM));

                blocks.add(WALL);
            } else {
                WALL = null;
                WALL_ITEM = null;
            }

            if (hasFence) {
                Pair<Block, Item> fencePair = register(name + "_fence", BrokenFenceBlock::new, originBlock);

                FENCE = fencePair.getLeft();
                FENCE_ITEM = fencePair.getRight();

                ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                        .register((itemGroup) -> itemGroup.addAfter(WALL_ITEM, FENCE_ITEM));
                blocks.add(FENCE);
            } else {
                FENCE = null;
                FENCE_ITEM = null;
            }

            BLOCKS = Collections.unmodifiableList(blocks);
        }
    }
}
