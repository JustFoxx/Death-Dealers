package io.github.justfoxx.death.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.justfoxx.death.Global;
import io.github.justfoxx.death.Powers;
import io.github.justfoxx.death.powers.HoeDamage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.CheckReturnValue;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At("RETURN"), method = "tick")
    public void tick(CallbackInfo info) {
        Powers.getPowers().forEach(power -> power.onTick((LivingEntity) (Object) this));
    }

    @ModifyVariable(at = @At("HEAD"), method = "damage", argsOnly = true)
    public float modifyDamage(float originalValue, DamageSource source, float amount) {
        Entity attackerEntityBase = source.getAttacker();
        if(!(attackerEntityBase instanceof LivingEntity attacker)) return originalValue;
        HoeDamage hoeDamage = Powers.hoeDamage;
        if(Powers.hoeDamage.isActive(attacker)) {
            switch (hoeDamage.damage(source, attacker)) {
                case 0 -> {
                    return 0;
                }
                case 1 -> {
                    return originalValue+3.5f;
                }
            }
        }
        return originalValue;
    }
}
