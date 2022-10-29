package io.github.justfoxx.death.data;

import io.github.justfoxx.death.Global;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class ItemTags {
    public static final TagKey<Item> HOES = ConventionalItemTags.HOES;
    public static final TagKey<Item> SWORDS = ConventionalItemTags.SWORDS;
    public static final TagKey<Item> ROTTEN_FOOD = TagKey.of(Registry.ITEM_KEY, Global.id("rotten_food"));
    public static final List<Item> rottenFoodList = List.of(
            Items.ROTTEN_FLESH,
            Items.POISONOUS_POTATO,
            Items.SPIDER_EYE
    );
}
