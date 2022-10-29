package io.github.justfoxx.death.mixins;

import io.github.justfoxx.death.Powers;
import io.github.justfoxx.death.data.ItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(at = @At("HEAD"), method = "eatFood", cancellable = true)
    public void eatFood(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if(Powers.statusEffect.isActive(entity) && !stack.isIn(ItemTags.ROTTEN_FOOD)) {
            cir.setReturnValue(ItemStack.EMPTY);
        }
    }
}
