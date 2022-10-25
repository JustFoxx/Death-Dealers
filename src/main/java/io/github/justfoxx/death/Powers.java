package io.github.justfoxx.death;

import io.github.justfoxx.death.powers.*;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class Powers {
    private static final List<BasePower> powers = new ArrayList<>();

    public static final BasePower armour = new Armour();
    public static final BasePower around = new Around();
    public static final HoeDamage hoeDamage = new HoeDamage();
    public static final Death death = new Death();
    public static final Source source = new Source();
    public static final Damage damage = new Damage();
    public static final BasePower scary = new PowerPlaceholder();
    public static void init() {
        register(armour,Global.id("armour"));
        register(around,Global.id("around"));
        register(hoeDamage,Global.id("hoedamage"));
        register(death,Global.id("death"));
        register(source,Global.id("source"));
        register(damage,Global.id("damage"));
        register(scary,Global.id("scary"));
    }

    public static List<BasePower> getPowers() {
        return powers;
    }
    public static void register(BasePower power, Identifier id) {
        power.registeredId(id);
        powers.add(power);
    }
}
