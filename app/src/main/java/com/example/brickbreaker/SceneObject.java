package com.example.brickbreaker;

import android.graphics.Canvas;

// Abstract class represents any object in the scene.
public abstract class SceneObject {

    protected Vector sceneDimensions;
    protected Vector pos;
    protected boolean active;

    public SceneObject(Vector pos, Vector sceneDimensions) {
        this.sceneDimensions = sceneDimensions;
        this.pos = pos;
        this.active = false;
    }

    // Update position of the object.
    abstract public void Update();


    public void Activate() {
        this.active = true;
    }

    public void Deactivate() {
        this.active = false;
    }

    // Render the object.
    abstract public void Draw(Canvas canvas);

    public Vector getPos() {
        return pos;
    }

    public boolean isActive() {
        return active;
    }
}
