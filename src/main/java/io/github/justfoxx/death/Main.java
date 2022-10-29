package io.github.justfoxx.death;

import io.github.justfoxx.death.commands.Commands;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        Powers.init();
        Events.init();
        Commands.init();
        Global.logger.info("Loaded mod!");
    }
}
