package io.github.justfoxx.death.powers;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

    private ItemStack defaultItem(ItemStack itemStack, String name) {
        itemStack.getOrCreateSubNbt("display").putInt("color", this.color);
        itemStack.getOrCreateNbt().putInt("HideFlags", 101);
        itemStack.getOrCreateNbt().putInt("Unbreakable", 1);
        NbtList loreList = new NbtList();
        loreList.add(NbtString.of(Text.Serializer.toJson(Text.literal("Death's Armour").formatted(Formatting.RED))));
        itemStack.getOrCreateSubNbt("display").put("Lore", loreList);
        itemStack.addEnchantment(this.protection, this.protectionLevel);
        itemStack.addEnchantment(this.curseOfBinding, this.curseOfBindingLevel);
        itemStack.addEnchantment(this.curseOfVanishing, this.curseOfVanishingLevel);
        itemStack.setCustomName(Text.literal(name+" of Death").formatted(Formatting.DARK_GRAY, Formatting.BOLD));
        return itemStack;
    }

    private void equipBoots(LivingEntity entity) {
        ItemStack itemStack = defaultItem(new ItemStack(this.boots), "Boots");
        if (entity.getEquippedStack(EquipmentSlot.FEET).isItemEqual(itemStack)) return;
        entity.equipStack(EquipmentSlot.FEET, itemStack);
    }

    private void equipLeggings(LivingEntity entity) {
        ItemStack itemStack = defaultItem(new ItemStack(this.leggings), "Leggings");
        if (entity.getEquippedStack(EquipmentSlot.LEGS).isItemEqual(itemStack)) return;
        entity.equipStack(EquipmentSlot.LEGS, itemStack);
    }

    private void equipChestplate(LivingEntity entity) {
        ItemStack itemStack = defaultItem(new ItemStack(this.chestplate), "Chestplate");
        if (entity.getEquippedStack(EquipmentSlot.CHEST).isItemEqual(itemStack)) return;
        entity.equipStack(EquipmentSlot.CHEST, itemStack);
    }

    private void equipHelmet(LivingEntity entity) {
        ItemStack itemStack = defaultItem(new ItemStack(this.helmet), "Helmet");
        if (entity.getEquippedStack(EquipmentSlot.HEAD).isItemEqual(itemStack)) return;
        entity.equipStack(EquipmentSlot.HEAD, itemStack);
    }

    private void unequipBoots(LivingEntity entity) {
        ItemStack itemStack = defaultItem(new ItemStack(this.boots), "Boots");
        if (!entity.getEquippedStack(EquipmentSlot.FEET).isItemEqual(itemStack)) return;
        entity.equipStack(EquipmentSlot.FEET, ItemStack.EMPTY);
    }

    private void unequipLeggings(LivingEntity entity) {
        ItemStack itemStack = defaultItem(new ItemStack(this.leggings), "Leggings");
        if (!entity.getEquippedStack(EquipmentSlot.LEGS).isItemEqual(itemStack)) return;
        entity.equipStack(EquipmentSlot.LEGS, ItemStack.EMPTY);
    }

    private void unequipChestplate(LivingEntity entity) {
        ItemStack itemStack = defaultItem(new ItemStack(this.chestplate), "Chestplate");
        if (!entity.getEquippedStack(EquipmentSlot.CHEST).isItemEqual(itemStack)) return;
        entity.equipStack(EquipmentSlot.CHEST, ItemStack.EMPTY);
    }

    private void unequipHelmet(LivingEntity entity) {
        ItemStack itemStack = defaultItem(new ItemStack(this.helmet), "Helmet");
        if (!entity.getEquippedStack(EquipmentSlot.HEAD).isItemEqual(itemStack)) return;
        entity.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
    }

    @Override
    public void onTick(LivingEntity entity) {
        if(!isActive(entity)) {
            unequipBoots(entity);
            unequipLeggings(entity);
            unequipChestplate(entity);
            unequipHelmet(entity);
            return;
        }
        equipBoots(entity);
        equipLeggings(entity);
        equipChestplate(entity);
        equipHelmet(entity);
    }
}
