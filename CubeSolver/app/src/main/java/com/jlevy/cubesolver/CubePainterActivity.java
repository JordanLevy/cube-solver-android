package com.jlevy.cubesolver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class CubePainterActivity extends Activity {

    CubePainterView cpv;
    Cube cube;
    public static Colour[] centerColours; //Y B R W O G
    public static Colour[] colourPalette = {new Colour(255, 255, 0), new Colour(0, 195, 255), new Colour(255, 0, 0), new Colour(255, 255, 255), new Colour(255, 174, 0), new Colour(0, 255, 76), new Colour(192, 192, 192)};
    public static Bitmap bmp1;
    public static Bitmap bmp2;
    public static Point[] cornerPoints1;
    public static Point[][] edgePoints1 = new Point[9][2];
    public static Point[] cornerPoints2;
    public static Point[][] edgePoints2 = new Point[9][2];
    Point[] cornerPoints = {new Point(100, 50), new Point(15, 80), new Point(100, 115), new Point(185, 80), new Point(15, 130), new Point(100, 170), new Point(185, 130)};
    Point[][] edgePoints = new Point[9][2];
    Bitmap ok_button;

    Bitmap paint_button;
    int paint_buttonX = MainActivity.display.getWidth()/6;
    int paint_buttonY = MainActivity.display.getHeight()/8;
    int paint_buttonExpandedRadius = (int)(0.9*Math.min((float)(MainActivity.display.getWidth())/6, (float)(MainActivity.display.getHeight()/6)));
    int paint_buttonUnexpandedRadius = (int)(0.6*paint_buttonExpandedRadius);

    Bitmap erase_button;
    int erase_buttonX = (int)(0.5*MainActivity.display.getWidth());
    int erase_buttonY = MainActivity.display.getHeight()/8;
    int erase_buttonRadius = (int)(0.6*0.16*Math.min(MainActivity.display.getWidth(), MainActivity.display.getHeight()));

    Bitmap flip_button;
    int flip_buttonX = (int)(0.82*MainActivity.display.getWidth());
    int flip_buttonY = MainActivity.display.getHeight()/8;
    int flip_buttonRadius = (int)(0.6*0.16*Math.min(MainActivity.display.getWidth(), MainActivity.display.getHeight()));

    boolean colourWheelOpen = false;
    int sliverSelected = 0;
    int colourSelected = 0;
    int sideViewed = 0;
    int stage = 1;
    int[] firstCorners = {0, 1, 3, 0, 1, 2, 3, 4, 6};
    int[] secondCorners = {1, 2, 2, 3, 4, 5, 6, 5, 5};
    int[] firstEdges =  {0, 1, 4, 7, 5, 2};
    int[] secondEdges = {2, 3, 5, 1, 6, 8};
    Quad[][][] quad = new Quad[3][3][3];

    public CubePainterActivity()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cpv = new CubePainterView(this);
        setContentView(cpv);
    }

    public void setCornerPoints()
    {
        for(int i = 0; i < cornerPoints.length; i++)
        {
            cornerPoints[i].x = cornerPoints[i].x / 200 * MainActivity.display.getWidth();
            cornerPoints[i].y = cornerPoints[i].y / 200 * MainActivity.display.getHeight();
        }
    }

    public void setEdgePoints()
    {
        for (int i = 0; i < firstCorners.length; i++) {
            double dx = (cornerPoints[secondCorners[i]].x - cornerPoints[firstCorners[i]].x) / 3.0;
            double dy = (cornerPoints[secondCorners[i]].y - cornerPoints[firstCorners[i]].y) / 3.0;
            edgePoints[i][0] = new Point(cornerPoints[firstCorners[i]].x + dx, cornerPoints[firstCorners[i]].y + dy);
            edgePoints[i][1] = new Point(cornerPoints[secondCorners[i]].x - dx, cornerPoints[secondCorners[i]].y - dy);
        }
    }

    public void setQuads()
    {
        Line h0, h1, v0, v1;

        h0 = new Line(edgePoints[0][0], edgePoints[2][0]);
        h1 = new Line(edgePoints[0][1], edgePoints[2][1]);
        v0 = new Line(edgePoints[1][0], edgePoints[3][0]);
        v1 = new Line(edgePoints[1][1], edgePoints[3][1]);

        quad[0][0][0] = new Quad(cornerPoints[0], v0.endPoint, h0.getPOI(v0), h0.startPoint);
        quad[0][0][1] = new Quad(h0.startPoint, h0.getPOI(v0), h1.getPOI(v0), h1.startPoint);
        quad[0][0][2] = new Quad(h1.startPoint, h1.getPOI(v0), v0.startPoint, cornerPoints[1]);
        quad[0][1][0] = new Quad(v0.endPoint, v1.endPoint, h0.getPOI(v1), h0.getPOI(v0));
        quad[0][1][1] = new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v1), h1.getPOI(v0));
        quad[0][1][2] = new Quad(h1.getPOI(v0), h1.getPOI(v1), v1.startPoint, v0.startPoint);
        quad[0][2][0] = new Quad(v1.endPoint, cornerPoints[3], h0.endPoint, h0.getPOI(v1));
        quad[0][2][1] = new Quad(h0.getPOI(v1), h0.endPoint, h1.endPoint, h1.getPOI(v1));
        quad[0][2][2] = new Quad(h1.getPOI(v1), h1.endPoint, cornerPoints[2], v1.startPoint);

        h0 = new Line(edgePoints[4][0], edgePoints[5][0]);
        h1 = new Line(edgePoints[4][1], edgePoints[5][1]);
        v0 = new Line(edgePoints[7][0], edgePoints[1][0]);
        v1 = new Line(edgePoints[7][1], edgePoints[1][1]);

        quad[1][0][0] = new Quad(cornerPoints[1], v0.endPoint, h0.getPOI(v0), h0.startPoint);
        quad[1][0][1] = new Quad(h0.startPoint, h0.getPOI(v0), h1.getPOI(v0), h1.startPoint);
        quad[1][0][2] = new Quad(h1.startPoint, h1.getPOI(v0), v0.startPoint, cornerPoints[4]);
        quad[1][1][0] = new Quad(v0.endPoint, v1.endPoint, h0.getPOI(v1), h0.getPOI(v0));
        quad[1][1][1] = new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v1), h1.getPOI(v0));
        quad[1][1][2] = new Quad(h1.getPOI(v0), h1.getPOI(v1), v1.startPoint, v0.startPoint);
        quad[1][2][0] = new Quad(v1.endPoint, cornerPoints[2], h0.endPoint, h0.getPOI(v1));
        quad[1][2][1] = new Quad(h0.getPOI(v1), h0.endPoint, h1.endPoint, h1.getPOI(v1));
        quad[1][2][2] = new Quad(h1.getPOI(v1), h1.endPoint, cornerPoints[5], v1.startPoint);

        h0 = new Line(edgePoints[5][0], edgePoints[6][0]);
        h1 = new Line(edgePoints[5][1], edgePoints[6][1]);
        v0 = new Line(edgePoints[8][1], edgePoints[2][1]);
        v1 = new Line(edgePoints[8][0], edgePoints[2][0]);

        quad[2][0][0] = new Quad(cornerPoints[2], v0.endPoint, h0.getPOI(v0), h0.startPoint);
        quad[2][0][1] = new Quad(h0.startPoint, h0.getPOI(v0), h1.getPOI(v0), h1.startPoint);
        quad[2][0][2] = new Quad(h1.startPoint, h1.getPOI(v0), v0.startPoint, cornerPoints[5]);
        quad[2][1][0] = new Quad(v0.endPoint, v1.endPoint, h0.getPOI(v1), h0.getPOI(v0));
        quad[2][1][1] = new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v1), h1.getPOI(v0));
        quad[2][1][2] = new Quad(h1.getPOI(v0), h1.getPOI(v1), v1.startPoint, v0.startPoint);
        quad[2][2][0] = new Quad(v1.endPoint, cornerPoints[3], h0.endPoint, h0.getPOI(v1));
        quad[2][2][1] = new Quad(h0.getPOI(v1), h0.endPoint, h1.endPoint, h1.getPOI(v1));
        quad[2][2][2] = new Quad(h1.getPOI(v1), h1.endPoint, cornerPoints[6], v1.startPoint);
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

    public void setBlankCube()
    {
        cube = new Cube();
        for(int x = 0; x < 3; x ++)
        {
            for(int y = 0; y < 3; y++)
            {
                for(int z = 0; z < 3; z++)
                {
                    for(int i = 0; i < cube.cubie[x][y][z].type; i++)
                    {
                        cube.cubie[x][y][z].sticker[i].colour = 6;
                    }
                }
            }
        }
        cube.cubie[0][1][1].sticker[0].colour = 0;
        cube.cubie[1][1][2].sticker[0].colour = 1;
        cube.cubie[1][2][1].sticker[0].colour = 2;
        cube.cubie[2][1][1].sticker[0].colour = 3;
        cube.cubie[1][0][1].sticker[0].colour = 4;
        cube.cubie[1][1][0].sticker[0].colour = 5;
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
                centerColours[i] = getColour(bmp1, new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v1), h1.getPOI(v0)));
            } else {
                h0 = new Line(edgePoints2[firstEdges[(i % 3) * 2]][0], edgePoints2[secondEdges[(i % 3) * 2]][0]);
                h1 = new Line(edgePoints2[firstEdges[(i % 3) * 2]][1], edgePoints2[secondEdges[(i % 3) * 2]][1]);
                v0 = new Line(edgePoints2[firstEdges[(i % 3) * 2 + 1]][0], edgePoints2[secondEdges[(i % 3) * 2 + 1]][0]);
                v1 = new Line(edgePoints2[firstEdges[(i % 3) * 2 + 1]][1], edgePoints2[secondEdges[(i % 3) * 2 + 1]][1]);
                centerColours[i] = getColour(bmp2, new Quad(h0.getPOI(v0), h0.getPOI(v1), h1.getPOI(v1), h1.getPOI(v0)));
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
        cube.cubie[0][1][1].sticker[0] = new Sticker(0);
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
        cube.cubie[1][1][2].sticker[0] = new Sticker(1);
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
        cube.cubie[1][2][1].sticker[0] = new Sticker(2);
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
        cube.cubie[2][1][1].sticker[0] = new Sticker(3);
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
        cube.cubie[1][0][1].sticker[0] = new Sticker(4);
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
        cube.cubie[1][1][0].sticker[0] = new Sticker(5);
        cube.cubie[0][1][0].sticker[1] = new Sticker(getColour(bmp2, new Quad(h1.getPOI(v0), h1.getPOI(v1), v1.startPoint, v0.startPoint)));
        cube.cubie[2][2][0].sticker[2] = new Sticker(getColour(bmp2, new Quad(v1.endPoint, cornerPoints2[3], h0.endPoint, h0.getPOI(v1))));
        cube.cubie[1][2][0].sticker[0] = new Sticker(getColour(bmp2, new Quad(h0.getPOI(v1), h0.endPoint, h1.endPoint, h1.getPOI(v1))));
        cube.cubie[0][2][0].sticker[1] = new Sticker(getColour(bmp2, new Quad(h1.getPOI(v1), h1.endPoint, cornerPoints2[6], v1.startPoint)));

    }

    public class CubePainterView extends View {

        private Paint paint;
        Context context;

        public CubePainterView(Context c)
        {
            super(c);
            context=c;
            paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);

            ok_button = BitmapFactory.decodeResource(getResources(), R.drawable.ok_button);

            paint_button = BitmapFactory.decodeResource(getResources(), R.drawable.paint_button);
            paint_button = Bitmap.createScaledBitmap(paint_button, paint_buttonUnexpandedRadius*2, paint_buttonUnexpandedRadius*2, false);

            erase_button = BitmapFactory.decodeResource(getResources(), R.drawable.erase_button);
            erase_button = Bitmap.createScaledBitmap(erase_button, erase_buttonRadius*2, erase_buttonRadius*2, false);

            flip_button = BitmapFactory.decodeResource(getResources(), R.drawable.flip_button);
            flip_button = Bitmap.createScaledBitmap(flip_button, flip_buttonRadius*2, flip_buttonRadius*2, false);

            if(bmp1 != null) {
                setCube();
            }
            else
            {
                setBlankCube();
            }
            setCornerPoints();
            setEdgePoints();
            setQuads();
            invalidate();
        }

        public int faceByName(String s)
        {
            if (s.equals("U"))
            {
                return 0;
            }
            else if (s.equals("F"))
            {
                return 1;
            }
            else if (s.equals("R"))
            {
                return 2;
            }
            else if (s.equals("D"))
            {
                return 3;
            }
            else if (s.equals("L"))
            {
                return 4;
            }
            else if (s.equals("B"))
            {
                return 5;
            }
            return -1;
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            canvas.drawRect(0, 0, MainActivity.display.getWidth(), MainActivity.display.getHeight(), paint);

            if(sideViewed == 0) {

                for(int a = 0; a < 3; a++) {
                    for(int b = 0; b < 3; b++) {
                        drawPolygon(canvas, colourPalette[cube.cubie[0][a][b].sticker[0].colour], quad[0][a][b].vertices);
                    }
                }

                for(int a = 0; a < 3; a++) {
                    for(int b = 0; b < 3; b++) {
                        if(b == 1) {
                            drawPolygon(canvas, colourPalette[cube.cubie[b][a][2].sticker[0].colour], quad[1][a][b].vertices);
                        } else if((b == 2 && a == 0) || (b == 0 && a == 2)) {
                            drawPolygon(canvas, colourPalette[cube.cubie[b][a][2].sticker[2].colour], quad[1][a][b].vertices);
                        } else {
                            drawPolygon(canvas, colourPalette[cube.cubie[b][a][2].sticker[1].colour], quad[1][a][b].vertices);
                        }
                    }
                }

                for(int a = 0; a < 3; a++) {
                    for(int b = 0; b < 3; b++) {
                        if(b == 1 && 2 - a == 1) {
                            drawPolygon(canvas, colourPalette[cube.cubie[b][2][2 - a].sticker[0].colour], quad[2][a][b].vertices);
                        } else if((b == 0 && 2 - a == 0) || (b == 2 && 2 - a == 2)) {
                            drawPolygon(canvas, colourPalette[cube.cubie[b][2][2 - a].sticker[2].colour], quad[2][a][b].vertices);
                        } else {
                            drawPolygon(canvas, colourPalette[cube.cubie[b][2][2 - a].sticker[1].colour], quad[2][a][b].vertices);
                        }
                    }
                }
            }
            else if(sideViewed == 1) {
                for (int a = 0; a < 3; a++) {
                    for(int b = 0; b < 3; b++) {
                        drawPolygon(canvas, colourPalette[cube.cubie[2][2 - a][2 - b].sticker[0].colour], quad[0][b][a].vertices);
                    }
                }

                for (int a = 0; a < 3; a++) {
                    for(int b = 0; b < 3; b++) {
                        if(b == 1 && a == 1) {
                            drawPolygon(canvas, colourPalette[cube.cubie[2 - b][0][2 - a].sticker[0].colour], quad[1][a][b].vertices);
                        } else if((b == 2 && a == 0) || (b == 0 && a == 2)) {
                            drawPolygon(canvas, colourPalette[cube.cubie[2 - b][0][2 - a].sticker[2].colour], quad[1][a][b].vertices);
                        } else {
                            drawPolygon(canvas, colourPalette[cube.cubie[2 - b][0][2 - a].sticker[1].colour], quad[1][a][b].vertices);
                        }
                    }
                }

                for (int a = 0; a < 3; a++) {
                    for(int b = 0; b < 3; b++) {
                        if(b == 1) {
                            drawPolygon(canvas, colourPalette[cube.cubie[2 - b][a][0].sticker[0].colour], quad[2][a][b].vertices);
                        } else if((b == 2 && a == 0) || (b == 0 && a == 2)) {
                            drawPolygon(canvas, colourPalette[cube.cubie[2 - b][a][0].sticker[2].colour], quad[2][a][b].vertices);
                        } else {
                            drawPolygon(canvas, colourPalette[cube.cubie[2 - b][a][0].sticker[1].colour], quad[2][a][b].vertices);
                        }
                    }
                }
            }

            canvas.drawBitmap(ok_button, MainActivity.display.getWidth() - ok_button.getWidth(), MainActivity.display.getHeight() - ok_button.getHeight(), paint);

            if(colourWheelOpen == false) {
                paint.setColor(Color.rgb(colourPalette[colourSelected].r, colourPalette[colourSelected].g, colourPalette[colourSelected].b));
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(paint_buttonX, paint_buttonY, paint_buttonUnexpandedRadius, paint);
                canvas.drawBitmap(paint_button, paint_buttonX - paint_buttonUnexpandedRadius, paint_buttonY - paint_buttonUnexpandedRadius, paint);
            }
            else
            {
                RectF rectF = new RectF(paint_buttonX - paint_buttonExpandedRadius, paint_buttonY - paint_buttonExpandedRadius, paint_buttonX + paint_buttonExpandedRadius, paint_buttonY + paint_buttonExpandedRadius);
                //canvas.drawCircle(174, 174, 64, paint);
                int j = 0; //used to swap orange and green so that opposite colours are on opposite ends of the wheel
                for(int i = 0; i < colourPalette.length - 1; i++) {
                    j = i;
                    if(i == 4)
                    {
                        j = 5;
                    }
                    if(i == 5)
                    {
                        j = 4;
                    }
                    if(j == colourSelected) {
                        paint.setStyle(Paint.Style.FILL);
                        paint.setColor(Color.WHITE);
                        canvas.drawArc(new RectF(paint_buttonX - paint_buttonExpandedRadius - 5, paint_buttonY - paint_buttonExpandedRadius - 5, paint_buttonX + paint_buttonExpandedRadius + 5, paint_buttonY + paint_buttonExpandedRadius + 5), 330 + (i * 60), 60, true, paint);
                    }
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.rgb(colourPalette[j].r, colourPalette[j].g, colourPalette[j].b));
                    canvas.drawArc(rectF, 330 + (i * 60), 60, true, paint);
                    if(j == colourSelected)
                    {
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setColor(Color.WHITE);
                        canvas.drawArc(new RectF(paint_buttonX - paint_buttonExpandedRadius - 5, paint_buttonY - paint_buttonExpandedRadius - 5, paint_buttonX + paint_buttonExpandedRadius + 5, paint_buttonY + paint_buttonExpandedRadius + 5), 330 + (i * 60), 60, true, paint);
                    }
                }
            }
            canvas.drawBitmap(erase_button, erase_buttonX - erase_buttonRadius, erase_buttonY - erase_buttonRadius, paint);
            canvas.drawBitmap(flip_button, flip_buttonX - flip_buttonRadius, flip_buttonY - flip_buttonRadius, paint);
        }

        private void drawPolygon(Canvas canvas, Colour colour, Point[] points)
        {
            if (points.length < 2) {
                return;
            }
            Paint polyPaint = new Paint();
            polyPaint.setColor(Color.rgb(colour.r, colour.g, colour.b));
            polyPaint.setStyle(Paint.Style.FILL);
            Path polyPath = new Path();
            polyPath.moveTo((int)points[0].x, (int)points[0].y);
            for (int i = 0; i < points.length; i++) {
                polyPath.lineTo((int)points[i].x, (int)points[i].y);
            }
            polyPath.lineTo((int)points[0].x, (int)points[0].y);
            canvas.drawPath(polyPath, polyPaint);
        }

        public double distance(double x, double y, double x1, double y1)
        {
            return Math.sqrt(((x1-x)*(x1-x) + (y1-y)*(y1-y)));
        }

        public void nextStage()
        {
            stage++;
            if(stage >= 2)
            {
                CubeSolverActivity.cube = cube;
                Intent intent = new Intent(getContext(), CubeSolverActivity.class);
                startActivity(intent);
            }
        }

        public void paintStickers(double x, double y)
        {
            Point p = new Point(x, y);
            if (sideViewed == 0) {
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        if (quad[0][a][b].contains(p) && !(a == 1 && b == 1)) {
                            cube.cubie[0][a][b].sticker[0].setColour(colourSelected);
                        }
                    }
                }

                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        if (quad[1][a][b].contains(p) && !(a == 1 && b == 1)) {
                            if (b == 1) {
                                cube.cubie[b][a][2].sticker[0].setColour(colourSelected);
                            } else if ((b == 2 && a == 0) || (b == 0 && a == 2)) {
                                cube.cubie[b][a][2].sticker[2].setColour(colourSelected);
                            } else {
                                cube.cubie[b][a][2].sticker[1].setColour(colourSelected);
                            }
                        }
                    }
                }

                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        if (quad[2][a][b].contains(p) && !(a == 1 && b == 1)) {
                            if (b == 1 && 2 - a == 1) {
                                cube.cubie[b][2][2 - a].sticker[0].setColour(colourSelected);
                            } else if ((b == 0 && 2 - a == 0) || (b == 2 && 2 - a == 2)) {
                                cube.cubie[b][2][2 - a].sticker[2].setColour(colourSelected);
                            } else {
                                cube.cubie[b][2][2 - a].sticker[1].setColour(colourSelected);
                            }
                        }
                    }
                }
            } else if (sideViewed == 1) {
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        if (quad[0][b][a].contains(p) && !(a == 1 && b == 1)) {
                            cube.cubie[2][2 - a][2 - b].sticker[0].setColour(colourSelected);
                        }
                    }
                }

                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        if (quad[1][a][b].contains(p) && !(a == 1 && b == 1)) {
                            if (b == 1 && a == 1) {
                                cube.cubie[2 - b][0][2 - a].sticker[0].setColour(colourSelected);
                            } else if ((b == 2 && a == 0) || (b == 0 && a == 2)) {
                                cube.cubie[2 - b][0][2 - a].sticker[2].setColour(colourSelected);
                            } else {
                                cube.cubie[2 - b][0][2 - a].sticker[1].setColour(colourSelected);
                            }
                        }
                    }
                }

                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        if (quad[2][a][b].contains(p) && !(a == 1 && b == 1)) {
                            if (b == 1) {
                                cube.cubie[2 - b][a][0].sticker[0].setColour(colourSelected);
                            } else if ((b == 2 && a == 0) || (b == 0 && a == 2)) {
                                cube.cubie[2 - b][a][0].sticker[2].setColour(colourSelected);
                            } else {
                                cube.cubie[2 - b][a][0].sticker[1].setColour(colourSelected);
                            }
                        }
                    }
                }
            }
        }

        private void touch_start(double x, double y)
        {
            if(distance(x, y, paint_buttonX, paint_buttonY) <= paint_buttonUnexpandedRadius*1.3)
            {
                colourWheelOpen = true;
            }
            else if(distance(x, y, erase_buttonX, erase_buttonY) <= erase_buttonRadius*1.3) {
                colourSelected = 6;
            }
            else if(distance(x, y, flip_buttonX, flip_buttonY) <= flip_buttonRadius*1.3) {
                if (sideViewed == 0) {
                    sideViewed = 1;
                } else if (sideViewed == 1) {
                    sideViewed = 0;
                }
            }
            else if(x >= MainActivity.display.getWidth() - ok_button.getWidth() && x <= MainActivity.display.getWidth() && y >= MainActivity.display.getHeight() - ok_button.getHeight() && y <= MainActivity.display.getHeight())
            {
                nextStage();
            }

            paintStickers(x, y);
        }

        private void touch_move(double x, double y)
        {
            if(colourWheelOpen)
            {
                double angle = Math.toDegrees(Math.atan2(y - paint_buttonX, x - paint_buttonY));
                if(angle >= -30 && angle <= 30)
                {
                    sliverSelected = 0;
                    colourSelected = 0;
                }
                else if(angle > 30 && angle <= 90)
                {
                    sliverSelected = 1;
                    colourSelected = 1;
                }
                else if(angle > 90 && angle <= 150)
                {
                    sliverSelected = 2;
                    colourSelected = 2;
                }
                else if(angle > 150 || angle <= -150)
                {
                    sliverSelected = 3;
                    colourSelected = 3;
                }
                else if(angle > -150 && angle <= -90)
                {
                    sliverSelected = 4;
                    colourSelected = 5;
                }
                else if(angle > -90 && angle < -30)
                {
                    sliverSelected = 5;
                    colourSelected = 4;
                }
            }
            else {
                paintStickers(x, y);
            }
        }

        private void touch_up()
        {
            colourWheelOpen = false;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            double x = event.getX();
            double y = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }
    }
}
