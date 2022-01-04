//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.diyigemt.ariadnes.logic;

import net.diyigemt.ariadnes.controller.Config;
import net.diyigemt.ariadnes.controller.WayPointStorage;
import net.minecraft.client.entity.player.ClientPlayerEntity;

public class AriadnesThread {
  private ClientPlayerEntity thePlayer;
  private WayPoint preWayPoint = null;

  public AriadnesThread(ClientPlayerEntity player) {
    this.thePlayer = player;
  }

  public void tick() {
    updateWayPointMap();
    updateDisplayArray();
  }

  private void updateWayPointMap() {
    WayPoint newWayPoint = getNewWayPoint();
    if (preWayPoint == null) {
      preWayPoint = newWayPoint;
    } else {
      WayPoint hitWayPoint = scanNearPoints(newWayPoint, Config.getInstance().getWayPointDensity(), null);
      if (hitWayPoint != null) {
        cleanPointsFromSetAfter(hitWayPoint);
        preWayPoint = hitWayPoint;
        preWayPoint.setNextPoint((WayPoint)null);
      }

      if (this.awayEnough(newWayPoint, preWayPoint)) {
        linkPoints(newWayPoint, preWayPoint);
        addToSet(preWayPoint);
        preWayPoint = newWayPoint;
      }

    }
  }

  private WayPoint getNewWayPoint() {
    double x = thePlayer.getX();
    double y = thePlayer.getY();
    double z = thePlayer.getZ();
    x = x < 0.0D ? x - 1.0D : x;
    y = y < 0.0D ? y - 1.0D : y;
    z = z < 0.0D ? z - 1.0D : z;
    return new WayPoint((int)x, (int)y, (int)z);
  }

  private WayPoint scanNearPoints(WayPoint centerPoint, int range, AriadnesThread at) {
    WayPointStorage.PointSet points = WayPointStorage.getInstance().getPoints();
    if (points.isNotEmpty()) {
      WayPoint wp = new WayPoint();
      int minX = centerPoint.X() - range;
      int maxX = centerPoint.X() + range;
      int minY = centerPoint.Y() - range;
      int maxY = centerPoint.Y() + range;
      int minZ = centerPoint.Z() - range;
      int maxZ = centerPoint.Z() + range;

      for(int x = minX; x <= maxX; ++x) {
        wp.setX(x);

        for(int y = minY; y <= maxY; ++y) {
          wp.setY(y);

          for(int z = minZ; z <= maxZ; ++z) {
            wp.setZ(z);
            if (points.isInSet(wp)) {
              WayPoint hitPoint = points.getOrigInst(wp);
              if (at == null) {
                return hitPoint;
              }

              at.insertPath(hitPoint);
            }
          }
        }
      }
    }

    return null;
  }

  private void cleanPointsFromSetAfter(WayPoint startPoint) {
    WayPointStorage.PointSet points = WayPointStorage.getInstance().getPoints();
    for(WayPoint wp = startPoint; points.isInSet(wp); wp = wp.getNext()) {
      wp = points.getOrigInst(wp);
      points.removePoint(wp);
    }

  }

  private void linkPoints(WayPoint newPoint, WayPoint prePoint) {
    newPoint.setPrePoint(prePoint);
    prePoint.setNextPoint(newPoint);
  }

  private boolean awayEnough(WayPoint newPoint, WayPoint prePoint) {
    int x = newPoint.X() - prePoint.X();
    int y = newPoint.Y() - prePoint.Y();
    int z = newPoint.Z() - prePoint.Z();
    int wayPointDensity = Config.getInstance().getWayPointDensity();
    return x > wayPointDensity || x < -wayPointDensity || y > wayPointDensity || y < -wayPointDensity || z > wayPointDensity || z < -wayPointDensity;
  }

  private void addToSet(WayPoint wp) {
    WayPointStorage.getInstance().getPoints().addPoint(wp);
  }

  private void updateDisplayArray() {
    WayPointStorage.PointSet points = WayPointStorage.getInstance().getPoints();
    WayPointStorage.PathArray paths = WayPointStorage.getInstance().getPaths();
    WayPoint newWayPoint = this.getNewWayPoint();
    if ((!newWayPoint.equals(preWayPoint) || !paths.isNotEmpty()) && points.isNotEmpty()) {
      paths.clear();
      this.scanNearPoints(newWayPoint, Config.getInstance().getWayPointRadius(), this);
    }

  }

  private void insertPath(WayPoint wp) {
    WayPointStorage.PathArray paths = WayPointStorage.getInstance().getPaths();
    if (wp.getPre() != null) {
      paths.addPath(new LinkPath(wp.getPre(), wp));
    }

    if (wp.getNext() != null) {
      paths.addPath(new LinkPath(wp, wp.getNext()));
    }

  }
}
