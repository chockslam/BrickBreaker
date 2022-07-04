package com.example.brickbreaker;


import android.graphics.Canvas;
import android.transition.Scene;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;

// Object Manager Class.
// Core engine class,
// which handles :
// * updating,
// * rendering,
// * collision detection,
// * onTouch event detection.
public class ObjectManager {

    // Linked List of all scene-objects to be handled.
    private LinkedList<SceneObject> objects;
    // LevelManager is stored in a separate variable.
    private LevelManager lm;

    public ObjectManager(){
        this.objects = new LinkedList<SceneObject>();
    }
    // Add object to be handled.
    public void AddObject(SceneObject obj){
        this.objects.push((SceneObject) obj);
    }

    // Set-up LevelManager.
    public void AddLevelManager(LevelManager lm){
        if(this.lm == null){
            this.lm = lm;
        }
    }

    // Handle update function of all the active objects in the scene.
    public void UpdateAll(){

        for(SceneObject obj: this.objects){
            if(obj!= null){
                if(obj.isActive()){
                    obj.Update();
                }
            }
        }
        this.lm.Update();
    }

    // Handle Draw function of all the active objects in the scene.
    public void RenderAll(Canvas canvas){
        for(SceneObject obj: this.objects){
            if(obj!= null){
                if(obj.isActive()){
                    obj.Draw(canvas);
                }
            }
        }
    }

    // Delete all the inactive objects in the scene from the list.
    public void deleteInactive(){
        for(Iterator<SceneObject> iter = objects.iterator(); iter.hasNext();) {
            SceneObject obj = iter.next();
            if (!obj.isActive()) {
                iter.remove();
            }
        }
    }
    // Delete all objects in the scene from the list.
    public void deleteAll(){
        for(Iterator<SceneObject> iter = objects.iterator(); iter.hasNext();) {
            SceneObject obj = iter.next();
                iter.remove();
        }
    }

    // Handle Collisions.
    public void checkCollisions(){
        ListIterator<SceneObject> it1 = this.objects.listIterator();
        SceneObject current;

        // outer loop.
        while (it1.hasNext()) {
            current = (SceneObject) it1.next();

            // checks.
            if(current == null)
                continue;
            if(!(current instanceof Collidable))
                continue;
            if(!current.isActive())
                continue;
            // inner loop iterator starts from the next index...
            ListIterator it2 = this.objects.listIterator(it1.nextIndex());
            // inner loop.
            while (it2.hasNext()) {
                SceneObject runner = (SceneObject) it2.next();

                // checks
                if(runner == null)
                    continue;
                if(!(runner instanceof Collidable))
                    continue;
                if(!runner.isActive())
                    continue;
                // Call onCollision function on both objects.
                ((Collidable) current).onCollision(runner);
                ((Collidable) runner).onCollision(current);

            }
        }

    }

    //    Returns first encounter of Score object
    public Score getScore(){
        for(SceneObject obj: this.objects){
            if(obj instanceof Score){
                return (Score) obj;
            }
        }
        return null;
    }


    //    Returns first encounter of HealthBar object
    public HealthBar getHealthBar(){
        for(SceneObject obj: this.objects){
            if(obj instanceof HealthBar){
                return (HealthBar) obj;
            }
        }
        return null;
    }

    // Handle touchEvents of all objects.
    // Currently applicable only to paddle.
    public boolean processTouchEvent(MotionEvent event){
        for(SceneObject obj: this.objects){
            if(obj instanceof Controllable){
                return ((Controllable) obj).onTouch(event);
            }
        }
        return false;
    }

    // Get LevelManager.
    public LevelManager getLevelManager(){
        return this.lm;
    }



}
