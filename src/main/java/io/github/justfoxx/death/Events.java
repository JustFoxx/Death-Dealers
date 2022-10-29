package io.github.justfoxx.death;

import io.github.justfoxx.death.mixins.MobEntityMixin;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;

public class Events {
    public static void init() {
        ServerEntityEvents.ENTITY_LOAD.register(Events::onEntityLoad);
    }

    private static void onEntityLoad(Entity entity, ServerWorld serverWorld) {
        if(!(entity instanceof MobEntity mob)) return;
        ((MobEntityMixin) mob).getGoalSelector().add(3, new FleeEntityGoal<>((PathAwareEntity) entity, LivingEntity.class, Powers.scary::isActive, 6.0F, 1.2D, 1.4D, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR::test));
    }
}
