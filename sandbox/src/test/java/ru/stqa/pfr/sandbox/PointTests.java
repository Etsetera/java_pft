package ru.stqa.pfr.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDistance () {
    Point p1 = new Point(5, 0);
    Point p2 = new Point(6, 0);
    Assert.assertEquals(p1.distance(p2), 1.0);
  }
  @Test
  public void test2Distance () {
    Point p1 = new Point(5, 9);
    Point p2 = new Point(4, 1);
    Assert.assertEquals(p1.distance(p2), 8.06225774829855);
  }
  @Test
  public void test3Distance () {
    Point p1 = new Point(100, 50);
    Point p2 = new Point(50, 40);
    Assert.assertEquals(p1.distance(p2), 50.99019513592785);
  }
  @Test
  public void test4Distance () {
    Point p1 = new Point(0.5, 10.6);
    Point p2 = new Point(9.6, 1.6);
    Assert.assertEquals(p1.distance(p2), 12.798828071350908);
  }
}
