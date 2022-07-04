package com.example.brickbreaker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;
// Level Manager.
// It tracks state of the game and handles level transitions.
public class LevelManager {

    int level;
    int numBricks;

    Context context;
    ObjectManager om;
    Random random;

    Vector playerVelocity;
    int playerLifes;

    int brickWidth;
    int brickHeight;

    int dWidth = 0;
    int dHeight = 0;


    public LevelManager(ObjectManager om, Context context, Point size){
        this.om = om;
        this.level = 1;
        this.numBricks = 0;
        this.context = context;
        this.random = new Random();
        this.dWidth = size.x;
        this.dHeight = size.y;
        startLevel(level);
    }
//  Tracks whether all bricks are destroyed or not.
    public void Update(){
        if(numBricks<=0){
            level++;
            this.om.deleteAll();
            startLevel(level);
        }
    }

    public void startLevel(int level){

        // Maximum 3 level.
        if(this.level > 3){
            this.level = 1;
        }

        // Handle init states of levels.
        if(this.level == 1 ){
            this.playerVelocity = new Vector(10,15);
            this.playerLifes = 3;
            this.brickWidth = 300;
            this.brickHeight = 60;
        }
        if(this.level == 2){
            this.playerVelocity = new Vector(20,30);
            this.playerLifes = 5;
            this.brickWidth = 200;
            this.brickHeight = 60;
        }

        if(this.level == 3){
            this.playerVelocity = new Vector(40,60);
            this.playerLifes = 7;
            this.brickWidth = 150;
            this.brickHeight = 40;
        }

        // Initialize objects.
        Ball ball = new Ball(new Vector((float)random.nextInt(dWidth),(float)dHeight/2), new Vector(10,15),  new Vector(dWidth, dHeight), new Vector(30, 30), this.om, context );
        Paddle paddle = new Paddle(new Vector ((float)dWidth/2 - 50,(float)dHeight*5/6), null, new Vector(dWidth, dHeight),  new Vector(200, 50), this.om ,context );
        HealthBar health = new HealthBar(null, Color.GREEN,  playerLifes, new Rect(dWidth-200, 30, dWidth-200+60*playerLifes, 80), new Vector(dWidth, dHeight), context, this.om );
        Score score = new Score(new Vector (20,120), Color.GREEN);

        // Activate objects
        ball.Activate();
        paddle.Activate();
        health.Activate();
        score.Activate();

        // Add objects to ObjectManager.
        this.om.AddObject(ball);
        this.om.AddObject(paddle);
        this.om.AddObject(health);
        this.om.AddObject(score);

        // Handle bricks.
        initBricks(this.level);
    }
    // Handle layout of bricks
    private void initBricks(int level){
        // Precalculate width and height of brick wall.
        float totalWidth = (float)dWidth/brickWidth;
        float totalHeight = (float)dHeight/brickHeight/10*level;

        // Precalculate offset to handle positioning of bricks in the middle of screen's X-axis,
        float offset = 0;
        for(int j = 0; j<totalWidth;j++){
            // Do not allow bricks to be added beyond screen boundaries.
            if(brickWidth*(j + 1)<=dWidth){
                offset = (dWidth - brickWidth*(j + 1))/2;
            }
        }

        // Create bricks.
        for(int i = 0; i<totalHeight;i++){
            for(int j = 0; j<totalWidth;j++){
                // Do not allow bricks to be added beyond screen boundaries.
                if(brickWidth*(j + 1)<=dWidth){
                    Brick brick = new Brick(new Vector(offset+j*brickWidth,150+i*brickHeight), new Vector(dWidth, dHeight), new Vector(this.brickWidth,this.brickHeight), context, this.om );
                    brick.Activate();
                    this.om.AddObject(brick);
                    this.numBricks++;
                }
            }

        }
    }

    // If brick is destroyed - decrement total number.
    public void decrementBricks(){
        this.numBricks--;
    }

    // Get current level.
    public int getLevel(){
        return this.level;
    }

}
