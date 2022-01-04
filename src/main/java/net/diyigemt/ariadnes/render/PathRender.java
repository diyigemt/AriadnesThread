package net.diyigemt.ariadnes.render;

import net.diyigemt.ariadnes.controller.Config;
import net.diyigemt.ariadnes.controller.UpdateController;
import net.diyigemt.ariadnes.controller.WayPointStorage;
import net.diyigemt.ariadnes.logic.LinkPath;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Iterator;

public class PathRender {
  private static final ResourceLocation location = new ResourceLocation("");

  private void render() {
    final Tessellator tessellator = Tessellator.getInstance();
    Minecraft.getInstance().levelRenderer.addParticle();
    Minecraft.func_71410_x().field_71446_o.func_110577_a(PathRenderer.location);
    GL11.glDisable(2896);
    final boolean blend = GL11.glIsEnabled(3042);
    final boolean cullFace = GL11.glIsEnabled(2884);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glDisable(2884);
    GL11.glEnable(3553);
    final Iterator<LinkPath> lpIterator = (Iterator<LinkPath>)Status.instance().paths().getIterator();
    while (lpIterator.hasNext()) {
      final LinkPath lp = lpIterator.next();
      final double x = lp.nextPoint().X() + 0.5 - RenderManager.field_78725_b;
      final double y = lp.nextPoint().Y() - RenderManager.field_78726_c;
      final double z = lp.nextPoint().Z() + 0.5 - RenderManager.field_78723_d;
      final int xOffset = lp.prePoint().X() - lp.nextPoint().X();
      final int yOffset = lp.prePoint().Y() - lp.nextPoint().Y();
      final int zOffset = lp.prePoint().Z() - lp.nextPoint().Z();
      float dist = (float)Math.sqrt(x * x + y * y + z * z);
      float dist2 = (float)Math.sqrt((x + xOffset) * (x + xOffset) + (y + yOffset) * (y + yOffset) + (z + zOffset) * (z + zOffset));
      dist = (Math.min(dist2, dist));
      dist2 = (float)Math.sqrt((x + xOffset * 0.5) * (x + xOffset * 0.5) + (y + yOffset * 0.5) * (y + yOffset * 0.5) + (z + zOffset * 0.5) * (z + zOffset * 0.5));
      dist = (Math.min(dist2, dist));
      final double radius = Math.sqrt(xOffset * xOffset + yOffset * yOffset + zOffset * zOffset);
      if (radius > Status.instance().getDisplayRadius() * 2.0) {
        continue;
      }
      final float xAngle = 90.0f - (float)Math.toDegrees(Math.asin(yOffset / radius));
      final float yAngle = (float)Math.toDegrees(Math.atan2(xOffset, zOffset));
      int alpha = Status.instance().getAlpha();
      if (dist > Status.instance().getDisplayRadius() - 4.0f) {
        alpha = (int)((Status.instance().getDisplayRadius() - dist) * 0.25f * Status.instance().getAlpha());
      }
      else if (dist < 2.0f) {
        alpha = (int)((dist - 1.0f) * Status.instance().getAlpha());
      }
      alpha = (Math.max(alpha, 0));
      GL11.glPushMatrix();
      GL11.glTranslated(x, y, z);
      GL11.glRotatef(yAngle, 0.0f, 1.0f, 0.0f);
      GL11.glRotatef(xAngle, 1.0f, 0.0f, 0.0f);
      GL11.glScaled(0.1, radius, 0.1);
      for (float side = 0.0f; side < 360.0f; side += 90.0f) {
        GL11.glRotatef(side, 0.0f, 1.0f, 0.0f);
        tessellator.func_78382_b();
        tessellator.func_78370_a(Status.instance().getRGB_R(), Status.instance().getRGB_G(), Status.instance().getRGB_B(), alpha);
        tessellator.func_78380_c(15728640);
        tessellator.func_78374_a(-0.5, -0.0, 0.5, 0.0, 1.0);
        tessellator.func_78374_a(-0.5, 1.0, 0.5, 0.0, 0.0);
        tessellator.func_78374_a(0.5, 1.0, 0.5, 1.0, 0.0);
        tessellator.func_78374_a(0.5, -0.0, 0.5, 1.0, 1.0);
        tessellator.func_78381_a();
      }
      GL11.glPopMatrix();
    }
    if (cullFace) {
      GL11.glEnable(2884);
    }
    GL11.glDisable(3042);
    if (blend) {
      GL11.glEnable(3042);
    }
    GL11.glEnable(2896);
  }

  public void doRender() {
    WayPointStorage.PathArray paths = WayPointStorage.getInstance().getPaths();
    int alpha = Config.getInstance().getAlpha();
    int rgbR = Config.getInstance().getR();
    int rgbG = Config.getInstance().getG();
    int rgbB = Config.getInstance().getB();
    int wayPointRadius = Config.getInstance().getWayPointRadius();
    if (!(UpdateController.getInstance().isActive() || paths.isNotEmpty())) return;


      Tessellator tessellator = Tessellator.getInstance();
      Minecraft.getInstance().textureManager.bind(location);
      Minecraft.func_71410_x().field_71446_o.func_110577_a(location);
      GL11.glDisable(2896);
      boolean blend = GL11.glIsEnabled(3042);
      boolean cullFace = GL11.glIsEnabled(2884);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2884);
      GL11.glEnable(3553);
      Iterator lpIterator = paths.getIterator();

      while(true) {
        double x;
        double y;
        double z;
        int xOffset;
        int yOffset;
        int zOffset;
        float dist;
        double radius;
        do {
          if (!lpIterator.hasNext()) {
            if (cullFace) {
              GL11.glEnable(2884);
            }

            GL11.glDisable(3042);
            if (blend) {
              GL11.glEnable(3042);
            }

            GL11.glEnable(2896);
            return;
          }
          LinkPath lp = (LinkPath)lpIterator.next();
          x = (double)lp.nextPoint().X() + 0.5D - RenderManager.field_78725_b;
          y = (double)lp.nextPoint().Y() - RenderManager.field_78726_c;
          z = (double)lp.nextPoint().Z() + 0.5D - RenderManager.field_78723_d;
          xOffset = lp.prePoint().X() - lp.nextPoint().X();
          yOffset = lp.prePoint().Y() - lp.nextPoint().Y();
          zOffset = lp.prePoint().Z() - lp.nextPoint().Z();
          dist = (float)Math.sqrt(x * x + y * y + z * z);
          float dist2 = (float)Math.sqrt((x + (double)xOffset) * (x + (double)xOffset) + (y + (double)yOffset) * (y + (double)yOffset) + (z + (double)zOffset) * (z + (double)zOffset));
          dist = dist2 < dist ? dist2 : dist;
          dist2 = (float)Math.sqrt((x + (double)xOffset * 0.5D) * (x + (double)xOffset * 0.5D) + (y + (double)yOffset * 0.5D) * (y + (double)yOffset * 0.5D) + (z + (double)zOffset * 0.5D) * (z + (double)zOffset * 0.5D));
          dist = dist2 < dist ? dist2 : dist;
          radius = Math.sqrt((double)(xOffset * xOffset + yOffset * yOffset + zOffset * zOffset));
        } while(radius > (double)wayPointRadius * 2.0D);

        float xAngle = 90.0F - (float)Math.toDegrees(Math.asin((double)yOffset / radius));
        float yAngle = (float)Math.toDegrees(Math.atan2((double)xOffset, (double)zOffset));
        if (dist > (float)wayPointRadius - 4.0F) {
          alpha = (int)(((float)wayPointRadius - dist) * 0.25F * (float)alpha);
        } else if (dist < 2.0F) {
          alpha = (int)((dist - 1.0F) * (float)alpha);
        }

        alpha = alpha < 0 ? 0 : alpha;
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(yAngle, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(xAngle, 1.0F, 0.0F, 0.0F);
        GL11.glScaled(0.1D, radius, 0.1D);

        for(float side = 0.0F; side < 360.0F; side += 90.0F) {
          GL11.glRotatef(side, 0.0F, 1.0F, 0.0F);
          BufferBuilder builder = tessellator.getBuilder();
          tessellator.func_78382_b();

          builder.color()
          tessellator.func_78370_a(rgbR, rgbG, rgbB, alpha);

          builder.
          tessellator.func_78380_c(15728640);

          builder.vertex();
          tessellator.func_78374_a(-0.5D, -0.0D, 0.5D, 0.0D, 1.0D);
          tessellator.func_78374_a(-0.5D, 1.0D, 0.5D, 0.0D, 0.0D);
          tessellator.func_78374_a(0.5D, 1.0D, 0.5D, 1.0D, 0.0D);
          tessellator.func_78374_a(0.5D, -0.0D, 0.5D, 1.0D, 1.0D);
          tessellator.func_78381_a();


        }

        GL11.glPopMatrix();
      }

  }
}
