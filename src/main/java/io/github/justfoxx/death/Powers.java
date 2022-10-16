package io.github.justfoxx.death;

import io.github.justfoxx.death.powers.Armour;
import io.github.justfoxx.death.powers.Around;
import io.github.justfoxx.death.powers.BasePower;
import io.github.justfoxx.death.powers.HoeDamage;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class Powers {
    private static final List<BasePower> powers = new ArrayList<>();

    public static final BasePower armour = new Armour();
    public static final BasePower around = new Around();
    public static final HoeDamage hoeDamage = new HoeDamage();
    public static void init() {
        register(armour,Global.id("armour"));
        register(around,Global.id("around"));
        register(hoeDamage,Global.id("hoedamage"));
    }

    public static List<BasePower> getPowers() {
        return powers;
    }
    public static void register(BasePower power, Identifier id) {
        power.registeredId(id);
        powers.add(power);
    }
}
