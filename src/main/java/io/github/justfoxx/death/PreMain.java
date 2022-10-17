package io.github.justfoxx.death;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class PreMain implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        MixinExtrasBootstrap.init();
        Global.logger.info("Made by JustFoxx");
        Global.logger.info("Thanks for installing our mod!");
        Global.logger.info("Check out other mods at https://www.curseforge.com/members/justafoxxo/projects");
    }
}
