package net.diyigemt.ariadnes.controller;

import net.diyigemt.ariadnes.logic.LinkPath;
import net.diyigemt.ariadnes.logic.WayPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WayPointStorage {
  private static final WayPointStorage INSTANCE = new WayPointStorage();
  private final PathArray paths = new PathArray();
  private final PointSet points = new PointSet();

  private WayPointStorage() {}

  public static WayPointStorage getInstance() { return INSTANCE; }

  public PathArray getPaths() {
    return paths;
  }

  public PointSet getPoints() {
    return points;
  }

  public static class PathArray {
    private final ArrayList<LinkPath> linkPath = new ArrayList<>();

    public PathArray() {
    }

    public void clear() {
      linkPath.clear();
    }

    public boolean isNotEmpty() {
      return !linkPath.isEmpty();
    }

    public Iterator<LinkPath> getIterator() {
      return linkPath.iterator();
    }

    public void addPath(LinkPath lp) {
      if (linkPath.contains(lp)) {
        linkPath.add(lp);
      }

    }
  }

  public static class PointSet {
    private final Map<WayPoint, WayPoint> pointSet = new HashMap<>();

    public PointSet() {
    }

    public void clear() {
      pointSet.clear();
    }

    public boolean isInSet(WayPoint wp) {
      return pointSet.containsKey(wp);
    }

    public boolean isNotEmpty() {
      return !pointSet.isEmpty();
    }

    public WayPoint getOrigInst(WayPoint wp) {
      return pointSet.get(wp);
    }

    public void removePoint(WayPoint wp) {
      pointSet.remove(wp);
    }

    public void addPoint(WayPoint wp) {
      pointSet.put(wp, wp);
    }

    public Iterator<Map.Entry<WayPoint, WayPoint>> getIterator() {
      return pointSet.entrySet().iterator();
    }
  }
}
