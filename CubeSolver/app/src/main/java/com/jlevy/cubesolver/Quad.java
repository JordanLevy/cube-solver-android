package com.jlevy.cubesolver;

public class Quad {

    Point[] vertices;
    Colour colour;

    public Quad(Point a, Point b, Point c, Point d)
    {
        vertices = new Point[4];
        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
        vertices[3] = d;
    }

    public Point minPoint()
    {
        Point p = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for(int i = 0; i < vertices.length; i++)
        {
            p.x = Math.min(p.x, vertices[i].x);
            p.y = Math.min(p.y, vertices[i].y);
        }
        return p;
    }

    public Point maxPoint()
    {
        Point p = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for(int i = 0; i < vertices.length; i++)
        {
            p.x = Math.max(p.x, vertices[i].x);
            p.y = Math.max(p.y, vertices[i].y);
        }
        return p;
    }

    public boolean contains(Point test) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
            if ((vertices[i].y > test.y) != (vertices[j].y > test.y) && (test.x < (vertices[j].x - vertices[i].x) * (test.y - vertices[i].y) / (vertices[j].y-vertices[i].y) + vertices[i].x)) {
                result = !result;
            }
        }
        return result;
    }
}
