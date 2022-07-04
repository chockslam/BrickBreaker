package com.example.brickbreaker;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

// Abstract Class represents any HUD/UI element in the scene.
public abstract class UIElement extends SceneObject {

    protected Paint paint;

    public UIElement(Vector pos, int color) {
        super(pos, null);
        this.paint = new Paint();
        this.paint.setColor(color);
    }

}
