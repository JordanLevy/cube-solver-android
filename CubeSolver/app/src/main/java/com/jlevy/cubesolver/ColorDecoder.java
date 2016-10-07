package com.jlevy.cubesolver;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

public class ColorDecoder {

    Bitmap bmp1;
    Bitmap bmp2;
    Point[] cornerPoints1;
    Point[][] edgePoints1;
    Point[] cornerPoints2;
    Point[][] edgePoints2;

    int[] firstCorners = {0, 1, 3, 0, 1, 2, 3, 4, 6};
    int[] secondCorners = {1, 2, 2, 3, 4, 5, 6, 5, 5};
    int[] firstEdges =  {0, 1, 4, 7, 5, 2};
    int[] secondEdges = {2, 3, 5, 1, 6, 8};

    Cube cube;
    public static Colour[] centerColours; //Y B R W O G

    public ColorDecoder(Bitmap bmp1, Point[] cornerPoints1, Point[][] edgePoints1, Bitmap bmp2, Point[] cornerPoints2, Point[][] edgePoints2) {
        this.bmp1 = bmp1;
        this.cornerPoints1 = cornerPoints1;
        this.edgePoints1 = edgePoints1;
        this.bmp2 = bmp2;
        this.cornerPoints2 = cornerPoints2;
        this.edgePoints2 = edgePoints2;
        setCube();
    }

    public Colour getColour(Bitmap bmp, Quad quad)
    {
        Colour c = new Colour(0, 0, 0);
        int n = 0;
        for(int x = (int)quad.minPoint().x; x < quad.maxPoint().x; x++)
        {
            for(int y = (int)quad.minPoint().y; y < quad.maxPoint().y; y++)
            {
                if(quad.contains(new Point(x, y))) {
                    int pixel = bmp.getPixel(x, y);
                    c.r += Color.red(pixel);
                    c.g += Color.green(pixel);
                    c.b += Color.blue(pixel);
                    n++;
                }
            }
        }
        if(n == 0)
        {
            return c;
        }
        c.r /= n;
        c.g /= n;
        c.b /= n;

        return c;
    }

    public void setCube()
    {
        cube = new Cube();
        centerColours = new Colour[6];
        Line h0, h1, v0, v1;

        //Setting the center stickers
        for(int i = 0; i < centerColours.length; i++) {
            if (i <= 2) {
                h0 = new Line(edgePoints1[firstEdges[(i % 3) * 2]][0], edgePoints1[secondEdges[(i % 3) * 2]][0]);
                h1 = new Line(edgePoints1[firstEdges[(i % 3) * 2]][1], edgePoints1[secondEdges[(i % 3) * 2]][1]);
                v0 = new Line(edgePoints1[firstEdges[(i % 3) * 2 + 1]][0], edgePoints1[secondEdges[(i % 3) * 2 + 1]][0]);
                v1 = new Line(edgePoints1[firstEdges[(i % 3) * 2 + 1]][1], edgePoints1[secondEdges[(i % 3) * 2 + 1]][1]);
                centerColours[i] = getColour(bmp1, new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v0), h1.getPOI(v1)));
            } else {
                h0 = new Line(edgePoints2[firstEdges[(i % 3) * 2]][0], edgePoints2[secondEdges[(i % 3) * 2]][0]);
                h1 = new Line(edgePoints2[firstEdges[(i % 3) * 2]][1], edgePoints2[secondEdges[(i % 3) * 2]][1]);
                v0 = new Line(edgePoints2[firstEdges[(i % 3) * 2 + 1]][0], edgePoints2[secondEdges[(i % 3) * 2 + 1]][0]);
                v1 = new Line(edgePoints2[firstEdges[(i % 3) * 2 + 1]][1], edgePoints2[secondEdges[(i % 3) * 2 + 1]][1]);
                centerColours[i] = getColour(bmp2, new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v0), h1.getPOI(v1)));
            }
        }

        //Setting the stickers that are visible from the first picture

        h0 = new Line(edgePoints1[0][0], edgePoints1[2][0]);
        h1 = new Line(edgePoints1[0][1], edgePoints1[2][1]);
        v0 = new Line(edgePoints1[1][0], edgePoints1[3][0]);
        v1 = new Line(edgePoints1[1][1], edgePoints1[3][1]);

        cube.cubie[0][0][0].sticker[0] = new Sticker(getColour(bmp1, new Quad(cornerPoints1[0], v0.endPoint, h0.getPOI(v0), h0.startPoint)));
        cube.cubie[0][0][1].sticker[0] = new Sticker(getColour(bmp1, new Quad(h0.startPoint, h0.getPOI(v0), h1.getPOI(v0), h1.startPoint)));
        cube.cubie[0][0][2].sticker[0] = new Sticker(getColour(bmp1, new Quad(h1.startPoint, h1.getPOI(v0), v0.startPoint, cornerPoints1[1])));
        cube.cubie[0][1][0].sticker[0] = new Sticker(getColour(bmp1, new Quad(v0.endPoint, v1.endPoint, h0.getPOI(v1), h0.getPOI(v0))));
        cube.cubie[0][1][1].sticker[0] = new Sticker(getColour(bmp1, new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v0), h1.getPOI(v1))));
        cube.cubie[0][1][2].sticker[0] = new Sticker(getColour(bmp1, new Quad(h1.getPOI(v0), h1.getPOI(v1), v1.startPoint, v0.startPoint)));
        cube.cubie[0][2][0].sticker[0] = new Sticker(getColour(bmp1, new Quad(v1.endPoint, cornerPoints1[3], h0.endPoint, h0.getPOI(v1))));
        cube.cubie[0][2][1].sticker[0] = new Sticker(getColour(bmp1, new Quad(h0.getPOI(v1), h0.endPoint, h1.endPoint, h1.getPOI(v1))));
        cube.cubie[0][2][2].sticker[0] = new Sticker(getColour(bmp1, new Quad(h1.getPOI(v1), h1.endPoint, cornerPoints1[2], v1.startPoint)));

        h0 = new Line(edgePoints1[4][0], edgePoints1[5][0]);
        h1 = new Line(edgePoints1[4][1], edgePoints1[5][1]);
        v0 = new Line(edgePoints1[7][0], edgePoints1[1][0]);
        v1 = new Line(edgePoints1[7][1], edgePoints1[1][1]);

        cube.cubie[0][0][2].sticker[1] = new Sticker(getColour(bmp1, new Quad(cornerPoints1[1], v0.endPoint, h0.getPOI(v0), h0.startPoint)));
        cube.cubie[1][0][2].sticker[0] = new Sticker(getColour(bmp1, new Quad(h0.startPoint, h0.getPOI(v0), h1.getPOI(v0), h1.startPoint)));
        cube.cubie[2][0][2].sticker[2] = new Sticker(getColour(bmp1, new Quad(h1.startPoint, h1.getPOI(v0), v0.startPoint, cornerPoints1[4])));
        cube.cubie[0][1][2].sticker[1] = new Sticker(getColour(bmp1, new Quad(v0.endPoint, v1.endPoint, h0.getPOI(v1), h0.getPOI(v0))));
        cube.cubie[1][1][2].sticker[0] = new Sticker(getColour(bmp1, new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v0), h1.getPOI(v1))));
        cube.cubie[2][1][2].sticker[1] = new Sticker(getColour(bmp1, new Quad(h1.getPOI(v0), h1.getPOI(v1), v1.startPoint, v0.startPoint)));
        cube.cubie[0][2][2].sticker[2] = new Sticker(getColour(bmp1, new Quad(v1.endPoint, cornerPoints1[2], h0.endPoint, h0.getPOI(v1))));
        cube.cubie[1][2][2].sticker[0] = new Sticker(getColour(bmp1, new Quad(h0.getPOI(v1), h0.endPoint, h1.endPoint, h1.getPOI(v1))));
        cube.cubie[2][2][2].sticker[1] = new Sticker(getColour(bmp1, new Quad(h1.getPOI(v1), h1.endPoint, cornerPoints1[5], v1.startPoint)));

        h0 = new Line(edgePoints1[5][0], edgePoints1[6][0]);
        h1 = new Line(edgePoints1[5][1], edgePoints1[6][1]);
        v0 = new Line(edgePoints1[8][1], edgePoints1[2][1]);
        v1 = new Line(edgePoints1[8][0], edgePoints1[2][0]);

        cube.cubie[0][2][2].sticker[1] = new Sticker(getColour(bmp1, new Quad(cornerPoints1[2], v0.endPoint, h0.getPOI(v0), h0.startPoint)));
        cube.cubie[1][2][2].sticker[1] = new Sticker(getColour(bmp1, new Quad(h0.startPoint, h0.getPOI(v0), h1.getPOI(v0), h1.startPoint)));
        cube.cubie[2][2][2].sticker[2] = new Sticker(getColour(bmp1, new Quad(h1.startPoint, h1.getPOI(v0), v0.startPoint, cornerPoints1[5])));
        cube.cubie[0][2][1].sticker[1] = new Sticker(getColour(bmp1, new Quad(v0.endPoint, v1.endPoint, h0.getPOI(v1), h0.getPOI(v0))));
        cube.cubie[1][2][1].sticker[0] = new Sticker(getColour(bmp1, new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v0), h1.getPOI(v1))));
        cube.cubie[2][2][1].sticker[1] = new Sticker(getColour(bmp1, new Quad(h1.getPOI(v0), h1.getPOI(v1), v1.startPoint, v0.startPoint)));
        cube.cubie[0][2][0].sticker[2] = new Sticker(getColour(bmp1, new Quad(v1.endPoint, cornerPoints1[3], h0.endPoint, h0.getPOI(v1))));
        cube.cubie[1][2][0].sticker[1] = new Sticker(getColour(bmp1, new Quad(h0.getPOI(v1), h0.endPoint, h1.endPoint, h1.getPOI(v1))));
        cube.cubie[2][2][0].sticker[1] = new Sticker(getColour(bmp1, new Quad(h1.getPOI(v1), h1.endPoint, cornerPoints1[6], v1.startPoint)));


        //Setting the stickers that are visible from the second picture

        h0 = new Line(edgePoints2[0][0], edgePoints2[2][0]);
        h1 = new Line(edgePoints2[0][1], edgePoints2[2][1]);
        v0 = new Line(edgePoints2[1][0], edgePoints2[3][0]);
        v1 = new Line(edgePoints2[1][1], edgePoints2[3][1]);

        cube.cubie[2][2][2].sticker[0] = new Sticker(getColour(bmp2, new Quad(cornerPoints2[0], v0.endPoint, h0.getPOI(v0), h0.startPoint)));
        cube.cubie[2][1][2].sticker[0] = new Sticker(getColour(bmp2, new Quad(h0.startPoint, h0.getPOI(v0), h1.getPOI(v0), h1.startPoint)));
        cube.cubie[2][0][2].sticker[0] = new Sticker(getColour(bmp2, new Quad(h1.startPoint, h1.getPOI(v0), v0.startPoint, cornerPoints2[1])));
        cube.cubie[2][2][1].sticker[0] = new Sticker(getColour(bmp2, new Quad(v0.endPoint, v1.endPoint, h0.getPOI(v1), h0.getPOI(v0))));
        cube.cubie[2][1][1].sticker[0] = new Sticker(getColour(bmp2, new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v0), h1.getPOI(v1))));
        cube.cubie[2][0][1].sticker[0] = new Sticker(getColour(bmp2, new Quad(h1.getPOI(v0), h1.getPOI(v1), v1.startPoint, v0.startPoint)));
        cube.cubie[2][2][0].sticker[0] = new Sticker(getColour(bmp2, new Quad(v1.endPoint, cornerPoints2[3], h0.endPoint, h0.getPOI(v1))));
        cube.cubie[2][1][0].sticker[0] = new Sticker(getColour(bmp2, new Quad(h0.getPOI(v1), h0.endPoint, h1.endPoint, h1.getPOI(v1))));
        cube.cubie[2][0][0].sticker[0] = new Sticker(getColour(bmp2, new Quad(h1.getPOI(v1), h1.endPoint, cornerPoints2[2], v1.startPoint)));

        h0 = new Line(edgePoints2[4][0], edgePoints2[5][0]);
        h1 = new Line(edgePoints2[4][1], edgePoints2[5][1]);
        v0 = new Line(edgePoints2[7][0], edgePoints2[1][0]);
        v1 = new Line(edgePoints2[7][1], edgePoints2[1][1]);

        cube.cubie[2][0][2].sticker[1] = new Sticker(getColour(bmp2, new Quad(cornerPoints2[1], v0.endPoint, h0.getPOI(v0), h0.startPoint)));
        cube.cubie[1][0][2].sticker[1] = new Sticker(getColour(bmp2, new Quad(h0.startPoint, h0.getPOI(v0), h1.getPOI(v0), h1.startPoint)));
        cube.cubie[0][0][2].sticker[2] = new Sticker(getColour(bmp2, new Quad(h1.startPoint, h1.getPOI(v0), v0.startPoint, cornerPoints2[4])));
        cube.cubie[2][0][1].sticker[1] = new Sticker(getColour(bmp2, new Quad(v0.endPoint, v1.endPoint, h0.getPOI(v1), h0.getPOI(v0))));
        cube.cubie[1][0][1].sticker[0] = new Sticker(getColour(bmp2, new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v0), h1.getPOI(v1))));
        cube.cubie[0][0][1].sticker[1] = new Sticker(getColour(bmp2, new Quad(h1.getPOI(v0), h1.getPOI(v1), v1.startPoint, v0.startPoint)));
        cube.cubie[2][0][0].sticker[2] = new Sticker(getColour(bmp2, new Quad(v1.endPoint, cornerPoints2[2], h0.endPoint, h0.getPOI(v1))));
        cube.cubie[1][0][0].sticker[1] = new Sticker(getColour(bmp2, new Quad(h0.getPOI(v1), h0.endPoint, h1.endPoint, h1.getPOI(v1))));
        cube.cubie[0][0][0].sticker[1] = new Sticker(getColour(bmp2, new Quad(h1.getPOI(v1), h1.endPoint, cornerPoints2[5], v1.startPoint)));

        h0 = new Line(edgePoints2[5][0], edgePoints2[6][0]);
        h1 = new Line(edgePoints2[5][1], edgePoints2[6][1]);
        v0 = new Line(edgePoints2[8][1], edgePoints2[2][1]);
        v1 = new Line(edgePoints2[8][0], edgePoints2[2][0]);

        cube.cubie[2][0][0].sticker[1] = new Sticker(getColour(bmp2, new Quad(cornerPoints2[2], v0.endPoint, h0.getPOI(v0), h0.startPoint)));
        cube.cubie[1][0][0].sticker[0] = new Sticker(getColour(bmp2, new Quad(h0.startPoint, h0.getPOI(v0), h1.getPOI(v0), h1.startPoint)));
        cube.cubie[0][0][0].sticker[2] = new Sticker(getColour(bmp2, new Quad(h1.startPoint, h1.getPOI(v0), v0.startPoint, cornerPoints2[5])));
        cube.cubie[2][1][0].sticker[1] = new Sticker(getColour(bmp2, new Quad(v0.endPoint, v1.endPoint, h0.getPOI(v1), h0.getPOI(v0))));
        cube.cubie[1][1][0].sticker[0] = new Sticker(getColour(bmp2, new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v0), h1.getPOI(v1))));
        cube.cubie[0][1][0].sticker[1] = new Sticker(getColour(bmp2, new Quad(h1.getPOI(v0), h1.getPOI(v1), v1.startPoint, v0.startPoint)));
        cube.cubie[2][2][0].sticker[2] = new Sticker(getColour(bmp2, new Quad(v1.endPoint, cornerPoints2[3], h0.endPoint, h0.getPOI(v1))));
        cube.cubie[1][2][0].sticker[0] = new Sticker(getColour(bmp2, new Quad(h0.getPOI(v1), h0.endPoint, h1.endPoint, h1.getPOI(v1))));
        cube.cubie[0][2][0].sticker[1] = new Sticker(getColour(bmp2, new Quad(h1.getPOI(v1), h1.endPoint, cornerPoints2[6], v1.startPoint)));

        for(int z = 0; z < 3; z++)
        {
            for(int x = 0; x < 3; x++)
            {
                for(int y = 0; y < 3; y++)
                {
                    Log.i("  ", "cubie[" + z + "][" + x + "][" + y + "]"  + cube.cubie[z][x][y].toString());
                }
            }
        }
    }
}
