package net.diyigemt.ariadnes;

import net.diyigemt.ariadnes.controller.Config;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

@net.minecraftforge.fml.common.Mod("ariadnesthread")
public class Mod {

  public Mod() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.getInstance().getConfigObject());
  }

}
