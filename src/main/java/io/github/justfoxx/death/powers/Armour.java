package io.github.justfoxx.death.powers;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class Armour extends BasePower {
    private final DyeableArmorItem boots = (DyeableArmorItem) Items.LEATHER_BOOTS;
    private final DyeableArmorItem leggings = (DyeableArmorItem) Items.LEATHER_LEGGINGS;
    private final DyeableArmorItem chestplate = (DyeableArmorItem) Items.LEATHER_CHESTPLATE;
    private final DyeableArmorItem helmet = (DyeableArmorItem) Items.LEATHER_HELMET;

    private final Enchantment protection = Enchantments.PROTECTION;
    private final Enchantment curseOfBinding = Enchantments.BINDING_CURSE;
    private final Enchantment curseOfVanishing = Enchantments.VANISHING_CURSE;

    private final int protectionLevel = 3;
    private final int curseOfBindingLevel = 1;
    private final int curseOfVanishingLevel = 1;

    private final int color = 0x000000;

    public Armour(Identifier id) {
        super(id);
    }

    private boolean defaultItem(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot) {
        itemStack.getOrCreateSubNbt("display").putInt("color", this.color);
        itemStack.getOrCreateNbt().putInt("HideFlags", 101);
        itemStack.getOrCreateNbt().putInt("Unbreakable", 1);
        NbtList loreList = new NbtList();
        loreList.add(NbtString.of(Text.Serializer.toJson(Text.literal("Death's Armour").formatted(Formatting.RED))));
        itemStack.getOrCreateSubNbt("display").put("Lore", loreList);
        itemStack.addEnchantment(this.protection, this.protectionLevel);
        itemStack.addEnchantment(this.curseOfBinding, this.curseOfBindingLevel);
        itemStack.addEnchantment(this.curseOfVanishing, this.curseOfVanishingLevel);
        return entity.getEquippedStack(slot).isItemEqual(itemStack);
    }

    private void equipBoots(LivingEntity entity) {
        ItemStack itemStack = new ItemStack(this.boots);
        itemStack.setCustomName(Text.literal("Boots of Death").formatted(Formatting.DARK_GRAY, Formatting.BOLD));
        if (defaultItem(entity, itemStack, EquipmentSlot.FEET)) return;
        entity.equipStack(EquipmentSlot.FEET, itemStack);
    }

    private void equipLeggings(LivingEntity entity) {
        ItemStack itemStack = new ItemStack(this.leggings);
        itemStack.setCustomName(Text.literal("Leggings of Death").formatted(Formatting.DARK_GRAY, Formatting.BOLD));
        if (defaultItem(entity, itemStack, EquipmentSlot.LEGS)) return;
        entity.equipStack(EquipmentSlot.LEGS, itemStack);
    }

    private void equipChestplate(LivingEntity entity) {
        ItemStack itemStack = new ItemStack(this.chestplate);
        itemStack.setCustomName(Text.literal("Chestplate of Death").formatted(Formatting.DARK_GRAY, Formatting.BOLD));
        if (defaultItem(entity, itemStack, EquipmentSlot.CHEST)) return;
        entity.equipStack(EquipmentSlot.CHEST, itemStack);
    }

    private void equipHelmet(LivingEntity entity) {
        ItemStack itemStack = new ItemStack(this.helmet);
        itemStack.setCustomName(Text.literal("Helmet of Death").formatted(Formatting.DARK_GRAY, Formatting.BOLD));
        if (defaultItem(entity, itemStack, EquipmentSlot.HEAD)) return;
        entity.equipStack(EquipmentSlot.HEAD, itemStack);
    }

    @Override
    public void onTick(LivingEntity entity) {
        if(!isActive(entity)) {
            //TODO: Remove armour after losing power
            return;
        }
        equipBoots(entity);
        equipLeggings(entity);
        equipChestplate(entity);
        equipHelmet(entity);
    }
}
