package io.github.justfoxx.death.powers;

import io.github.apace100.apoli.power.VariableIntPower;
import io.github.justfoxx.death.Global;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class Life extends BasePower {
    public void onKill(DamageSource damageSource) {
        if(!(damageSource.getAttacker() instanceof LivingEntity attacker)) return;
        if(!isActive(attacker)) return;
        VariableIntPower power = (VariableIntPower) getPower(attacker);
        modifyResource(power, 1000, MATH.ADD);
        getPowerHolder(attacker).sync();
    }
    @Override
    public void onTick(LivingEntity entity) {
        if(!isActive(entity)) return;
        if(!(getPower(entity) instanceof VariableIntPower power)) return;

        if(power.getValue() != 0) {
            modifyResource(power, 1, MATH.REMOVE);
            getPowerHolder(entity).sync();
            Global.logger.info("Life: " + power.getValue());
            return;
        }
        entity.onDeath(new DamageSource("life"));
    }
}
