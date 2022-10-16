package io.github.justfoxx.death.datagen;

import io.github.justfoxx.death.data.BlockTags;
import io.github.justfoxx.death.data.ItemTags;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class Generate implements DataGeneratorEntrypoint {
    private static class DieBlocks extends FabricTagProvider<Block> {
        public DieBlocks(FabricDataGenerator dataGenerator) {
            super(dataGenerator, Registry.BLOCK);
        }

        @Override
        protected void generateTags() {
            FabricTagBuilder<Block> plantsTagBuilder = getOrCreateTagBuilder(BlockTags.PLANTS);
            BlockTags.plants.forEach(plantsTagBuilder::add);

            FabricTagBuilder<Block> deadCoralTagBuilder = getOrCreateTagBuilder(BlockTags.DEAD_CORAL);
            BlockTags.deadCoralBlocks.forEach(deadCoralTagBuilder::add);

            FabricTagBuilder<Block> coralTagBuilder = getOrCreateTagBuilder(BlockTags.CORAL);
            BlockTags.coralBlocks.forEach(coralTagBuilder::add);

            FabricTagBuilder<Block> dirtTagBuilder = getOrCreateTagBuilder(BlockTags.DIRT);
            BlockTags.dirt.forEach(dirtTagBuilder::add);

            FabricTagBuilder<Block> grassBlocksTagBuilder = getOrCreateTagBuilder(BlockTags.GRASS_BLOCKS);
            BlockTags.grassBlocks.forEach(grassBlocksTagBuilder::add);
        }
    }

    private static class UsedItems extends FabricTagProvider<Item> {
        public UsedItems(FabricDataGenerator dataGenerator) {
            super(dataGenerator, Registry.ITEM);
        }

        @Override
        protected void generateTags() {
            FabricTagBuilder<Item> hoesTagBuilder = getOrCreateTagBuilder(ItemTags.HOES);
            ItemTags.hoes.forEach(hoesTagBuilder::add);
        }
    }
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(DieBlocks::new);
        fabricDataGenerator.addProvider(UsedItems::new);
    }
}
