package org.example.illusion.event.impl;

import org.example.illusion.event.Event;


public final class JumpDelayEvent implements Event {
    private int jumpTicks = 10;

    public int getJumpTicks() {
        return jumpTicks;
    }

    public void setJumpTicks(int jumpTicks) {
        this.jumpTicks = jumpTicks;
    }
}
