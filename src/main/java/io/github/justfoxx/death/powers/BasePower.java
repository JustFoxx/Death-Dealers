package io.github.justfoxx.death.powers;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerTypeReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public abstract class BasePower {
    protected final Identifier id;
    protected final PowerTypeReference<Power> powerTypeReference;
    BasePower(Identifier id) {
        this.id = id;
        this.powerTypeReference = new PowerTypeReference<>(this.id);
    }

    public boolean isActive(LivingEntity livingEntity) {return powerTypeReference.isActive(livingEntity);}

    public abstract void onTick(LivingEntity entity);
}
