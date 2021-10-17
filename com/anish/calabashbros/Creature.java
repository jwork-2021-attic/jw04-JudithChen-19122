package com.anish.calabashbros;

import java.awt.Color;

public class Creature extends Thing {

    Creature(Color color, char glyph, World world) {
        super(color, glyph, world);
    }

    public void moveTo(int xPos, int yPos) {
        this.world.put(this, xPos, yPos);
    }

    public void moveFromTo(int px, int py,int xPos, int yPos){
        this.world.put(this, xPos, yPos);
        this.world.put(new Trajectory(this.world),px,py);
    }

}
