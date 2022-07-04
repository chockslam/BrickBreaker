package com.example.brickbreaker;

import android.graphics.Canvas;
import android.graphics.Paint;

// Class represents Player's score.
public class Score extends UIElement{
    private int points;


    public Score(Vector pos, int color) {
        super(pos, color);

        this.points = 0;

        // Text located in the top-left corner.
        this.paint.setTextAlign(Paint.Align.LEFT);
        this.paint.setTextSize(this.pos.getY());

        this.paint.setColor(color);
    }

    public void Increment(){
        this.points++;
    }


    // Update is empty.
    @Override
    public void Update() {

    }

    // Draw text.
    @Override
    public void Draw(Canvas canvas) {
        if(this.active){
            canvas.drawText(""+points, pos.getX(),pos.getY(),paint);
        }
    }

    public int getPoints(){
        return this.points;
    }
}
