package com.example.brickbreaker;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Brick extends DrawableGameObject implements Collidable{
    private Random randomizer;
    private int life;
    private ObjectManager om;
    private int type;
    public Brick(Vector pos, Vector sceneDimensions, Vector size, Context context, ObjectManager om) {
        super(pos, sceneDimensions, size, context);
        this.om = om;
        this.life = 2;
        this.randomizer = new Random();
        this.type = randomizer.nextInt(9);
        this.drawable = BitmapFactory.decodeResource(context.getResources(), RandomBrickType(this.type, this.life));
        this.drawable = getResizedBitmap(this.drawable, (int)size.getY(), (int)size.getX() );
    }

    private int RandomBrickType(int type, int life){
        if(life == 2){

            switch (type){
                case 0:
                    return R.drawable.tile_1;
                case 1:
                    return R.drawable.tile_2;
                case 2:
                    return R.drawable.tile_3;
                case 3:
                    return R.drawable.tile_4;
                case 4:
                    return R.drawable.tile_5;
                case 5:
                    return R.drawable.tile_6;
                case 6:
                    return R.drawable.tile_7;
                case 7:
                    return R.drawable.tile_8;
                case 8:
                    return R.drawable.tile_9;
                default:
                    return R.drawable.tile_1;

            }

        }
        else{
            switch (type){
                case 0:
                    return R.drawable.tile_1_broken;
                case 1:
                    return R.drawable.tile_2_broken;
                case 2:
                    return R.drawable.tile_3_broken;
                case 3:
                    return R.drawable.tile_4_broken;
                case 4:
                    return R.drawable.tile_5_broken;
                case 5:
                    return R.drawable.tile_6_broken;
                case 6:
                    return R.drawable.tile_7_broken;
                case 7:
                    return R.drawable.tile_8_broken;
                case 8:
                    return R.drawable.tile_9_broken;
                default:
                    return R.drawable.tile_1_broken;

            }
        }
    }


    @Override
    public void Update() {
        if(this.active){
            if(this.life<=0){
                this.Deactivate();
                this.om.getLevelManager().decrementBricks();
            }
        }


    }


    @Override
    public void onCollision(SceneObject obj) {
        
    }

    public void Hit(){
        life--;

        if(life == 1){
            this.om.getScore().Increment();
        }

        if(life==0){
            this.om.getScore().Increment();
            this.om.getScore().Increment();
        }

        if(life>0){
            this.drawable = BitmapFactory.decodeResource(context.getResources(), RandomBrickType(this.type, this.life));
            this.drawable = getResizedBitmap(this.drawable, (int)this.size.getY(), (int)this.size.getX() );
        }
    }


}
