package com.example.brickbreaker;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Brick extends DrawableGameObject implements Collidable{
    private Random randomizer;
    private int life;
    private ObjectManager om;
    public Brick(Vector pos, Vector sceneDimensions, Vector size, Context context, ObjectManager om) {
        super(pos, sceneDimensions, size, context);
        this.om = om;
        randomizer = new Random();
        int randomNumber = randomizer.nextInt(9);
        this.drawable = BitmapFactory.decodeResource(context.getResources(), RandomBrickType(randomNumber));
        this.drawable = getResizedBitmap(this.drawable, (int)size.getY(), (int)size.getX() );
        this.life = 1;
    }

    private int RandomBrickType(int type){

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
    }


}
