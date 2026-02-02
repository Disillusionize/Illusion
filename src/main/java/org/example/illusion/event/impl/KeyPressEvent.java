package org.example.illusion.event.impl;

import org.example.illusion.event.Event;
import org.lwjgl.input.Keyboard;

public final class KeyPressEvent implements Event {

    private final int key;
    private final boolean down;

    public KeyPressEvent() {
        this.key = Keyboard.getEventKey();
        this.down = Keyboard.getEventKeyState();
    }

    public int getKey() {
        return key;
    }

    public boolean isDown() {
        return down;
    }

}
