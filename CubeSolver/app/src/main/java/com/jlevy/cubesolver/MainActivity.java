package com.jlevy.cubesolver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class MainActivity extends AppCompatActivity  {

    public static Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = getWindowManager().getDefaultDisplay();
    }

    public void onPicButtonClick(View view)
    {
        Intent intent = new Intent(this, CapturePreview.class);
        startActivity(intent);
    }

    public void onDrawButtonClick(View view)
    {
        Intent intent = new Intent(this, CubePainterActivity.class);
        startActivity(intent);
    }

    public void onInstructionsButtonClick(View view)
    {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }

    public void onExitButtonClick(View view)
    {

    }

}
