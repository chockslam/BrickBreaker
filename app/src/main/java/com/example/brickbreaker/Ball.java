package com.example.brickbreaker;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Ball extends MovableGameObject implements Collidable{

    Random randomizer;
    ObjectManager om;
    float initY;
    boolean collided;

    public Ball(Vector pos, Vector vel, Vector sceneDimensions, Vector size, ObjectManager om, Context context) {
        super(pos, vel, sceneDimensions, size, context);
        this.initY = this.pos.getY();
        this.drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
        this.drawable = getResizedBitmap(this.drawable, (int)size.getY(), (int)size.getX() );
        randomizer = new Random();
        this.om = om;
        this.collided = false;
    }

    @Override
    public void Update() {
        if(this.active){
            this.collided = false;
            this.pos.setX(this.pos.getX()+ this.vel.getX());
            this.pos.setY(this.pos.getY()+ this.vel.getY());

            if(this.pos.getX()>= sceneDimensions.getX()-this.drawable.getWidth()||this.pos.getX() <= 0){
                vel.setX(vel.getX()*(-1));
            }
            if(this.pos.getY()<=0){
                vel.setY(vel.getY()*(-1));
            }

        }
    }


    @Override
    public void onCollision(SceneObject obj) {

        float speedFactor = (float)this.om.getLevelManager().getLevel()*this.om.getLevelManager().getLevel()/10;
        if(!collided){

            if (obj instanceof Paddle){
                // if ball goes underneath the paddle.
                if(this.pos.getY()> obj.getPos().getY() + ((Paddle) obj).getDrawable().getHeight()){
                        this.pos.setX(1+randomizer.nextInt((int)(sceneDimensions.getX()-this.drawable.getWidth() - this.drawable.getWidth())));
                        this.pos.setY(initY);
                        this.om.getHealthBar().Decrement();
                }
                else
                // collision with the top of the paddle.
                if(     (this.pos.getX()+this.drawable.getWidth()) > obj.getPos().getX()
                        && this.pos.getX() <= obj.getPos().getX() + ((DrawableGameObject) obj).getDrawable().getWidth()
                        && this.pos.getY() + this.drawable.getHeight() <= obj.getPos().getY() + ((DrawableGameObject) obj).getDrawable().getHeight()
                        && this.pos.getY() + this.drawable.getHeight()>=obj.getPos().getY()){

                        this.vel.setX((this.vel.getX() + speedFactor));
                        this.vel.setY((this.vel.getY() + speedFactor)*-1);
                }
            }
            if (obj instanceof Brick){
                // Top of the brick
                if (
                        this.pos.getX() + this.drawable.getWidth() > obj.getPos().getX()
                        && this.pos.getX() <= obj.getPos().getX() + ((DrawableGameObject) obj).getDrawable().getWidth()
                        && this.pos.getY() + this.drawable.getHeight() <= obj.getPos().getY() + ((DrawableGameObject) obj).getDrawable().getHeight()
                        && this.pos.getY() + this.drawable.getHeight() >= obj.getPos().getY()
                    )
                {
                    collided = true;
                    speedFactor = -speedFactor;
                    this.vel.setY(-this.vel.getY() + speedFactor);
                }
                // Bottom of the brick
                if  (
                        this.pos.getX() + this.drawable.getWidth() > obj.getPos().getX()
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
                        this.pos.getY() + this.drawable.getHeight() > obj.getPos().getY()
                        && this.pos.getY() <= obj.getPos().getY() + ((DrawableGameObject) obj).getDrawable().getHeight()
                        &&(this.pos.getX() + this.drawable.getWidth()) >= obj.getPos().getX()
                        && this.pos.getX() + this.drawable.getWidth() <= obj.getPos().getX()  + ((DrawableGameObject) obj).getDrawable().getWidth()

                    )
                    {
                        collided = true;
                        speedFactor = -speedFactor;
                        this.vel.setX(-this.vel.getX() + speedFactor);
                    }
                // Right of the brick
                if  (
                        this.pos.getY() + this.drawable.getHeight() > obj.getPos().getY()
                        && this.pos.getY() <= obj.getPos().getY() + ((DrawableGameObject) obj).getDrawable().getHeight()
                        && this.pos.getX() <= obj.getPos().getX() + ((DrawableGameObject) obj).getDrawable().getWidth() + this.drawable.getWidth()
                        && this.pos.getX() >= obj.getPos().getX()
                    )
                    {
                        collided = true;
                        this.vel.setX(-this.vel.getX() + speedFactor);
                    }

                if(collided){
                    ((Brick) obj).Hit();
                    this.om.getScore().Increment();
                }

            }
        }
    }
}
