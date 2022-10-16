package io.github.justfoxx.death;

import io.github.justfoxx.death.powers.Armour;
import io.github.justfoxx.death.powers.Around;
import io.github.justfoxx.death.powers.BasePower;

import java.util.ArrayList;
import java.util.List;

public class Powers {
    private static final List<BasePower> powers = new ArrayList<>();

    public static void init() {
        register(new Armour(Global.id("armour")));
        register(new Around(Global.id("around")));
    }

    public static List<BasePower> getPowers() {
        return powers;
    }
    public static void register(BasePower power) {
        powers.add(power);
    }
}
