package com.jlevy.cubesolver;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class CapturePreview extends Activity implements SurfaceHolder.Callback, Camera.ShutterCallback, Camera.PictureCallback {

    Camera mCamera;
    SurfaceView mPreview;
    int pictureNum;
    Bitmap bmp1;
    Bitmap bmp2;
    Solver solver;
    ImageView imageView;
    GuidelineMoverActivity gma;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_preview);
        imageView=(ImageView) findViewById(R.id.camera_guidelines);
        mPreview = (SurfaceView)findViewById(R.id.preview);
        mPreview.getHolder().addCallback(this);
        mPreview.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        imageView.bringToFront();
        mCamera = Camera.open();
        pictureNum = 0;

        Camera.Parameters params = mCamera.getParameters();
        params.setPictureSize(1280, 720);
        params.setPreviewSize(1280, 720);
        mCamera.setParameters(params);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCamera.stopPreview();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCamera.release();
    }

    public void onCancelClick(View v) {
        finish();
    }

    public void onSnapClick(View v) {
        mCamera.takePicture(this, null, null, this);
    }

    @Override
    public void onShutter() {
        Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Bitmap temp;
        temp = BitmapFactory.decodeByteArray(data, 0, data.length);

        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedBitmap = Bitmap.createBitmap(temp, 0, 0, temp.getWidth(), temp.getHeight(), matrix, true);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(rotatedBitmap, MainActivity.display.getWidth(), MainActivity.display.getHeight(), true);
        temp = scaledBitmap;

        if(pictureNum == 0)
        {
            bmp1 = temp;
            pictureNum++;
            camera.startPreview();
        }
        else
        {
            bmp2 = temp;
            pictureNum++;
            //solver = new Solver(bmp1, bmp2);
            GuidelineMoverActivity.bmp1 = bmp1;
            GuidelineMoverActivity.bmp2 = bmp2;
            Intent intent = new Intent(this, GuidelineMoverActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters params = mCamera.getParameters();
        List<Camera.Size> sizes = params.getSupportedPreviewSizes();
        Camera.Size selected = sizes.get(0);
        params.setPreviewSize(selected.width,selected.height);
        mCamera.setParameters(params);

        mCamera.setDisplayOrientation(90);
        mCamera.startPreview();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(mPreview.getHolder());
            Log.d("surfaceCreated", " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("PREVIEW", "surfaceDestroyed");
    }

}
