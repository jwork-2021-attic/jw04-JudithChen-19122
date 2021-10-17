package com.anish.calabashbros;

public class Guide {
    private int[][] maze;
    private String plan = "";

    private int[][] ifrepeat;
    private int[][] nextlocationx; 
    private int[][] nextlocationy;

    public void load(int[][] a){
        maze=a;
        int len=a.length;
        ifrepeat = new int[len][len];
        nextlocationx = new int[len][len];
        nextlocationy = new int[len][len];
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                ifrepeat[i][j]=0;
                nextlocationx[i][j]=0; 
                nextlocationy[i][j]=0;
            }
        }
    }
    
    public String getPlan() {
        return this.plan;
    }    

    private void dfs(int px, int py, int nx, int ny){
        //System.out.println("here1");
        if(nx<0 || nx>maze.length-1 || ny<0 || ny>maze.length-1) return;
        //System.out.println("here2");
        if(ifrepeat[nx][ny]==1) return;
        //System.out.println("here3");
        if(maze[nx][ny]==0) return;
        //System.out.println("here");
        //System.out.println(maze.length);
        //System.out.println("here");
        ifrepeat[nx][ny] = 1;
        nextlocationx[nx][ny]=px;
        nextlocationy[nx][ny]=py;
        if(nx==0 && ny==0) {
            System.out.println("success!");
            return;
        }
        dfs(nx,ny,nx+1,ny);
        dfs(nx,ny,nx,ny+1);
        dfs(nx,ny,nx-1,ny);
        dfs(nx,ny,nx,ny-1);
    }


    public void findway(){
        for(int i=0; i<ifrepeat.length; i++){
            for(int j=0; j<ifrepeat.length; j++){
                ifrepeat[i][j]=0;
            }
        }
        ifrepeat[ifrepeat.length-1][ifrepeat.length-1] = 1;
        int nx = ifrepeat.length-1;
        int ny = ifrepeat.length-1;

        dfs(nx,ny,nx+1,ny);
        dfs(nx,ny,nx,ny+1);
        dfs(nx,ny,nx-1,ny);
        dfs(nx,ny,nx,ny-1);
        
        nx=0;
        ny=0;
        int tx,ty;

        while(nx != maze.length-1 || ny != maze.length-1){
            tx = nextlocationx[nx][ny];
            ty = nextlocationy[nx][ny];
            nx=tx;
            ny=ty;
            plan+="" + nx + "," + ny + "\n";
        }
        
    }
    
}
