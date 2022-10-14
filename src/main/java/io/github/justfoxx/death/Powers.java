package io.github.justfoxx.death;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerTypeReference;
import net.minecraft.util.Identifier;

public class Powers {
    public static PowerTypeReference<Power> getPower(Identifier id) {
        return new PowerTypeReference<>(id);
    }
    public static final PowerTypeReference<Power> POWER = getPower(Global.id("test"));

}
