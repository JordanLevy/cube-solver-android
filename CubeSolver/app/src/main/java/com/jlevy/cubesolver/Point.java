package com.jlevy.cubesolver;

import android.graphics.Color;

public class Point {

    public double x;
    public double y;
    Color color;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y, Color color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public double distance(Point p)
    {
        return Math.sqrt((p.x - x)*(p.x - x) + (p.y - y)*(p.y - y));
    }

    public boolean isInside(Quad quad)
    {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = quad.vertices.length - 1; i < quad.vertices.length; j = i++) {
            if ((quad.vertices[i].y > this.y) != (quad.vertices[j].y > this.y) &&
                    (this.x < (quad.vertices[j].x - quad.vertices[i].x) * (this.y - quad.vertices[i].y) / (quad.vertices[j].y-quad.vertices[i].y) + quad.vertices[i].x)) {
                result = !result;
            }
        }
        return result;
    }
}
