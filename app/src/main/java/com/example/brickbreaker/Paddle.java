package com.example.brickbreaker;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.MotionEvent;

// Paddle which is controlled by touch
// Probably should have been derived from DrawableGameObject instead of MovableGameObject.
public class Paddle extends MovableGameObject implements Collidable, Controllable {

    // Track old X-position for touchEvent.
    float oldX;
    float oldPaddleX;

    // Bounce sound.
    MediaPlayer bounce;

    public Paddle(Vector pos, Vector vel, Vector sceneDimensions, Vector size, ObjectManager om, Context context) {
        super(pos, vel, sceneDimensions, size, context);
        this.oldX = 0;
        this.oldPaddleX = 0;

        this.drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.paddle);
        this.drawable = getResizedBitmap(this.drawable, (int)size.getY(), (int)size.getX() );

        this.bounce = MediaPlayer.create(context, R.raw.bounce);
    }

    @Override
    public void Update() {

    }



    @Override
    public void onCollision(SceneObject obj) {

    }

//    Handle touch event
    @Override
    public boolean onTouch(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        // only if touch is below paddle.
        if(touchY>=this.pos.getY()){
            int action = event.getAction();
            // if touched - register.
            if(action == MotionEvent.ACTION_DOWN){
                oldX = (int) event.getX();
                oldPaddleX = this.pos.getX();
            }
            // if dragged handle new position.
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
        return false;
    }

//    Play sound effect.
    public void playBounce(){
        if(this.bounce != null){
            this.bounce.start();
        }
    }
}
