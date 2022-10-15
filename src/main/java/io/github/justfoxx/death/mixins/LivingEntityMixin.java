package io.github.justfoxx.death.mixins;

import io.github.justfoxx.death.Powers;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At("RETURN"), method = "tick")
    public void tick(CallbackInfo info) {
        Powers.getPowers().forEach(power -> power.onTick((LivingEntity) (Object) this));
    }
}
