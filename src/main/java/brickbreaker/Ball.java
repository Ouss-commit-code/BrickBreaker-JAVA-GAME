
package brickbreaker;

import java.awt.*;


public class Ball {
    private int x = 350; //initial x-coordinate center of screen
    private int y = 530; //intial y-coordinate above paddle
    private int dx = 2; //horizontal velocity pixel per frame
    private int dy = -2; // vertical velocity upward
    
    //draw ball position
    public void draw(Graphics g) {
        g.setColor(Color.pink);
        g.fillOval(x, y, 20, 20); // draw a filled circle 20x20 pixels
    }
    //update ball position
    public void move(){
        x += dx;    //move ball horizontally by dx 
        y += dy;    //move  ball vertically by dy
    }
    
    // getter for x-coordinate
    public int getX(){
        return x;
    }
    // getter for y-coordinate
    public int getY(){
        return y;
    }
    //getter for horizontal velocity 
    public int getDx(){
        return dx;
    }
    // Setter for horizontal velocity 
    public void setDx(int dx){
        this.dx = dx ;
    }
    //getter for vertical velocity 
    public int getDy(){
        return dy;
    }
    // setter for vertical velocity 
    public void setDy(int dy){
        this.dy = dy ;
    }
}