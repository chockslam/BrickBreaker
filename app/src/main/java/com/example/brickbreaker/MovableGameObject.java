package com.example.brickbreaker;

import android.content.Context;

// Abstract class represents object that is able to move across the space using its velocity.
public abstract class MovableGameObject extends DrawableGameObject {
    Vector vel;

    public MovableGameObject(Vector pos,Vector vel, Vector sceneDimensions, Vector size, Context context) {
        super(pos, sceneDimensions, size, context);
        this.vel = vel;
    }

}
