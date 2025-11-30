package org.jawbts.morebrick.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MoreBrickDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(MoreBrickModelProvider::new);
        pack.addProvider(MoreBrickBlockTagProvider::new);
        pack.addProvider(MoreBrickBlockLootTableProvider::new);
        pack.addProvider(MoreBrickRecipeProvider::new);
    }
}
