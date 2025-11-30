package org.jawbts.morebrick;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickBlockItemMap {
    // only stores the original brick item, not the broken one
    public static final Map<Block, Item> BLOCK_ITEM_MAP = new HashMap<>();
    public static final Map<Item, Block> ITEM_BLOCK_MAP = new HashMap<>();
    public static final Map<Block, Item> BROKEN_BLOCK_ITEM_MAP = new HashMap<>();
    public static final Map<Item, Block> BROKEN_ITEM_BLOCK_MAP = new HashMap<>();

    private static void put(Item item, Block... blocks) {
        for (Block block : blocks) {
            BLOCK_ITEM_MAP.put(block, item);
            ITEM_BLOCK_MAP.put(item, block);
        }
    }

    private static void putBroken(Item item, Block... blocks) {
        for (Block block : blocks) {
            BROKEN_BLOCK_ITEM_MAP.put(block, item);
            BROKEN_ITEM_BLOCK_MAP.put(item, block);
        }
    }

    private static void putBroken(Item item, List<Block> blocks) {
        for (Block block : blocks) {
            BROKEN_BLOCK_ITEM_MAP.put(block, item);
            BROKEN_ITEM_BLOCK_MAP.put(item, block);
        }
    }

    public static boolean isIntactBrick(Item item) {
        return BLOCK_ITEM_MAP.containsValue(item);
    }

    public static boolean isBrokenBrick(Item item) {
        return BROKEN_BLOCK_ITEM_MAP.containsValue(item);
    }

    public static boolean isIntactBricks(Block block) {
        return BLOCK_ITEM_MAP.containsKey(block);
    }

    public static boolean isBrokenBricks(Block block) {
        return BROKEN_BLOCK_ITEM_MAP.containsKey(block);
    }

    static {
        putBroken(BrickItems.CHISELED_STONE_BRICK, BrickBlocks.BROKEN_CHISELED_STONE_BRICKS_PAIR.BLOCK);
        putBroken(BrickItems.CRACKED_STONE_BRICK, BrickBlocks.BROKEN_CRACKED_STONE_BRICKS.BLOCK);
        putBroken(BrickItems.CRACKED_POLISHED_BLACKSTONE_BRICK, BrickBlocks.BROKEN_CRACKED_POLISHED_BLACKSTONE_BRICKS.BLOCK);
        putBroken(BrickItems.CRACKED_DEEPSLATE_BRICK, BrickBlocks.BROKEN_CRACKED_DEEPSLATE_BRICKS.BLOCK);
        putBroken(BrickItems.CHISELED_TUFF_BRICK, BrickBlocks.BROKEN_CHISELED_TUFF_BRICKS.BLOCK);
        putBroken(BrickItems.QUARTZ_BRICK, BrickBlocks.BROKEN_QUARTZ_BRICKS.BLOCK);

        putBroken(Items.BRICK, BrickBlocks.BROKEN_BRICKS_GROUP.BLOCKS);
        putBroken(Items.NETHER_BRICK, BrickBlocks.BROKEN_NETHER_BRICKS_GROUP.BLOCKS);
        putBroken(Items.RESIN_BRICK, BrickBlocks.BROKEN_RESIN_BRICKS_GROUP.BLOCKS);
        putBroken(BrickItems.STONE_BRICK, BrickBlocks.BROKEN_STONE_BRICKS_GROUP.BLOCKS);
        putBroken(BrickItems.MOSSY_STONE_BRICK, BrickBlocks.BROKEN_MOSSY_STONE_BRICKS_GROUP.BLOCKS);
        putBroken(BrickItems.POLISHED_BLACKSTONE_BRICK, BrickBlocks.BROKEN_POLISHED_BLACKSTONE_BRICKS_GROUP.BLOCKS);
        putBroken(BrickItems.DEEPSLATE_BRICK, BrickBlocks.BROKEN_DEEPSLATE_BRICKS_GROUP.BLOCKS);
        putBroken(BrickItems.TUFF_BRICK, BrickBlocks.BROKEN_TUFF_BRICKS_GROUP.BLOCKS);
        putBroken(BrickItems.END_STONE_BRICK, BrickBlocks.BROKEN_END_STONE_BRICKS_GROUP.BLOCKS);
        putBroken(BrickItems.PRISMARINE_BRICK, BrickBlocks.BROKEN_PRISMARINE_BRICKS_GROUP.BLOCKS);
        putBroken(BrickItems.MUD_BRICK, BrickBlocks.BROKEN_MUD_BRICKS_GROUP.BLOCKS);

        put(BrickItems.STONE_BRICK, Blocks.STONE_BRICKS, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICK_WALL);
        put(BrickItems.CHISELED_STONE_BRICK, Blocks.CHISELED_STONE_BRICKS);
        put(BrickItems.CRACKED_STONE_BRICK, Blocks.CRACKED_STONE_BRICKS);
        put(BrickItems.MOSSY_STONE_BRICK, Blocks.MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_WALL);
        put(BrickItems.DEEPSLATE_BRICK, Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICK_SLAB, Blocks.DEEPSLATE_BRICK_WALL);
        put(BrickItems.CRACKED_DEEPSLATE_BRICK, Blocks.CRACKED_DEEPSLATE_BRICKS);
        put(BrickItems.TUFF_BRICK, Blocks.TUFF_BRICKS, Blocks.TUFF_BRICK_STAIRS, Blocks.TUFF_BRICK_SLAB, Blocks.TUFF_BRICK_WALL);
        put(BrickItems.CHISELED_TUFF_BRICK, Blocks.CHISELED_TUFF_BRICKS);
        put(Items.BRICK, Blocks.BRICKS, Blocks.BRICK_STAIRS, Blocks.BRICK_SLAB, Blocks.BRICK_WALL);
        put(BrickItems.MUD_BRICK, Blocks.MUD_BRICKS, Blocks.MUD_BRICK_STAIRS, Blocks.MUD_BRICK_SLAB, Blocks.MUD_BRICK_WALL);
        put(Items.RESIN_BRICK, Blocks.RESIN_BRICKS, Blocks.RESIN_BRICK_STAIRS, Blocks.RESIN_BRICK_SLAB, Blocks.RESIN_BRICK_WALL);
        put(BrickItems.PRISMARINE_BRICK, Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_BRICK_SLAB);
        put(Items.NETHER_BRICK, Blocks.NETHER_BRICKS, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_BRICK_SLAB, Blocks.NETHER_BRICK_WALL, Blocks.NETHER_BRICK_FENCE);
        put(BrickItems.POLISHED_BLACKSTONE_BRICK, Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_WALL);
        put(BrickItems.CRACKED_POLISHED_BLACKSTONE_BRICK, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        put(BrickItems.END_STONE_BRICK, Blocks.END_STONE_BRICKS, Blocks.END_STONE_BRICK_STAIRS, Blocks.END_STONE_BRICK_SLAB, Blocks.END_STONE_BRICK_WALL);
        put(BrickItems.QUARTZ_BRICK, Blocks.QUARTZ_BRICKS);
        put(BrickItems.INFESTED_STONE_BRICK, Blocks.INFESTED_STONE_BRICKS);
        put(BrickItems.INFESTED_MOSSY_STONE_BRICK, Blocks.INFESTED_MOSSY_STONE_BRICKS);
        put(BrickItems.INFESTED_CHISELED_STONE_BRICK, Blocks.INFESTED_CHISELED_STONE_BRICKS);
    }
}
