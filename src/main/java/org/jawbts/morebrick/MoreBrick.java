package org.jawbts.morebrick;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class MoreBrick implements ModInitializer {
    public static final RegistryEntry<StatusEffect> BLUDGEONED =
            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of("morebrick", "bludgeoned"), new BludgeonedEffect());


    @Override
    public void onInitialize() {
        BrickItems.initialize();
        BrickBlocks.initialize();
    }
}
