package io.github.justfoxx.death.powers;

import io.github.apace100.apoli.power.VariableIntPower;
import net.minecraft.entity.LivingEntity;

public class Source extends BasePower {
    public void kill(LivingEntity killer, LivingEntity victim) {
        if(!isActive(killer)) return;
        if(isActive(victim)) return;
        VariableIntPower power = (VariableIntPower) getPower(killer);
        modifyResource(power,1,MATH.ADD);
        getPowerHolder(killer).sync();
    }
}