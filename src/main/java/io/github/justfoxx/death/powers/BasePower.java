package io.github.justfoxx.death.powers;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public abstract class BasePower {
    protected enum MATH {
        ADD,
        REMOVE,
        SET
    }
    protected Identifier id;
    protected PowerTypeReference<Power> powerTypeReference;
    protected PowerType<?> powerType;
    protected PowerHolderComponent powerHolderComponent;

    public void registeredId(Identifier id) {
        this.id = id;
        this.powerTypeReference = new PowerTypeReference<>(this.id);
    }

    public boolean isActive(LivingEntity livingEntity) {
        return powerTypeReference.isActive(livingEntity);
    }

    public void onTick(LivingEntity entity){}

    public PowerHolderComponent getPowerHolder(LivingEntity entity) {
        if (powerHolderComponent == null) {
            powerHolderComponent = PowerHolderComponent.KEY.get(entity);
        }
        return powerHolderComponent;
    }

    public Power getPower(LivingEntity entity){
        if (this.powerType == null) {
            this.powerType = PowerTypeRegistry.get(id);
        }
        return getPowerHolder(entity).getPower(this.powerType);
    }

    protected void modifyResource(VariableIntPower power, int value, MATH math) {
        switch (math) {
            case ADD ->power.setValue(power.getValue() + value);
            case REMOVE ->power.setValue(power.getValue() - value);
            case SET ->power.setValue(value);
        }
    }
}
