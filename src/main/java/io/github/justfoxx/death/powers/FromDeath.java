package io.github.justfoxx.death.powers;

import com.mojang.brigadier.CommandDispatcher;
import io.github.justfoxx.death.Powers;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

public class FromDeath extends BasePower{
    Random random = Random.create();
    public int randomMob() {
        Chance chance = new Chance(random.nextBetween(1, doubleToInt(100)));
        if(chance.chanceNext(doubleToInt(.001))) return 1;
        if(chance.chanceNext(doubleToInt(.499))) return 2;
        if(chance.chanceNext(doubleToInt(.5))) return 3;
        if(chance.chanceNext(doubleToInt(4))) return 4;
        if(chance.chanceNext(doubleToInt(5))) return 5;
        if(chance.chanceNext(doubleToInt(5))) return 6;
        if(chance.chanceNext(doubleToInt(5))) return 7;
        if(chance.chanceNext(doubleToInt(5))) return 8;
        if(chance.chanceNext(doubleToInt(5))) return 9;
        if(chance.chanceNext(doubleToInt(5))) return 10;
        if(chance.chanceNext(doubleToInt(5))) return 11;
        if(chance.chanceNext(doubleToInt(10))) return 12;
        if(chance.chanceNext(doubleToInt(10))) return 13;
        if(chance.chanceNext(doubleToInt(20))) return 14;
        if(chance.chanceNext(doubleToInt(20))) return 15;
        return 0;
    }

    public void getCommand(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("fromdeath").executes(context -> {
            Vec3d pos = context.getSource().getPosition();
            ServerWorld world = context.getSource().getWorld();
            int mob = randomMob();
            spawnEntity(mob, world, pos);
            return 1;
        }).requires(source -> source.hasPermissionLevel(4)));

    }


    private void spawnEntity(int i, ServerWorld world, Vec3d pos) {
        switch (i) {
            case 14 -> summonHelper("minecraft:zombie", world, pos);
            case 13 -> summonHelper("minecraft:skeleton", world, pos);
            case 12 -> summonHelper("minecraft:creeper", world, pos);
            case 11 -> summonHelper("minecraft:spider", world, pos);
            case 10 -> summonHelper("minecraft:cave_spider", world, pos);
            case 9 -> summonHelper("minecraft:enderman", world, pos);
            case 8 -> summonHelper("minecraft:witch", world, pos);
            case 7 -> summonHelper("minecraft:pillager", world, pos);
            case 6 -> summonHelper("minecraft:vindicator", world, pos);
            case 5 -> summonHelper("minecraft:evoker", world, pos);
            case 4 -> summonHelper("minecraft:ravager", world, pos);
            case 3 -> summonHelper("minecraft:wither_skeleton", world, pos);
            case 2 -> summonHelper("minecraft:warden", world, pos);
            //bob zombie
            case 1 -> summonBob(world, pos);
        }
    }

    private void summonHelper(String id, ServerWorld world, Vec3d pos) {
        NbtCompound nbt = new NbtCompound();
        nbt.putString("id", id);
        Entity entity = EntityType.loadEntityWithPassengers(nbt, world, entityx -> {
            entityx.refreshPositionAndAngles(pos.x, pos.y, pos.z, entityx.getYaw(), entityx.getPitch());
            return entityx;
        });
        if (!(entity instanceof MobEntity livingEntity)) return;
        livingEntity.initialize(world, world.getLocalDifficulty(new BlockPos(pos)), SpawnReason.COMMAND, null, null);
        world.spawnNewEntityAndPassengers(entity);
    }


    private final ItemStack hand = new ItemStack(Items.NETHERITE_SWORD).setCustomName(Powers.damage.name);
    private final ItemStack head = new ItemStack(Items.NETHERITE_HELMET);
    private final ItemStack chest = new ItemStack(Items.NETHERITE_CHESTPLATE);
    private final ItemStack legs = new ItemStack(Items.NETHERITE_LEGGINGS);
    private final ItemStack feet = new ItemStack(Items.NETHERITE_BOOTS);
    public final Text text = Text.literal("Bob").formatted(Formatting.BOLD, Formatting.AQUA);
    private void summonBob(ServerWorld world, Vec3d pos) {
        NbtCompound nbt = new NbtCompound();
        nbt.putString("id", "minecraft:zombie");
        ZombieEntity entity = (ZombieEntity) EntityType.loadEntityWithPassengers(nbt, world, entityx -> {
            entityx.refreshPositionAndAngles(pos.x, pos.y, pos.z, entityx.getYaw(), entityx.getPitch());
            return entityx;
        });
        hand.addEnchantment(Enchantments.SHARPNESS, 5);
        head.addEnchantment(Enchantments.PROTECTION, 4);
        chest.addEnchantment(Enchantments.PROTECTION, 4);
        legs.addEnchantment(Enchantments.PROTECTION, 4);
        feet.addEnchantment(Enchantments.PROTECTION, 4);
        entity.equipStack(EquipmentSlot.MAINHAND, hand);
        entity.equipStack(EquipmentSlot.HEAD, head);
        entity.equipStack(EquipmentSlot.CHEST, chest);
        entity.equipStack(EquipmentSlot.LEGS, legs);
        entity.equipStack(EquipmentSlot.FEET, feet);
        entity.setCustomName(text);
        entity.setMovementSpeed(0.1f);
        entity.initialize(world, world.getLocalDifficulty(new BlockPos(pos)), SpawnReason.COMMAND, null, null);
        world.spawnNewEntityAndPassengers(entity);
    }

    private static class Chance {
        private int chanceNow;
        private int chanceNext;
        private final int rndInt;
        public Chance(int rndInt) {
            this.rndInt = rndInt;
        }
        public boolean chanceNext(int chance) {
            chanceNext = chanceNow + chance;
            if(rndInt >= chanceNow && rndInt <= chanceNext) {
                chanceNow = chanceNext;
                return true;
            }
            chanceNow = chanceNext;
            return false;
        }
    }

    private int doubleToInt(double d) {
        return doubleToInt(.001, d);
    }

    private int doubleToInt(double by,double d) {
        return (int) (d/by);
    }
}
