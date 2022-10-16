package io.github.justfoxx.death.data;

import io.github.justfoxx.death.Global;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class ItemTags {
    public static final List<Item> hoes = List.of(
            Items.DIAMOND_HOE,
            Items.IRON_HOE,
            Items.STONE_HOE,
            Items.GOLDEN_HOE,
            Items.NETHERITE_HOE,
            Items.WOODEN_HOE
    );

    public static final TagKey<Item> HOES = TagKey.of(Registry.ITEM_KEY, Global.id("hoes"));
}
