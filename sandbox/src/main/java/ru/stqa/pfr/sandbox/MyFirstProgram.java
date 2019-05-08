package ru.stqa.pfr.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Elena");

    Square s = new Square(5);
    System.out.println("Пплощадь квадрата со стороной " + s.l + "=" + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Пплощадь прямоугольника со сторонами " + r.a + " и " + r.b + "=" + r.area());

    Point p1 = new Point(5, 9);
    Point p2 = new Point(4, 1);
    System.out.println("Расстояние между точкой p1 c координатами (" + p1.x + ";" + p1.y + ") и точкой p2 с координатами (" + p2.x + ";" + p2.y + ")" + " = " + p1.distance(p2));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}