package com.example.brickbreaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;

import com.example.brickbreaker.UIElement;
import com.example.brickbreaker.Vector;

// Class represents health bar in the top right corner.
public class HealthBar extends UIElement {

    private int life;
    // Health bar - rect;
    private Rect rect;
    private Context context;
    private int initLife;
    private ObjectManager om;
    // Sounds.
    private MediaPlayer gameOver, hpLose;

    public HealthBar(Vector pos, int color, int life, Rect rect, Vector sceneDimensions, Context context, ObjectManager om) {
        super(pos, color);
        this.om = om;
        this.life = life;
        this.initLife = life;
        this.rect = rect;
        this.context = context;
        this.paint.setColor(color);
        this.sceneDimensions = sceneDimensions;
        // Do not let health bar to go beyond screen width.
        if(this.rect.right>=sceneDimensions.getX()){
            this.rect.left -= (this.rect.right - sceneDimensions.getX()+5) ;
            this.rect.right = (int)sceneDimensions.getX()-5;
        }
        this.gameOver = MediaPlayer.create(context, R.raw.gameover);
        this.hpLose = MediaPlayer.create(context, R.raw.hplose);
    }

//    Update Handles tracks game-over state.
    @Override
    public void Update() {
        if(this.active){
            if (life <= 0){
                if(this.gameOver!=null){
                    this.gameOver.start();
                }
                this.active = false;
                this.om.deleteAll();
                Intent intent = new Intent(context,GameOver.class);
                //intent.putExtra("points", om.getScore().getPoints());
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        }

    }

//    Draw rectangle.
    @Override
    public void Draw(Canvas canvas) {
        if(this.active){
            canvas.drawRect(this.rect, this.paint);
        }
    }

//    Decrement life and update state of the bar.
    public void Decrement(){
        this.life--;
        if(hpLose!=null && life>0) {
            hpLose.start();
        }
        this.rect.right = this.rect.left + 60*life;
        if((float)this.life/(float)this.initLife<=0.7){
            this.paint.setColor(Color.YELLOW);
        }
        if((float)this.life/(float)this.initLife<=0.35) {
            this.paint.setColor(Color.RED);
        }
    }
}
