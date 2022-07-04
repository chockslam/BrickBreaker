package com.example.brickbreaker;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.Random;


// Class represents Ball in the scene.
public class Ball extends MovableGameObject implements Collidable{

    private Random randomizer;
    private ObjectManager om;
    private float initY;
    private boolean collided;   // Flag tracks whether the ball already collided in the current frame.

    public Ball(Vector pos, Vector vel, Vector sceneDimensions, Vector size, ObjectManager om, Context context) {
        super(pos, vel, sceneDimensions, size, context);
        this.initY = this.pos.getY();
        this.drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
        this.drawable = getResizedBitmap(this.drawable, (int)size.getY(), (int)size.getX() );
        this.randomizer = new Random();
        this.om = om;
        this.collided = false;
    }

//    Update position every frame according to the velocity of the ball.
    @Override
    public void Update() {
        if(this.active){
            this.collided = false;
            this.pos.setX(this.pos.getX()+ this.vel.getX());
            this.pos.setY(this.pos.getY()+ this.vel.getY());

            if(this.pos.getX()>= sceneDimensions.getX()-this.drawable.getWidth()||this.pos.getX() <= 0){
                this.vel.setX(vel.getX()*(-1));
            }
            if(this.pos.getY()<=0){
                this.vel.setY(vel.getY()*(-1));
            }

        }
    }

//    All collision in the scene is handled here.
    @Override
    public void onCollision(SceneObject obj) {

        float speedFactor = (float)this.om.getLevelManager().getLevel()*this.om.getLevelManager().getLevel()/10;
        if(!collided){

            if (obj instanceof Paddle){
                // if ball goes underneath the paddle.
                if(this.pos.getY() > obj.getPos().getY() + ((Paddle) obj).getDrawable().getHeight()){
                        this.pos.setX(1+randomizer.nextInt((int)(sceneDimensions.getX()-this.drawable.getWidth() - this.drawable.getWidth())));
                        this.pos.setY(initY);
                        this.om.getHealthBar().Decrement();
                }
                else
                // collision with the top of the paddle.
                if(     !collided
                        &&(this.pos.getX()+this.drawable.getWidth()) > obj.getPos().getX()
                        && this.pos.getX() <= obj.getPos().getX() + ((DrawableGameObject) obj).getDrawable().getWidth()
                        && this.pos.getY() + this.drawable.getHeight() <= obj.getPos().getY() + ((DrawableGameObject) obj).getDrawable().getHeight()
                        && this.pos.getY() + this.drawable.getHeight()>=obj.getPos().getY()){
                        ((Paddle) obj).playBounce();
                        this.vel.setY(-this.vel.getY());
                }
            }
            if (obj instanceof Brick){
                // Top of the brick
                if (
                        !collided
                        &&this.pos.getX() + this.drawable.getWidth() > obj.getPos().getX()
                        && this.pos.getX() <= obj.getPos().getX() + ((DrawableGameObject) obj).getDrawable().getWidth()
                        && this.pos.getY() + this.drawable.getHeight() <= obj.getPos().getY() + ((DrawableGameObject) obj).getDrawable().getHeight()
                        && this.pos.getY() + this.drawable.getHeight() >= obj.getPos().getY()
                    )
                {
                    collided = true;
                    speedFactor = -speedFactor;
                    this.vel.setY(-this.vel.getY() - speedFactor);
                }
                // Bottom of the brick
                if  (
                        !collided
                        &&this.pos.getX() + this.drawable.getWidth() > obj.getPos().getX()
                        && this.pos.getX() <= obj.getPos().getX() + ((DrawableGameObject) obj).getDrawable().getWidth()
                        && this.pos.getY() <= obj.getPos().getY() + ((DrawableGameObject) obj).getDrawable().getHeight()
                        && this.pos.getY() >= obj.getPos().getY()
                    )
                    {
                        collided = true;
                        this.vel.setY(-this.vel.getY() + speedFactor);
                    }
                // Left of the brick
                if  (
                        !collided
                        &&this.pos.getY() + this.drawable.getHeight() > obj.getPos().getY()
                        && this.pos.getY() <= obj.getPos().getY() + ((DrawableGameObject) obj).getDrawable().getHeight()
                        &&(this.pos.getX() + this.drawable.getWidth()) >= obj.getPos().getX()
                        && this.pos.getX() + this.drawable.getWidth() <= obj.getPos().getX()  + ((DrawableGameObject) obj).getDrawable().getWidth()

                    )
                    {
                        collided = true;
                        speedFactor = -speedFactor;
                        this.vel.setX(-this.vel.getX() - speedFactor);
                    }
                // Right of the brick
                if  (
                        !collided
                        &&this.pos.getY() + this.drawable.getHeight() > obj.getPos().getY()
                        && this.pos.getY() <= obj.getPos().getY() + ((DrawableGameObject) obj).getDrawable().getHeight()
                        && this.pos.getX() <= obj.getPos().getX() + ((DrawableGameObject) obj).getDrawable().getWidth() + this.drawable.getWidth()
                        && this.pos.getX() >= obj.getPos().getX()
                    )
                    {
                        collided = true;
                        this.vel.setX(-this.vel.getX() + speedFactor);
                    }

//               If collision happened handle brick state.
                if(collided){
                    ((Brick) obj).Hit();
                }

            }
        }
    }
}
