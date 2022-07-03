package com.example.brickbreaker;

import android.graphics.Canvas;

public abstract class SceneObject {
    protected Vector sceneDimensions;
    protected Vector pos;
    protected boolean active;

    public SceneObject(Vector pos, Vector sceneDimensions) {
        this.sceneDimensions = sceneDimensions;
        this.pos = pos;
        this.active = false;
    }

    abstract public void Update();

    public void Activate() {
        this.active = true;
    }

    public void Deactivate() {
        this.active = false;
    }

    abstract public void Draw(Canvas canvas);

    public Vector getPos() {
        return pos;
    }

    public boolean isActive() {
        return active;
    }
}
