package com.example.brickbreaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.brickbreaker.UIElement;
import com.example.brickbreaker.Vector;

public class HealthBar extends UIElement {

    int life;
    Rect rect;
    Context context;
    int initLife;

    public HealthBar(Vector pos, int color, int life, Rect rect, Vector sceneDimensions, Context context) {
        super(pos, color);
        this.life = life;
        this.initLife = life;
        this.rect = rect;
        this.context = context;
        this.paint.setColor(color);
        this.sceneDimensions = sceneDimensions;
        if(this.rect.right>=sceneDimensions.getX()){
            this.rect.left -= (this.rect.right - sceneDimensions.getX()+5) ;
            this.rect.right = (int)sceneDimensions.getX()-5;
        }
    }

    @Override
    public void Update() {
        if(this.active){
            this.rect.right = this.rect.left + 60*life;
            if((float)this.life/(float)this.initLife<=0.7){
                this.paint.setColor(Color.YELLOW);
            }
            if((float)this.life/(float)this.initLife<=0.3) {
                this.paint.setColor(Color.RED);
            }
            if (life <= 0){
                this.active = false;
                Intent intent = new Intent(context,GameOver.class);
                //intent.putExtra("points", points);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        }

    }

    @Override
    public void Draw(Canvas canvas) {
        if(this.active){
            canvas.drawRect(this.rect, this.paint);
        }
    }

    public void Decrement(){
        this.life--;
    }
}
