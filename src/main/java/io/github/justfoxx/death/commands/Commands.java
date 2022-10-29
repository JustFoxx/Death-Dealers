package io.github.justfoxx.death.commands;

import io.github.justfoxx.death.Powers;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class Commands {
    public static void init() {
        CommandRegistrationCallback.EVENT.register(Powers.fromDeath::getCommand);
    }
}
