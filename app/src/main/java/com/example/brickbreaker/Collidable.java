package com.example.brickbreaker;
// Collidable interface.
// Denotes the object as able to collide with SceneObject objects.
public interface Collidable {

    void onCollision(SceneObject obj);
}
