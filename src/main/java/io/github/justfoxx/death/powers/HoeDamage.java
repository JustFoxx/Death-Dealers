package io.github.justfoxx.death.powers;

import io.github.justfoxx.death.data.ItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class HoeDamage extends BasePower {
    public int damage(DamageSource source, LivingEntity attacker) {
        if(source.isProjectile()) return 0;

        if(attacker.getMainHandStack().isIn(ItemTags.HOES)) {
            return 1;
        }
        if(!(
                source.isExplosive()
                && source.isFire()
                && source.isFallingBlock()
                && source.isMagic()
                && source.isOutOfWorld()
        )) {
            return 0;
        }

        return 2;
    }
}
