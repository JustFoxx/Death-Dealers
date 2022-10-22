package io.github.justfoxx.death.powers;

import io.github.justfoxx.death.data.ItemTags;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Damage extends BasePower{

    public boolean damage(DamageSource source) {
        if(source.isFire()) return true;
        if(source.isProjectile()) return false;
        if(!(source.getAttacker() instanceof LivingEntity attacker)) return false;
        if(attacker.getMainHandStack().isEmpty()) return false;
        if(!attacker.getMainHandStack().isIn(ItemTags.SWORDS)) return false;
        return true;
    }
}
