package ru.job4j.condition;
/**
 * @author MShonorov
 * @version $Id$
 * @since 0.1
 */
public class Point {
    /**
     * Point coordinates.
     */
    private int x;
    private int y;
    /**
     * Constructor.
     * @param x axis.
     * @param y axis.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Distance between two points.
     * @param that target point.
     * @return distance.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)
        );
    }
    /**
     * Main.
     * @param args main args.
     */
    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);
        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками А и В : " + result);
    }
}
