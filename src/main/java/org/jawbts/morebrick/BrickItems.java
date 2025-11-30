package org.jawbts.morebrick;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

public class BrickItems {
    public static final Item STONE_BRICK = register("stone_brick", Item::new, new Item.Settings());
    public static final Item CHISELED_STONE_BRICK = register("chiseled_stone_brick", Item::new, new Item.Settings());
    public static final Item CRACKED_STONE_BRICK = register("cracked_stone_brick", Item::new, new Item.Settings());
    public static final Item MOSSY_STONE_BRICK = register("mossy_stone_brick", Item::new, new Item.Settings());
    public static final Item INFESTED_STONE_BRICK = register("infested_stone_brick", Item::new, new Item.Settings());
    public static final Item INFESTED_CHISELED_STONE_BRICK = register("infested_chiseled_stone_brick", Item::new, new Item.Settings());
    public static final Item INFESTED_CRACKED_STONE_BRICK = register("infested_cracked_stone_brick", Item::new, new Item.Settings());
    public static final Item INFESTED_MOSSY_STONE_BRICK = register("infested_mossy_stone_brick", Item::new, new Item.Settings());
    public static final Item POLISHED_BLACKSTONE_BRICK = register("polished_blackstone_brick", Item::new, new Item.Settings());
    public static final Item CRACKED_POLISHED_BLACKSTONE_BRICK = register("cracked_polished_blackstone_brick", Item::new, new Item.Settings());
    public static final Item DEEPSLATE_BRICK = register("deepslate_brick", Item::new, new Item.Settings());
    public static final Item CRACKED_DEEPSLATE_BRICK = register("cracked_deepslate_brick", Item::new, new Item.Settings());
    public static final Item TUFF_BRICK = register("tuff_brick", Item::new, new Item.Settings());
    public static final Item CHISELED_TUFF_BRICK = register("chiseled_tuff_brick", Item::new, new Item.Settings());
    public static final Item END_STONE_BRICK = register("end_stone_brick", Item::new, new Item.Settings());
    public static final Item PRISMARINE_BRICK = register("prismarine_brick", Item::new, new Item.Settings());
    public static final Item QUARTZ_BRICK = register("quartz_brick", Item::new, new Item.Settings());
    public static final Item MUD_BRICK = register("mud_brick", Item::new, new Item.Settings());

    public static final Item BROKEN_BRICK = register("broken_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_NETHER_BRICK = register("broken_nether_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_RESIN_BRICK = register("broken_resin_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_STONE_BRICK = register("broken_stone_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_CHISELED_STONE_BRICK = register("broken_chiseled_stone_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_CRACKED_STONE_BRICK = register("broken_cracked_stone_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_MOSSY_STONE_BRICK = register("broken_mossy_stone_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_INFESTED_STONE_BRICK = register("broken_infested_stone_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_INFESTED_CHISELED_STONE_BRICK = register("broken_infested_chiseled_stone_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_INFESTED_CRACKED_STONE_BRICK = register("broken_infested_cracked_stone_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_INFESTED_MOSSY_STONE_BRICK = register("broken_infested_mossy_stone_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_POLISHED_BLACKSTONE_BRICK = register("broken_polished_blackstone_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_CRACKED_POLISHED_BLACKSTONE_BRICK = register("broken_cracked_polished_blackstone_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_DEEPSLATE_BRICK = register("broken_deepslate_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_CRACKED_DEEPSLATE_BRICK = register("broken_cracked_deepslate_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_TUFF_BRICK = register("broken_tuff_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_CHISELED_TUFF_BRICK = register("broken_chiseled_tuff_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_END_STONE_BRICK = register("broken_end_stone_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_PRISMARINE_BRICK = register("broken_prismarine_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_QUARTZ_BRICK = register("broken_quartz_brick", Item::new, new Item.Settings());
    public static final Item BROKEN_MUD_BRICK = register("broken_mud_brick", Item::new, new Item.Settings());

    public static final List<Item> ITEMS = List.of(
            STONE_BRICK,
            CHISELED_STONE_BRICK,
            CRACKED_STONE_BRICK,
            MOSSY_STONE_BRICK,
            INFESTED_STONE_BRICK,
            INFESTED_CHISELED_STONE_BRICK,
            INFESTED_CRACKED_STONE_BRICK,
            INFESTED_MOSSY_STONE_BRICK,
            POLISHED_BLACKSTONE_BRICK,
            CRACKED_POLISHED_BLACKSTONE_BRICK,
            DEEPSLATE_BRICK,
            CRACKED_DEEPSLATE_BRICK,
            TUFF_BRICK,
            CHISELED_TUFF_BRICK,
            END_STONE_BRICK,
            PRISMARINE_BRICK,
            QUARTZ_BRICK,
            MUD_BRICK,
            BROKEN_BRICK,
            BROKEN_NETHER_BRICK,
            BROKEN_RESIN_BRICK,
            BROKEN_STONE_BRICK,
            BROKEN_CHISELED_STONE_BRICK,
            BROKEN_CRACKED_STONE_BRICK,
            BROKEN_MOSSY_STONE_BRICK,
            BROKEN_INFESTED_STONE_BRICK,
            BROKEN_INFESTED_CHISELED_STONE_BRICK,
            BROKEN_INFESTED_CRACKED_STONE_BRICK,
            BROKEN_INFESTED_MOSSY_STONE_BRICK,
            BROKEN_POLISHED_BLACKSTONE_BRICK,
            BROKEN_CRACKED_POLISHED_BLACKSTONE_BRICK,
            BROKEN_DEEPSLATE_BRICK,
            BROKEN_CRACKED_DEEPSLATE_BRICK,
            BROKEN_TUFF_BRICK,
            BROKEN_CHISELED_TUFF_BRICK,
            BROKEN_END_STONE_BRICK,
            BROKEN_PRISMARINE_BRICK,
            BROKEN_QUARTZ_BRICK,
            BROKEN_MUD_BRICK
    );

    public static final List<Item> DATAGEN_NEEDED_ITEMS = List.of(
            STONE_BRICK,
            CHISELED_STONE_BRICK,
            CRACKED_STONE_BRICK,
            MOSSY_STONE_BRICK,
            POLISHED_BLACKSTONE_BRICK,
            CRACKED_POLISHED_BLACKSTONE_BRICK,
            DEEPSLATE_BRICK,
            CRACKED_DEEPSLATE_BRICK,
            TUFF_BRICK,
            CHISELED_TUFF_BRICK,
            END_STONE_BRICK,
            PRISMARINE_BRICK,
            QUARTZ_BRICK,
            MUD_BRICK,
            BROKEN_BRICK,
            BROKEN_NETHER_BRICK,
            BROKEN_RESIN_BRICK,
            BROKEN_STONE_BRICK,
            BROKEN_CHISELED_STONE_BRICK,
            BROKEN_CRACKED_STONE_BRICK,
            BROKEN_MOSSY_STONE_BRICK,
            BROKEN_POLISHED_BLACKSTONE_BRICK,
            BROKEN_CRACKED_POLISHED_BLACKSTONE_BRICK,
            BROKEN_DEEPSLATE_BRICK,
            BROKEN_CRACKED_DEEPSLATE_BRICK,
            BROKEN_TUFF_BRICK,
            BROKEN_CHISELED_TUFF_BRICK,
            BROKEN_END_STONE_BRICK,
            BROKEN_PRISMARINE_BRICK,
            BROKEN_QUARTZ_BRICK,
            BROKEN_MUD_BRICK
    );

    public static final Map<Item, Item> INTACT_TO_BROKEN_ITEMS = new HashMap<>();

    static {
        INTACT_TO_BROKEN_ITEMS.put(Items.BRICK, BROKEN_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(Items.NETHER_BRICK, BROKEN_NETHER_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(Items.RESIN_BRICK, BROKEN_RESIN_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(STONE_BRICK, BROKEN_STONE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(CHISELED_STONE_BRICK, BROKEN_CHISELED_STONE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(CRACKED_STONE_BRICK, BROKEN_CRACKED_STONE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(MOSSY_STONE_BRICK, BROKEN_MOSSY_STONE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(INFESTED_STONE_BRICK, BROKEN_INFESTED_STONE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(INFESTED_CHISELED_STONE_BRICK, BROKEN_INFESTED_CHISELED_STONE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(INFESTED_CRACKED_STONE_BRICK, BROKEN_INFESTED_CRACKED_STONE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(INFESTED_MOSSY_STONE_BRICK, BROKEN_INFESTED_MOSSY_STONE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(POLISHED_BLACKSTONE_BRICK, BROKEN_POLISHED_BLACKSTONE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(CRACKED_POLISHED_BLACKSTONE_BRICK, BROKEN_CRACKED_POLISHED_BLACKSTONE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(DEEPSLATE_BRICK, BROKEN_DEEPSLATE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(CRACKED_DEEPSLATE_BRICK, BROKEN_CRACKED_DEEPSLATE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(TUFF_BRICK, BROKEN_TUFF_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(CHISELED_TUFF_BRICK, BROKEN_CHISELED_TUFF_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(END_STONE_BRICK, BROKEN_END_STONE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(PRISMARINE_BRICK, BROKEN_PRISMARINE_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(QUARTZ_BRICK, BROKEN_QUARTZ_BRICK);
        INTACT_TO_BROKEN_ITEMS.put(MUD_BRICK, BROKEN_MUD_BRICK);
    }

    private static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        final RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("morebrick", name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> {
                    IntStream.range(0, ITEMS.size())
                            .mapToObj(i -> ITEMS.get(ITEMS.size() - 1 - i))
                            .forEach(item -> itemGroup.addAfter(Items.RESIN_BRICK, item));
                });
    }
}
