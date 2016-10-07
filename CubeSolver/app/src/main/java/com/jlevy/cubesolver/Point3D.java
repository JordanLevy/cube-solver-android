package com.jlevy.cubesolver;

import android.graphics.Color;

public class Point3D {

    public double x;
    public double y;
    public double z;
    Color color;

    public Point3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(double x, double y,double z,  Color color)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }

    public boolean isEqual(Point3D p)
    {
        if(x == p.x && y == p.y && z == p.z)
        {
            return true;
        }
        return false;
    }
}
