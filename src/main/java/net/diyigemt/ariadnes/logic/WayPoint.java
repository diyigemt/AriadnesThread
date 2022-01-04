//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.diyigemt.ariadnes.logic;

import java.io.Serializable;

public class WayPoint implements Serializable {
  private int x;
  private int y;
  private int z;
  private WayPoint prePoint = null;
  private WayPoint nextPoint = null;

  public int X() {
    return this.x;
  }

  public int Y() {
    return this.y;
  }

  public int Z() {
    return this.z;
  }

  public boolean equals(Object point) {
    if (!(point instanceof WayPoint)) {
      return false;
    } else {
      WayPoint p = (WayPoint)point;
      return this.x == p.x && this.y == p.y && this.z == p.z;
    }
  }

  public int hashCode() {
    return (this.x << 20) + (this.y << 20) + this.z;
  }

  public WayPoint() {
  }

  public WayPoint(int inx, int iny, int inz) {
    this.x = inx;
    this.y = iny;
    this.z = inz;
  }

  public WayPoint(WayPoint wp) {
    this.x = wp.x;
    this.y = wp.y;
    this.z = wp.z;
  }

  public void setX(int inX) {
    this.x = inX;
  }

  public void setY(int inY) {
    this.y = inY;
  }

  public void setZ(int inZ) {
    this.z = inZ;
  }

  public void setPrePoint(WayPoint wp) {
    if (wp == null) {
      this.prePoint = null;
    } else {
      this.prePoint = new WayPoint(wp);
    }

  }

  public void setNextPoint(WayPoint wp) {
    if (wp == null) {
      this.nextPoint = null;
    } else {
      this.nextPoint = new WayPoint(wp);
    }

  }

  public WayPoint getNext() {
    return this.nextPoint;
  }

  public WayPoint getPre() {
    return this.prePoint;
  }
}
