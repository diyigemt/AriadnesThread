//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.diyigemt.ariadnes.logic;

public class LinkPath {
  private WayPoint prePoint;
  private WayPoint nextPoint;

  public LinkPath(WayPoint preP, WayPoint nextP) {
    this.prePoint = preP;
    this.nextPoint = nextP;
  }

  public boolean equals(Object path) {
    if (!(path instanceof LinkPath)) {
      return false;
    } else {
      LinkPath pt = (LinkPath)path;
      return this.prePoint.equals(pt.prePoint) && this.nextPoint.equals(pt.nextPoint) || this.prePoint.equals(pt.nextPoint) && this.nextPoint.equals(pt.prePoint);
    }
  }

  public WayPoint prePoint() {
    return this.prePoint;
  }

  public WayPoint nextPoint() {
    return this.nextPoint;
  }
}
