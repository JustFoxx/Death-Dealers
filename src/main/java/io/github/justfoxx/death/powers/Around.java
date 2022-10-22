package io.github.justfoxx.death.powers;

import io.github.justfoxx.death.data.BlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class Around extends BasePower {
    private final int radius = 3;
    private int ambientChance;
    private void resetAmbientDelay() {
        this.ambientChance = -this.getMinAmbientDelay();
    }
    private int getMinAmbientDelay() {
        return 5;
    }

    private void spawnParticles(BlockPos blockPos,ServerWorld world) {
        world.spawnParticles(ParticleTypes.SMOKE, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 10, 0.1, 0.1, 0.1, 0.1);
    }

    private void killAround(LivingEntity entity) {
        BlockPos blockPosOfEntity = entity.getBlockPos();
        for (BlockPos blockPos : BlockPos.iterateRandomly(entity.world.getRandom(), 30, blockPosOfEntity, radius)) {
            BlockState blockState = entity.world.getBlockState(blockPos);
            if(blockPos.getY() != blockPosOfEntity.getY()) {
                continue;
            }
            if (blockState.isAir()) {
                continue;
            }
            ServerWorld world = (ServerWorld) entity.getWorld();
            if (blockState.isIn(BlockTags.GRASS_BLOCKS)) {
                world.setBlockState(blockPos, Blocks.DIRT.getDefaultState());
            } else if (blockState.isIn(BlockTags.DIRT)) {
                world.setBlockState(blockPos, Blocks.COARSE_DIRT.getDefaultState());
            } else if (blockState.isIn(BlockTags.PLANTS)) {
                world.setBlockState(blockPos, Blocks.DEAD_BUSH.getDefaultState());
            } else if (blockState.isIn(BlockTags.CORAL)) {
                world.setBlockState(blockPos, Blocks.DEAD_BRAIN_CORAL.getDefaultState());
            } else if (blockState.isIn(BlockTags.DEAD_CORAL)) {
                world.setBlockState(blockPos, Blocks.MANGROVE_ROOTS.getDefaultState());
            } else {
                continue;
            }
            spawnParticles(blockPos, world);
            break;
        }
    }

    private final Random rnd = new Random();

    @Override
    public void onTick(LivingEntity entity) {
        if(!isActive(entity)) return;
        int random = rnd.nextInt(1000);
        if (!(
                entity.isAlive()
                && random < this.ambientChance++
                && !entity.getWorld().isClient()
        )) return;
        resetAmbientDelay();
        killAround(entity);
    }
}
