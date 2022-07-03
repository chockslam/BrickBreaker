package com.example.brickbreaker;


import android.graphics.Canvas;
import android.transition.Scene;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;

public class ObjectManager {

    private LinkedList<SceneObject> objects;
    private LevelManager lm;

    public ObjectManager(){
        objects = new LinkedList<SceneObject>();
    }
    public void AddObject(SceneObject obj){
        this.objects.push((SceneObject) obj);
    }
    public void AddLevelManager(LevelManager lm){
        if(this.lm == null){
            this.lm = lm;
        }
    }

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

    public void RenderAll(Canvas canvas){
        for(SceneObject obj: this.objects){
            if(obj!= null){
                if(obj.isActive()){
                    obj.Draw(canvas);
                }
            }
        }
    }

    public void deleteInactive(){
        for(Iterator<SceneObject> iter = objects.iterator(); iter.hasNext();) {
            SceneObject obj = iter.next();
            if (!obj.isActive()) {
                iter.remove();
            }
        }
    }

    public void deleteAll(){
        for(Iterator<SceneObject> iter = objects.iterator(); iter.hasNext();) {
            SceneObject obj = iter.next();
                iter.remove();
        }
    }

    public void checkCollisions(){
        ListIterator it1 = this.objects.listIterator();
        SceneObject current;

        while (it1.hasNext()) {
            current = (SceneObject) it1.next();

            if(current == null)
                continue;
            if(!(current instanceof Collidable))
                continue;
            if(!current.isActive())
                continue;

            ListIterator it2 = this.objects.listIterator(it1.nextIndex());
            while (it2.hasNext()) {
                SceneObject runner = (SceneObject) it2.next();

                if(runner == null)
                    continue;
                if(!(runner instanceof Collidable))
                    continue;
                if(!runner.isActive())
                    continue;

                ((Collidable) current).onCollision(runner);
                ((Collidable) runner).onCollision(current);

            }
        }

    }

    public Score getScore(){
        for(SceneObject obj: this.objects){
            if(obj instanceof Score){
                return (Score) obj;
            }
        }
        return null;
    }


    public HealthBar getHealthBar(){
        for(SceneObject obj: this.objects){
            if(obj instanceof HealthBar){
                return (HealthBar) obj;
            }
        }
        return null;
    }

    public boolean processTouchEvent(MotionEvent event){
        for(SceneObject obj: this.objects){
            if(obj instanceof Controllable){
                return ((Controllable) obj).onTouch(event);
            }
        }
        return false;
    }

    public LevelManager getLevelManager(){
        return this.lm;
    }



}
