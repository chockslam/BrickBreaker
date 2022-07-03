package com.example.brickbreaker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.content.Context;

import androidx.core.graphics.drawable.IconCompat;

public abstract class DrawableGameObject extends SceneObject {

    protected Bitmap drawable;
    protected Context context;
    protected Vector size;


    public DrawableGameObject(Vector pos, Vector sceneDimensions, Vector size, Context context) {
        super(pos, sceneDimensions);
        this.context = context;
        this.size = size;
    }
    @Override

    public void Draw(Canvas canvas){
        if(this.active){
            canvas.drawBitmap(drawable, pos.getX(), pos.getY(), null);
        }
    }

    public Bitmap getDrawable() {
        return drawable;
    }

    //    https://stackoverflow.com/questions/34491569/how-to-change-the-size-of-bitmap
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;

    }
}
