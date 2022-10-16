package io.github.justfoxx.death;

import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        Powers.init();
        Global.logger.info("Loaded mod!");
    }
}
