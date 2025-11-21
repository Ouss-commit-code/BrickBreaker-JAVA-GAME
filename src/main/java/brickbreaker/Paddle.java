
package brickbreaker;

import java.awt.*;


public class Paddle {
 private int x = 350;  // initialize x-coordinate center of screen
 private final int y = 550;  // fixed y-coordinate near bottom
 private final int width = 100; // Paddle width
 private final int speed = 10; // movement speed in pixels 
  
   //draw padle
 public void draw(Graphics g){
     g.setColor(Color.LIGHT_GRAY);
     g.fillRect(x, y, width,10); //draw filled rectangle for paddle 10px height
     }
   //move paddle to the right 
public void moveRight(){
    if(x < 700){           // ensure paddle stays within right boundary 800 width
        x += speed;         //move paddle right by speed
    }
} 
//move paddle to the left 
public void moveLeft(){
    if(x > 10){           // ensure paddle stays within left boundary 800 width
        x -= speed;         //move paddle right by speed
    }
}
//getter for x-coordinate
public int getX(){
    return x;
}
//getter for y-coordinate 
public int getY(){
    return y;
}
//getter for width 
public int getWidth(){
    return width;
}
}

