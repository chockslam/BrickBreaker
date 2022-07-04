package com.example.brickbreaker;

import android.view.MotionEvent;
// Controllable interface.
// Denotes whether the object can be controlled by touch.
public interface Controllable {

    boolean onTouch(MotionEvent event);
}
