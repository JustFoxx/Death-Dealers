package io.github.justfoxx.death;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.justfoxx.death.powers.Armour;
import io.github.justfoxx.death.powers.BasePower;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mutable;

import java.util.ArrayList;
import java.util.List;

public class Powers {
    private static List<BasePower> powers = new ArrayList<>();

    public static BasePower ARMOUR = register(new Armour(Global.id("armour")));

    public static List<BasePower> getPowers() {
        return powers;
    }
    public static BasePower register(BasePower power) {
        powers.add(power);
        return power;
    }
}
