package net.diyigemt.ariadnes.controller;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

  private static final ForgeConfigSpec CONFIG;
  private static final Config INSTANCE = new Config();
  private static final ForgeConfigSpec.IntValue RGB_ALPHA;
  private static final ForgeConfigSpec.IntValue RGB_R;
  private static final ForgeConfigSpec.IntValue RGB_G;
  private static final ForgeConfigSpec.IntValue RGB_B;
  private static final ForgeConfigSpec.IntValue wayPointDensity;
  private static final ForgeConfigSpec.IntValue wayPointRadius;

  static {
    ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    builder.comment("general settings");
    builder.push("client");
    RGB_ALPHA = builder.comment("color of thread: alpha").defineInRange("alpha", 100, 0, 100);
    RGB_R = builder.comment("color of thread: rgb_r").defineInRange("rgb_r", 128, 0, 255);
    RGB_G = builder.comment("color of thread: rgb_g").defineInRange("rgb_g", 0, 0, 255);
    RGB_B = builder.comment("color of thread: rgb_b").defineInRange("rgb_b", 128, 0, 255);
    wayPointDensity = builder.comment("the density of way point").defineInRange("way_point_density", 2, 0, 255);
    wayPointRadius = builder.comment("render distance of way point").defineInRange("display_radius", 16, 0, 255);
    CONFIG = builder.pop().build();
  }

  private Config() {}

  public static Config getInstance() { return INSTANCE; }

  public ForgeConfigSpec getConfigObject() { return CONFIG; }

  public int getAlpha() {
    return RGB_ALPHA.get();
  }

  public int getR() {
    return RGB_R.get();
  }

  public int getG() {
    return RGB_G.get();
  }

  public int getB() {
    return RGB_B.get();
  }

  public int getWayPointDensity() {
    return wayPointDensity.get();
  }

  public int getWayPointRadius() {
    return wayPointRadius.get();
  }
}
