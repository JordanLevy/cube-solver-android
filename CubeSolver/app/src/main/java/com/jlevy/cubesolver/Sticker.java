package com.jlevy.cubesolver;

public class Sticker {

    Colour rgbColour;
    public int colour;

    public Sticker()
    {

    }

    public Sticker(Colour rgbColour)
    {
        this.rgbColour = rgbColour;
        colour = roundColour();
    }

    public Sticker(int colour)
    {
        this.colour = colour;
        this.rgbColour = CubePainterActivity.colourPalette[colour];
    }

    public boolean matches(Sticker s)
    {
        return this.colour == s.colour;
    }

    public boolean matches(Sticker s1, Sticker s2)
    {
        return ((this.colour == s1.colour) && (this.colour == s2.colour));
    }

    public boolean opposite(Sticker s)
    {
        if((this.colour == 0 && s.colour == 3) || (this.colour == 3 && s.colour == 0) || (this.colour == 1 && s.colour == 5) || (this.colour == 5 && s.colour == 1) || (this.colour == 3 && s.colour == 6) || (this.colour == 6 && s.colour == 3))
        {
            return true;
        }
        return false;
    }

    public boolean adjacent(Sticker s)
    {
        return (!matches(s) && ! opposite(s));
    }

    public double compareColour(Colour a)
    {
        return Math.pow(Math.abs(a.r - rgbColour.r), 2) + Math.pow(Math.abs(a.g - rgbColour.g), 2) + Math.pow(Math.abs(a.b - rgbColour.b), 2);
    }

    public int roundColour()
    {
        int colour = 0;
        double distance = compareColour(CubePainterActivity.centerColours[0]);
        for(int i = 1; i < CubePainterActivity.centerColours.length; i++)
        {
            if(compareColour(CubePainterActivity.centerColours[i]) < distance)
            {
                colour = i;
                distance = compareColour(CubePainterActivity.centerColours[colour]);
            }
        }
        return colour;
    }

    public void setColour(int colour)
    {
        this.colour = colour;
        this.rgbColour = CubePainterActivity.colourPalette[colour];
    }

    @Override
    public String toString()
    {
        return new StringBuffer()
                .append("Colour-")
                .append(colour).toString();
    }

}
