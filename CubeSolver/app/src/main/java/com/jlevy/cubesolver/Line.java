package com.jlevy.cubesolver;

public class Line {

    double m;
    double b;
    Point startPoint;
    Point endPoint;

    public Line(double m, double b)
    {
        this.m = m;
        this.b = b;
    }

    public Line(Point a, Point b)
    {
        this.m = getSlope(a, b);
        this.b = getYIntercept(a);
        this.startPoint = a;
        this.endPoint = b;
    }

    public double getSlope(Point a, Point b)
    {
        if(a.x - b.x != 0) {
            return ((b.y - a.y) / (b.x - a.x));
        }
        return Integer.MAX_VALUE;
    }

    public double getYIntercept(Point a)
    {
        return (a.y - m * a.x);
    }

    public Point getPOI(Line line)
    {
        double x = (line.b - this.b) / (this.m - line.m);
        return new Point(x, m * x + b);
    }

}
