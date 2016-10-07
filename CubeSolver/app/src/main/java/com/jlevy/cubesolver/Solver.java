package com.jlevy.cubesolver;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

public class Solver {

    Cube cube;
    int[] colors;
    int width;
    int height;

    public Solver(Bitmap bmp1, Bitmap bmp2)
    {
        width = bmp1.getWidth();
        height = bmp1.getHeight();
        setCube(bmp1, bmp2);
    }

    public void setCube(Bitmap bmp1, Bitmap bmp2)
    {

    }

}
