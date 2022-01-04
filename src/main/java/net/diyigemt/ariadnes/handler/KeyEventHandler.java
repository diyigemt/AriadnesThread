package net.diyigemt.ariadnes.handler;

import net.diyigemt.ariadnes.controller.Config;
import net.diyigemt.ariadnes.controller.UpdateController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.UUID;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class KeyEventHandler {
  public static final KeyBinding ACTIVE_KEY = new KeyBinding("开启/关闭毛线团", KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_P, "阿里阿德涅的线团");
  private static boolean active = false;
  @SubscribeEvent
  public static void onKeyPressedEvent(InputEvent.KeyInputEvent event) {
    if (ACTIVE_KEY.isDown()) {
      active = !active;
      UpdateController.getInstance().setActive(active);
      ClientPlayerEntity player = Minecraft.getInstance().player;
      assert player != null;
      player.sendMessage(new TranslationTextComponent("按下了P键"), UUID.randomUUID());
      player.sendMessage(new TranslationTextComponent(String.valueOf(Config.getInstance().getAlpha())), UUID.randomUUID());
    }
  }
}
