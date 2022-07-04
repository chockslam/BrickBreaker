package com.example.brickbreaker;


// Vector class.
// Does not have any vector specific operations.
// Should be considered in the next iteration of the engine refactoring.
public class Vector {
    private float x,y;
    public Vector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
