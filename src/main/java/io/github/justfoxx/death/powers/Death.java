package io.github.justfoxx.death.powers;

import io.github.justfoxx.death.IEntityDataSaver;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameMode;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class Death extends BasePower{
    private final StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.DARKNESS, 10, 1, true, false);

    public void death(DamageSource source, LivingEntity entity) {
        if(entity.getServer() == null) return;
        MinecraftServer server = entity.getServer();
        LivingEntity attacker=null;
        if(source.getAttacker() instanceof LivingEntity livingEntity) {
            attacker = livingEntity;
        }
        if(attacker instanceof ServerPlayerEntity attackerEntity) {
            attacker.addStatusEffect(statusEffectInstance);
            attackerEntity.sendMessageToClient(Text.literal("I'm going for you.").formatted(Formatting.BOLD, Formatting.DARK_RED), true);
        }
        ServerWorld world = server.getWorld(World.NETHER);
        entity.setHealth(Float.MAX_VALUE);
        entity.setOnFire(false);
        assert world != null;
        world.spawnParticles(ParticleTypes.SMOKE, entity.getX(), entity.getY()+1, entity.getZ(), 100,1,1,1, 0.1);
        FabricDimensions.teleport(entity,server.getWorld(World.NETHER), new TeleportTarget(entity.getPos(), entity.getVelocity(), entity.getYaw(), entity.getPitch()));
        if(entity instanceof ServerPlayerEntity playerEntity) {
            playerEntity.changeGameMode(GameMode.SPECTATOR);
            server.getPlayerManager().broadcast(Text.literal("%s returned to hell".formatted(entity.getEntityName())).formatted(Formatting.DARK_RED, Formatting.BOLD), false);
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
        int reqTime = 5*60*20;
        if(ticks <= reqTime) {
            if(ticks % 10 ==0) ((ServerWorld)entity.getWorld()).spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, entity.getX(), entity.getY()+1, entity.getZ(), 10,0.5,1,0.5, .01);
            ((IEntityDataSaver) entity).getPersistentData().putInt("ticks", ticks+1);
            //Global.logger.info(String.valueOf(ticks));
            int leftTime = reqTime-ticks;
            int ticksLeft = leftTime%20;
            int secondsLeft = (leftTime/20)%60;
            int minutesLeft = (leftTime/20/60)%60;
            if(entity instanceof ServerPlayerEntity playerEntity) {
                playerEntity.sendMessageToClient(Text.literal("You will be respawned in %01d:%02d:%02d minutes".formatted(minutesLeft,secondsLeft,ticksLeft)).formatted(Formatting.DARK_RED, Formatting.BOLD), true);
            }
            return;
        }
        ((IEntityDataSaver) entity).getPersistentData().putBoolean("dead", false);
        ((IEntityDataSaver) entity).getPersistentData().putInt("ticks", 0);
        if(entity instanceof ServerPlayerEntity playerEntity) {
            playerEntity.changeGameMode(GameMode.SURVIVAL);
        }
        entity.playSound(SoundEvents.ENTITY_WARDEN_EMERGE, 1, 1);
    }
}
