package io.github.justfoxx.death.powers;

import io.github.justfoxx.death.Global;
import io.github.justfoxx.death.data.ItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Damage extends BasePower{
    public final Text name = Text.literal("Holy Sword").formatted(Formatting.BOLD, Formatting.AQUA);
    public boolean damage(DamageSource source) {
        if(source.isFire()) return true;
        if(source.isProjectile()) return false;
        if(!(source.getAttacker() instanceof LivingEntity attacker)) return false;
        if(attacker.getMainHandStack().isEmpty()) return false;
        if(!attacker.getMainHandStack().isIn(ItemTags.SWORDS)) return false;
        return attacker.getMainHandStack().getName().equals(name);
    }
}
