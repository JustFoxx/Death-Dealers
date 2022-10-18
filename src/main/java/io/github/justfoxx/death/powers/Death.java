package io.github.justfoxx.death.powers;

import io.github.justfoxx.death.Global;
import io.github.justfoxx.death.IEntityDataSaver;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameMode;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class Death extends BasePower{
    public void death(DamageSource source, LivingEntity entity) {
        if(entity.getServer() == null) return;

        LivingEntity attacker=null;
        if(source.getAttacker() instanceof LivingEntity livingEntity) {
            attacker = livingEntity;
        }
        if(attacker instanceof ServerPlayerEntity attackerEntity) {
            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.DARKNESS, 10, 1, true, false);
            attacker.addStatusEffect(statusEffectInstance);
            attackerEntity.sendMessageToClient(Text.literal("I'm going for you.").formatted(Formatting.BOLD, Formatting.DARK_RED), true);
        }
        ServerWorld world = entity.getServer().getWorld(World.NETHER);
        entity.setHealth(Float.MAX_VALUE);
        entity.setOnFire(false);
        assert world != null;
        world.spawnParticles(ParticleTypes.SMOKE, entity.getX(), entity.getY()+1, entity.getZ(), 100,1,1,1, 0.1);
        FabricDimensions.teleport(entity,entity.getServer().getWorld(World.NETHER), new TeleportTarget(entity.getPos(), entity.getVelocity(), entity.getYaw(), entity.getPitch()));
        if(entity instanceof ServerPlayerEntity playerEntity) {
            playerEntity.setAbsorptionAmount(20);
            playerEntity.changeGameMode(GameMode.SPECTATOR);
        }
        ((IEntityDataSaver) entity).getPersistentData().putBoolean("dead", true);
        ((IEntityDataSaver) entity).getPersistentData().putInt("ticks", 0);
    }
    @Override
    public void onTick(LivingEntity entity) {
        if(!isActive(entity)) return;
        boolean dead = ((IEntityDataSaver) entity).getPersistentData().getBoolean("dead");
        if(!dead) return;
        int ticks = ((IEntityDataSaver) entity).getPersistentData().getInt("ticks");
        if(ticks <= 360000) {
            ((ServerWorld)entity.getWorld()).spawnParticles(ParticleTypes.ASH, entity.getX(), entity.getY()+1, entity.getZ(), 100,1,1,1, 0.1);
            ((IEntityDataSaver) entity).getPersistentData().putInt("ticks", ticks+1);
            Global.logger.info(String.valueOf(ticks));
            return;
        }
        ((IEntityDataSaver) entity).getPersistentData().putBoolean("dead", false);
        ((IEntityDataSaver) entity).getPersistentData().putInt("ticks", 0);
        if(entity instanceof ServerPlayerEntity playerEntity) {
            playerEntity.changeGameMode(GameMode.SURVIVAL);
        }
    }
}
