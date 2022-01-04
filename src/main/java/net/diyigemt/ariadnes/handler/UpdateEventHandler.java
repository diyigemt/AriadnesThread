package net.diyigemt.ariadnes.handler;

import net.diyigemt.ariadnes.controller.UpdateController;
import net.diyigemt.ariadnes.render.PathRender;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class UpdateEventHandler {
  private final PathRender renderer = new PathRender();
  @SubscribeEvent
  public void onUpdateEvent(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntity() instanceof ClientPlayerEntity) {
      UpdateController.getInstance().update();
    }
  }

  public void onWorldLoadEvent(WorldEvent.Load event) {
    UpdateController.getInstance().setStatus(UpdateController.RunningStatus.RUNNING);
  }

  public void onRenderTick(RenderWorldLastEvent event) {
    renderer.doRender();
  }
}
