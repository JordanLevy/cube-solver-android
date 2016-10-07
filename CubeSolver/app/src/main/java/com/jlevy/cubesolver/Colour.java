package com.jlevy.cubesolver;

public class Colour {

    int r;
    int g;
    int b;

    public Colour(int r, int g, int b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getBrightness()
    {
        return (int)Math.sqrt(r * r * .241 + g * g * .691 + b * b * .068);
    }

}
