
package brickbreaker;

import java.awt.*;


public class Brick {            

   
//brick posotion and size 
    private int x,y,width,height;
    
    //constructor to initialize a brick 
    public Brick(int x, int y, int width, int height ){
        this.x = x;              //set x-coordinate
        this.y = y;              //set y-coordinate
        this.width = width;              //set width
        this.height = height;            //set height          
    }
    
    //draw bricks 
    public void draw(Graphics g){
        g.setColor(Color.BLUE);        //brick color to yellow
        g.fillRect(x, y, width, height);   //draw filled rectangle for brick
        g.setColor(Color.BLACK);       // set outline to black
        g.drawRect(x, y, width, height);   // draw brick outline
        
    }
    //GETTER for x-coordinate
    public int getX(){
        return x;
    }
     //GETTER for y-coordinate
    public int getY(){
        return y;
    }
    //getter for width 
    public int getWidth() {
        return width;
    }
    //geeter for height 
    public int getHeight(){
        return height;
    }
    
}

  