package io.github.justfoxx.death;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        Global.logger.info("Loaded mod!");
    }
}
