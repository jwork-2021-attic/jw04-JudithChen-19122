package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.calabashbros.MazeGenerator;
import com.anish.calabashbros.Wall;
import com.anish.calabashbros.Guide;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash bro;
    private int[][] maze;
    String[] moveSteps;
    private Wall wall;

    public WorldScreen() {
        world = new World();

        wall = new Wall(world);
        MazeGenerator mazeGenerator = new MazeGenerator(40);
        mazeGenerator.generateMaze();
        maze=mazeGenerator.getmaze();

        for(int i=0;i<40;i++){
            for(int j=0;j<40;j++){
                if(maze[i][j]==0)
                    world.put(wall, i, j);
            }
        }
        bro = new Calabash(new Color(204, 0, 0), 1, world);
        world.put(bro, 0, 0);
         
        Guide myguide=  new Guide();
        myguide.load(maze);
        myguide.findway();
        moveSteps = this.parsePlan(myguide.getPlan());
        
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash bro, String step) {
        String[] couple = step.split(",");
        bro.moveFromTo(bro.getX(),bro.getY(),Integer.parseInt(couple[0]), Integer.parseInt(couple[1]));
    }

 

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if (i < this.moveSteps.length) {
            this.execute(bro, moveSteps[i]);
            i++;
        }
        return this;
    }

}
