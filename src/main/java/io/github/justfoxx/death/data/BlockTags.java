package io.github.justfoxx.death.data;

import io.github.justfoxx.death.Global;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class BlockTags {
    public static final List<Block> plants = List.of(
            Blocks.AZALEA,
            Blocks.FLOWERING_AZALEA,
            Blocks.BAMBOO,
            Blocks.BAMBOO_SAPLING,
            Blocks.BEETROOTS,
            Blocks.BIG_DRIPLEAF,
            Blocks.SMALL_DRIPLEAF,
            Blocks.BIG_DRIPLEAF_STEM,
            Blocks.CACTUS,
            Blocks.CARROTS,
            Blocks.FERN,
            Blocks.LARGE_FERN,
            Blocks.DANDELION,
            Blocks.POPPY,
            Blocks.BLUE_ORCHID,
            Blocks.ALLIUM,
            Blocks.AZURE_BLUET,
            Blocks.RED_TULIP,
            Blocks.ORANGE_TULIP,
            Blocks.WHITE_TULIP,
            Blocks.PINK_TULIP,
            Blocks.OXEYE_DAISY,
            Blocks.CORNFLOWER,
            Blocks.LILY_OF_THE_VALLEY,
            Blocks.WITHER_ROSE,
            Blocks.SUNFLOWER,
            Blocks.LILAC,
            Blocks.ROSE_BUSH,
            Blocks.PEONY,
            Blocks.GRASS,
            Blocks.TALL_GRASS,
            Blocks.PUMPKIN_STEM,
            Blocks.MELON_STEM,
            Blocks.POTATOES,
            Blocks.ACACIA_SAPLING,
            Blocks.BIRCH_SAPLING,
            Blocks.DARK_OAK_SAPLING,
            Blocks.JUNGLE_SAPLING,
            Blocks.OAK_SAPLING,
            Blocks.SPRUCE_SAPLING,
            Blocks.SWEET_BERRY_BUSH
    );

    public static final List<Block> deadCoralBlocks = List.of(
            Blocks.DEAD_BRAIN_CORAL_BLOCK,
            Blocks.DEAD_BUBBLE_CORAL_BLOCK,
            Blocks.DEAD_FIRE_CORAL_BLOCK,
            Blocks.DEAD_HORN_CORAL_BLOCK,
            Blocks.DEAD_TUBE_CORAL_BLOCK
    );

    public static final List<Block> coralBlocks = List.of(
            Blocks.BRAIN_CORAL_BLOCK,
            Blocks.BUBBLE_CORAL_BLOCK,
            Blocks.FIRE_CORAL_BLOCK,
            Blocks.HORN_CORAL_BLOCK,
            Blocks.TUBE_CORAL_BLOCK
    );

    public static final List<Block> dirt = List.of(
            Blocks.DIRT
    );

    public static final List<Block> grassBlocks = List.of(
            Blocks.GRASS_BLOCK,
            Blocks.PODZOL,
            Blocks.MYCELIUM
    );

    public static final TagKey<Block> PLANTS = TagKey.of(Registry.BLOCK_KEY, Global.id("plants"));
    public static final TagKey<Block> DEAD_CORAL = TagKey.of(Registry.BLOCK_KEY, Global.id("dead_coral"));
    public static final TagKey<Block> CORAL = TagKey.of(Registry.BLOCK_KEY, Global.id("coral"));
    public static final TagKey<Block> DIRT = TagKey.of(Registry.BLOCK_KEY, Global.id("dirt"));
    public static final TagKey<Block> GRASS_BLOCKS = TagKey.of(Registry.BLOCK_KEY, Global.id("grass_blocks"));
}
