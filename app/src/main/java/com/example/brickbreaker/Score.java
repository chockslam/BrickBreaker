package com.example.brickbreaker;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Score extends UIElement{
    int points;

    public Score(Vector pos, int color) {
        super(pos, color);
        this.points = 0;
        this.paint.setTextAlign(Paint.Align.LEFT);
        this.paint.setTextSize(this.pos.getY());
        this.paint.setColor(color);
    }

    public void Increment(){
        this.points++;
    }


    @Override
    public void Update() {

    }

    @Override
    public void Draw(Canvas canvas) {
        if(this.active){
            canvas.drawText(""+points, pos.getX(),pos.getY(),paint);
        }
    }
}
