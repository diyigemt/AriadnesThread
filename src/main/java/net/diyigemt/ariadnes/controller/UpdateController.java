package net.diyigemt.ariadnes.controller;

import net.diyigemt.ariadnes.logic.AriadnesThread;
import net.minecraft.client.Minecraft;

public class UpdateController {

  private static final UpdateController INSTANCE = new UpdateController();
  private static RunningStatus status = RunningStatus.SETUP;
  private static AriadnesThread thread = null;
  private static boolean ACTIVE = false;

  private UpdateController() {}

  public static UpdateController getInstance() { return INSTANCE; }

  public void setStatus(RunningStatus s) {
    status = s;
  }

  public void update() {
    switch (status) {
      case SETUP:{break;}
      case RUNNING:{
        if (thread == null) {
          thread = new AriadnesThread(Minecraft.getInstance().player);
        }
        thread.tick();
      }
    }
  }

  public boolean isActive() {
    return ACTIVE;
  }

  public void setActive(boolean ACTIVE) {
    UpdateController.ACTIVE = ACTIVE;
  }

  public enum RunningStatus {
    SETUP,
    STOP,
    RUNNING,
  }
}
