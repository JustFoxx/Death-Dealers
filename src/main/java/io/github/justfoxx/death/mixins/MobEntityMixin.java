package io.github.justfoxx.death.mixins;

import io.github.apace100.origins.power.OriginsPowerTypes;
import io.github.justfoxx.death.Global;
import io.github.justfoxx.death.Powers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin {

    @Shadow @Final protected GoalSelector goalSelector;

    @Inject(at = @At("HEAD"), method = "initGoals")
    private void addGoals(CallbackInfo info) {
        Global.logger.info("Adding goals to " + this.toString());
        if (!((Object) this instanceof PathAwareEntity entity)) return;
        Global.logger.info("Adding goals to " + entity.getType().toString());
        Goal goal = new FleeEntityGoal<>(entity, LivingEntity.class, Powers.scary::isActive, 6.0F, 1.0D, 1.2D, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR::test);
        this.goalSelector.add(3, goal);
    }
}
