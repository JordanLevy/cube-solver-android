package com.jlevy.cubesolver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class GuidelineMoverActivity extends Activity {

    GuidelineView gv;
    Point[] cornerPoints1 = {new Point(100, 30), new Point(15, 60), new Point(100, 95), new Point(185, 60), new Point(15, 110), new Point(100, 150), new Point(185, 110)};
    Point[][] edgePoints1 = new Point[9][2];
    Point[] cornerPoints2 = {new Point(100, 30), new Point(15, 60), new Point(100, 95), new Point(185, 60), new Point(15, 110), new Point(100, 150), new Point(185, 110)};
    Point[][] edgePoints2 = new Point[9][2];
    double rad = 30;
    int cornerGrabbed = -1;
    int[] edgeGrabbed = {-1, -1};
    public static Bitmap bmp1;
    public static Bitmap bmp2;
    Bitmap ok_button;
    int stage = 1;
    int[] firstCorners = {0, 1, 3, 0, 1, 2, 3, 4, 6};
    int[] secondCorners = {1, 2, 2, 3, 4, 5, 6, 5, 5};
    int[] firstEdges =  {0, 1, 1, 4, 2, 5};
    int[] secondEdges = {2, 3, 7, 5, 8, 6};
    ColorDecoder colorDecoder;

    public GuidelineMoverActivity()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gv = new GuidelineView(this);
        setContentView(gv);
        setCornerPoints();
    }

    public void setCornerPoints()
    {
        for(int i = 0; i < cornerPoints1.length; i++)
        {
            cornerPoints1[i].x = cornerPoints1[i].x / 200 * MainActivity.display.getWidth();
            cornerPoints1[i].y = cornerPoints1[i].y / 200 * MainActivity.display.getHeight();
            cornerPoints2[i].x = cornerPoints2[i].x / 200 * MainActivity.display.getWidth();
            cornerPoints2[i].y = cornerPoints2[i].y / 200 * MainActivity.display.getHeight();
        }
    }

    public class GuidelineView extends View {

        private Paint paint;
        Context context;

        public GuidelineView(Context c) {
            super(c);
            context=c;
            paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if(stage == 1) {
                canvas.drawBitmap(bmp1, 0, 0, paint);
                paint.setColor(Color.WHITE);
                for (int i = 0; i < cornerPoints1.length; i++) {
                    canvas.drawCircle((float) cornerPoints1[i].x, (float) cornerPoints1[i].y, (float) rad, paint);
                }

                for(int i = 0; i < firstCorners.length; i++) {
                    canvas.drawLine((float) cornerPoints1[firstCorners[i]].x, (float) cornerPoints1[firstCorners[i]].y, (float) cornerPoints1[secondCorners[i]].x, (float) cornerPoints1[secondCorners[i]].y, paint);
                }
            }
            else if (stage == 2) {
                canvas.drawBitmap(bmp1, 0, 0, paint);
                paint.setColor(Color.GREEN);
                for(int i = 0; i < firstCorners.length; i++) {
                    canvas.drawLine((float) cornerPoints1[firstCorners[i]].x, (float) cornerPoints1[firstCorners[i]].y, (float) cornerPoints1[secondCorners[i]].x, (float) cornerPoints1[secondCorners[i]].y, paint);
                }

                paint.setColor(Color.WHITE);
                for(int i = 0; i < edgePoints1.length; i++) {
                    for(int j = 0; j < edgePoints1[i].length; j++)
                    {
                        canvas.drawCircle((float) edgePoints1[i][j].x, (float) edgePoints1[i][j].y, (float) rad, paint);
                    }
                }

                for(int i = 0; i < firstEdges.length; i++)
                {
                    canvas.drawLine((float) edgePoints1[firstEdges[i]][0].x, (float) edgePoints1[firstEdges[i]][0].y, (float) edgePoints1[secondEdges[i]][0].x, (float) edgePoints1[secondEdges[i]][0].y, paint);
                    canvas.drawLine((float) edgePoints1[firstEdges[i]][1].x, (float) edgePoints1[firstEdges[i]][1].y, (float) edgePoints1[secondEdges[i]][1].x, (float) edgePoints1[secondEdges[i]][1].y, paint);
                }

            }
            else if(stage == 3) {
                canvas.drawBitmap(bmp2, 0, 0, paint);
                paint.setColor(Color.WHITE);
                for (int i = 0; i < cornerPoints2.length; i++) {
                    canvas.drawCircle((float) cornerPoints2[i].x, (float) cornerPoints2[i].y, (float) rad, paint);
                }

                for(int i = 0; i < firstCorners.length; i++) {
                    canvas.drawLine((float) cornerPoints2[firstCorners[i]].x, (float) cornerPoints2[firstCorners[i]].y, (float) cornerPoints2[secondCorners[i]].x, (float) cornerPoints2[secondCorners[i]].y, paint);
                }
            }
            else if (stage == 4) {
                canvas.drawBitmap(bmp2, 0, 0, paint);
                paint.setColor(Color.GREEN);
                for(int i = 0; i < firstCorners.length; i++) {
                    canvas.drawLine((float) cornerPoints2[firstCorners[i]].x, (float) cornerPoints2[firstCorners[i]].y, (float) cornerPoints2[secondCorners[i]].x, (float) cornerPoints2[secondCorners[i]].y, paint);
                }

                paint.setColor(Color.WHITE);
                for(int i = 0; i < edgePoints2.length; i++) {
                    for(int j = 0; j < edgePoints2[i].length; j++)
                    {
                        canvas.drawCircle((float) edgePoints2[i][j].x, (float) edgePoints2[i][j].y, (float) rad, paint);
                    }
                }

                for(int i = 0; i < firstEdges.length; i++)
                {
                    canvas.drawLine((float) edgePoints2[firstEdges[i]][0].x, (float) edgePoints2[firstEdges[i]][0].y, (float) edgePoints2[secondEdges[i]][0].x, (float) edgePoints2[secondEdges[i]][0].y, paint);
                    canvas.drawLine((float) edgePoints2[firstEdges[i]][1].x, (float) edgePoints2[firstEdges[i]][1].y, (float) edgePoints2[secondEdges[i]][1].x, (float) edgePoints2[secondEdges[i]][1].y, paint);
                }

            }
            ok_button = BitmapFactory.decodeResource(getResources(), R.drawable.ok_button);
            canvas.drawBitmap(ok_button, MainActivity.display.getWidth() - ok_button.getWidth(), MainActivity.display.getHeight() - ok_button.getHeight(), paint);

        }

        public double distance(double x, double y, double x1, double y1)
        {
            return Math.sqrt(((x1-x)*(x1-x) + (y1-y)*(y1-y)));
        }

        public void setEdgePoints()
        {
            if(stage == 2) {
                for (int i = 0; i < firstCorners.length; i++) {
                    double dx = (cornerPoints1[secondCorners[i]].x - cornerPoints1[firstCorners[i]].x) / 3.0;
                    double dy = (cornerPoints1[secondCorners[i]].y - cornerPoints1[firstCorners[i]].y) / 3.0;
                    edgePoints1[i][0] = new Point(cornerPoints1[firstCorners[i]].x + dx, cornerPoints1[firstCorners[i]].y + dy);
                    edgePoints1[i][1] = new Point(cornerPoints1[secondCorners[i]].x - dx, cornerPoints1[secondCorners[i]].y - dy);
                }
            }
            else if(stage == 4)
            {
                for (int i = 0; i < firstCorners.length; i++) {
                    double dx = (cornerPoints2[secondCorners[i]].x - cornerPoints2[firstCorners[i]].x) / 3.0;
                    double dy = (cornerPoints2[secondCorners[i]].y - cornerPoints2[firstCorners[i]].y) / 3.0;
                    edgePoints2[i][0] = new Point(cornerPoints2[firstCorners[i]].x + dx, cornerPoints2[firstCorners[i]].y + dy);
                    edgePoints2[i][1] = new Point(cornerPoints2[secondCorners[i]].x - dx, cornerPoints2[secondCorners[i]].y - dy);
                }
            }
            invalidate();
        }

        public void nextStage()
        {
            stage++;
            if(stage == 2 || stage == 4)
            {
                setEdgePoints();
            }
            else if(stage >= 5)
            {
                CubePainterActivity.bmp1 = bmp1;
                CubePainterActivity.bmp2 = bmp2;
                CubePainterActivity.cornerPoints1 = cornerPoints1;
                CubePainterActivity.cornerPoints2 = cornerPoints2;
                CubePainterActivity.edgePoints1 = edgePoints1;
                CubePainterActivity.edgePoints2 = edgePoints2;
                Intent intent = new Intent(this.getContext(), CubePainterActivity.class);
                startActivity(intent);
            }
        }

        private void touch_start(double x, double y) {
            if(stage == 1) {
                for (int i = 0; i < cornerPoints1.length; i++) {
                    if (distance(x, y, cornerPoints1[i].x, cornerPoints1[i].y) <= rad) {
                        cornerGrabbed = i;
                        break;
                    }
                }
                if(cornerGrabbed == -1)
                {
                    if(x >= MainActivity.display.getWidth() - ok_button.getWidth() && x <= MainActivity.display.getWidth() && y >= MainActivity.display.getHeight() - ok_button.getHeight() && y <= MainActivity.display.getHeight())
                    {
                        nextStage();
                    }
                }
            }
            else if(stage == 2)
            {
                for(int i = 0; i < edgePoints1.length; i++)
                {
                    for(int j = 0; j < edgePoints1[i].length; j++)
                    {
                        if(distance(x, y, edgePoints1[i][j].x, edgePoints1[i][j].y) <= rad)
                        {
                            edgeGrabbed[0] = i;
                            edgeGrabbed[1] = j;
                            break;
                        }
                    }
                    if(edgeGrabbed[0] != -1 && edgeGrabbed[1] != -1)
                    {
                        break;
                    }
                }
                if(edgeGrabbed[0] == -1 && edgeGrabbed[1] == -1)
                {
                    if(x >= MainActivity.display.getWidth() - ok_button.getWidth() && x <= MainActivity.display.getWidth() && y >= MainActivity.display.getHeight() - ok_button.getHeight() && y <= MainActivity.display.getHeight())
                    {
                        nextStage();
                    }
                }
            }
            else if(stage == 3) {
                for (int i = 0; i < cornerPoints2.length; i++) {
                    if (distance(x, y, cornerPoints2[i].x, cornerPoints2[i].y) <= rad) {
                        cornerGrabbed = i;
                        break;
                    }
                }
                if(cornerGrabbed == -1)
                {
                    if(x >= MainActivity.display.getWidth() - ok_button.getWidth() && x <= MainActivity.display.getWidth() && y >= MainActivity.display.getHeight() - ok_button.getHeight() && y <= MainActivity.display.getHeight())
                    {
                        nextStage();
                    }
                }
            }
            else if(stage == 4)
            {
                for(int i = 0; i < edgePoints2.length; i++)
                {
                    for(int j = 0; j < edgePoints2[i].length; j++)
                    {
                        if(distance(x, y, edgePoints2[i][j].x, edgePoints2[i][j].y) <= rad)
                        {
                            edgeGrabbed[0] = i;
                            edgeGrabbed[1] = j;
                            break;
                        }
                    }
                    if(edgeGrabbed[0] != -1 && edgeGrabbed[1] != -1)
                    {
                        break;
                    }
                }
                if(edgeGrabbed[0] == -1 && edgeGrabbed[1] == -1)
                {
                    if(x >= MainActivity.display.getWidth() - ok_button.getWidth() && x <= MainActivity.display.getWidth() && y >= MainActivity.display.getHeight() - ok_button.getHeight() && y <= MainActivity.display.getHeight())
                    {
                        nextStage();
                    }
                }
            }
        }

        private void touch_move(double x, double y) {
            if(stage == 1) {
                if (cornerGrabbed != -1) {
                    cornerPoints1[cornerGrabbed].x = x;
                    cornerPoints1[cornerGrabbed].y = y;
                }
            }
            else if(stage == 2)
            {
                if(edgeGrabbed[0] != -1 && edgeGrabbed[1] != -1)
                {
                    double rise = (cornerPoints1[secondCorners[edgeGrabbed[0]]].y) - (cornerPoints1[firstCorners[edgeGrabbed[0]]].y);
                    double run = (cornerPoints1[secondCorners[edgeGrabbed[0]]].x) - (cornerPoints1[firstCorners[edgeGrabbed[0]]].x);
                    double slope = rise / run;
                    double yIntercept = cornerPoints1[firstCorners[edgeGrabbed[0]]].y - (slope * cornerPoints1[firstCorners[edgeGrabbed[0]]].x);
                    double xDist = Math.abs(x - ((y - yIntercept)/slope));
                    double yDist = Math.abs(y - (slope * x + yIntercept));
                    if(xDist > yDist) {
                        edgePoints1[edgeGrabbed[0]][edgeGrabbed[1]].x = x;
                        edgePoints1[edgeGrabbed[0]][edgeGrabbed[1]].y = slope * x + yIntercept;
                    }else {
                        edgePoints1[edgeGrabbed[0]][edgeGrabbed[1]].x = (y - yIntercept)/slope;
                        edgePoints1[edgeGrabbed[0]][edgeGrabbed[1]].y = y;
                    }
                }
            }
            else if(stage == 3) {
                if (cornerGrabbed != -1) {
                    cornerPoints2[cornerGrabbed].x = x;
                    cornerPoints2[cornerGrabbed].y = y;
                }
            }
            else if(stage == 4)
            {
                if(edgeGrabbed[0] != -1 && edgeGrabbed[1] != -1)
                {
                    double rise = (cornerPoints2[secondCorners[edgeGrabbed[0]]].y) - (cornerPoints2[firstCorners[edgeGrabbed[0]]].y);
                    double run = (cornerPoints2[secondCorners[edgeGrabbed[0]]].x) - (cornerPoints2[firstCorners[edgeGrabbed[0]]].x);
                    double slope = rise / run;
                    double yIntercept = cornerPoints2[firstCorners[edgeGrabbed[0]]].y - (slope * cornerPoints2[firstCorners[edgeGrabbed[0]]].x);
                    double xDist = Math.abs(x - ((y - yIntercept)/slope));
                    double yDist = Math.abs(y - (slope * x + yIntercept));
                    if(xDist > yDist) {
                        edgePoints2[edgeGrabbed[0]][edgeGrabbed[1]].x = x;
                        edgePoints2[edgeGrabbed[0]][edgeGrabbed[1]].y = slope * x + yIntercept;
                    }else {
                        edgePoints2[edgeGrabbed[0]][edgeGrabbed[1]].x = (y - yIntercept)/slope;
                        edgePoints2[edgeGrabbed[0]][edgeGrabbed[1]].y = y;
                    }
                }
            }
        }

        private void touch_up() {
            if(stage == 1 || stage == 3) {
                cornerGrabbed = -1;
            }else if(stage == 2 || stage == 4) {
                edgeGrabbed[0] = -1;
                edgeGrabbed[1] = -1;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
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
