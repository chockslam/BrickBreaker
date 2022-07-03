package com.example.brickbreaker;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;

public class Paddle extends MovableGameObject implements Collidable, Controllable {

    Vector oldPos;
    float oldX;
    float oldPaddleX;
    //float xTouch;

    public Paddle(Vector pos, Vector vel, Vector sceneDimensions, Vector size, ObjectManager om, Context context) {
        super(pos, vel, sceneDimensions, size, context);
        this.oldPos = new Vector(0,0);
        this.oldX = 0;
        this.oldPaddleX = 0;
        this.drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.paddle);
        this.drawable = getResizedBitmap(this.drawable, (int)size.getY(), (int)size.getX() );
    }

    @Override
    public void Update() {

    }



    @Override
    public void onCollision(SceneObject obj) {

    }

    @Override
    public boolean onTouch(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        if(touchY>=this.pos.getY()){
            int action = event.getAction();
            if(action == MotionEvent.ACTION_DOWN){
                oldX = (int) event.getX();
                oldPaddleX = this.pos.getX();
                this.oldPos.setX(this.pos.getX());
            }
            if(action == MotionEvent.ACTION_MOVE){
                float shift = oldX-touchX;
                float newPaddleX = oldPaddleX - shift;
                if(newPaddleX<=0){
                    this.pos.setX(0);
                }else if(newPaddleX>= this.sceneDimensions.getX() - this.drawable.getWidth()){
                    this.pos.setX(this.sceneDimensions.getX() - this.drawable.getWidth());
                }else{
                    this.pos.setX(newPaddleX);
                }
            }
            return true;

        }
        // Should I do it?
        return false;
    }
}
