package io.github.justfoxx.death.mixins;

import io.github.justfoxx.death.Global;
import io.github.justfoxx.death.Powers;
import io.github.justfoxx.death.data.ItemTags;
import io.github.justfoxx.death.powers.HoeDamage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow protected abstract void drop(DamageSource source);

    @Inject(at = @At("RETURN"), method = "tick")
    public void tick(CallbackInfo info) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if(entity.getWorld() instanceof ServerWorld) {
            Powers.getPowers().forEach(power -> power.onTick(entity));
        }
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

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void canDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if(entity.getWorld().isClient()) return;
        if(entity.isInvulnerableTo(source)) return;
        if(entity.isDead()) return;
        if(Powers.damage.isActive(entity)) {
            if(!Powers.damage.damage(source)) {
                cir.setReturnValue(false);
            }
        }
        if(amount == 0 && source.getAttacker() instanceof LivingEntity attacker && Powers.hoeDamage.isActive(attacker)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)
    public void onDeath(DamageSource damageSource, CallbackInfo ci) {
        Powers.life.onKill(damageSource);
        LivingEntity entity = (LivingEntity) (Object) this;
        if(Powers.death.isActive(entity)) {
            this.drop(damageSource);
            Powers.death.death(damageSource,entity);
            ci.cancel();
        }
        if(!(damageSource.getAttacker() instanceof LivingEntity attacker)) return;
        Powers.source.kill(attacker,entity);
    }

    @Inject(at = @At("HEAD"), method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z", cancellable = true)
    public void addStatusEffect(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if(Powers.statusEffect.isActive(entity)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "eatFood", cancellable = true)
    public void eatFood(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if(Powers.statusEffect.isActive(entity) && !stack.isIn(ItemTags.ROTTEN_FOOD)) {
            cir.setReturnValue(ItemStack.EMPTY);
        }
    }
}
