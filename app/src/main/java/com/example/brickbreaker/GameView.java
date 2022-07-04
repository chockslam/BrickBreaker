package com.example.brickbreaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.Random;

// Application / GameView class.
// Handles engine's architecture.
public class GameView extends View {

    // Android specific.
    Context context;
    Handler handler;
    final long UPD_MIL = 30;
    Runnable runnable;
    // Object Manager. Game Engine Utility.
    ObjectManager om;




    public GameView(Context context) {
        super(context);
        // Android Specific.
        this.context = context;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // initialize ObjectManager and add LevelManager to it.
        this.om = new ObjectManager();
        this.om.AddLevelManager(new LevelManager(this.om, context, size));



    }
//    Called every frame.
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);

        // Handle objects in Object Manager class.
        this.om.checkCollisions();
        this.om.UpdateAll();
        this.om.RenderAll(canvas);
        this.om.deleteInactive();

        handler.postDelayed(runnable,UPD_MIL);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle TouchEvents using ObjectManager.
        if(this.om.processTouchEvent(event)){
            return true;
        }

        return super.onTouchEvent(event);
    }






}
