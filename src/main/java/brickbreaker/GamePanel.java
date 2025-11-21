
package brickbreaker;

import javax.swing.JPanel; // JPanel for creating the game canvas 
import javax.swing.Timer; // timer for the game loop 
import java.awt.*; // AWT for graphics and colors 
import java.awt.event.*; // AWT event handling for the keyboard and timer 
import java.util.ArrayList; // Util Arraylist for storing bricks  
      


public class GamePanel extends JPanel implements ActionListener, KeyListener{
    // game state variables :
    private boolean play = false; // indicate  if the game is active (strats paused)
    private int score = 0; // track players score 
    private int totalBricks = 21 ; // total nbe of bricks 7x3 grid
    private int delay = 8 ; // Timer delay in milliseconds controls game speed  
    private Timer timer;  // Timer for the game loop 
    private Ball ball ; // ball object for boucing 
    private Paddle paddle; // paddle object for plater control 
    private ArrayList<Brick> bricks; // list store all bricks
   
    //Constructor to intialize the game 
    public GamePanel() {
        
      paddle = new Paddle(); // create new paddle instance 
      ball = new Ball(); // create new ball instance
      
      bricks = new ArrayList<>(); // initialize the brick list
      initBricks(); // set up bricks grid 
      
      setFocusable(true); // allow panel to receive keyboard input
        addKeyListener(this); // register this panel as key listener
     
     timer = new Timer(delay,this); // create timer that triggers every 8ms 
     timer.start(); // start the game loop
    }
    
    //initiaze the brick grid 7 col X 3rows 
    private void initBricks(){
        int brickWidth = 80; // width each brick
        int brickHeight = 30; // height each brick
        for (int j =0; j<3;j++) {              // loop for 3 rows
            for (int i =0; i<7;i++) {          // loop for 7 col
            int x = 50 + i*(brickWidth + 10);
            int y = 50 + j*(brickWidth + 10);
            bricks.add(new Brick( x, y , brickWidth,  brickHeight));
         //add a brick at the calculated position gaps
         //horizontal and vertical gap of 10 pixel || margin of 50 pixels from the top-left corner
            }
            }
    }
    // renders the game graphics 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);            //call parent method to clear the panel 
        
        //draw background 
        g.setColor(Color.BLACK); // set background color to black
        g.fillRect(0, 0, 800, 600); // fill entire panel 800x600
        
        //draw bricks
        for(Brick brick : bricks) {    //loop through bricks
             brick.draw(g);            // call each brick draw method
        }
        
        // draw paddle
        paddle.draw(g); // call paddle draw method
        //draw ball
        ball.draw(g); // call ball draw method
        //draw score 
        g.setColor(Color.PINK); //set text color to white 
        g.setFont(new Font("Sorrow",Font.BOLD,20)); //set font for score 
        g.drawString(" POINT : " + score,650,30 ); // display score at top-right
        
        //handle game over 
        if(ball.getY() > 570){       // check if the ball fall below paddle
           play = false ; // pauses the game
           g.setColor(Color.MAGENTA); 
           g.setFont(new Font ("Arial",Font.BOLD,30));
           g.drawString("Game Over X_X , Score: " + score,250,300 ); //display game over
           g.drawString("                                  Enter to Restart  ",150,100); // display restart prompt 
        }
        
        // handle win condition
        if(totalBricks <= 0) {              // if all bricks are destroyed 
        play = false ;
        g.setColor(Color.GREEN);
        g.setFont(new Font ("Arial",Font.BOLD,30));
        g.drawString("YOU WON $_$ , Score: " + score,150,200 ); // display win messsage 
        g.drawString("    Press Enter to Restart  ",150,100 );
       }
}
    // game loop : called by timer every 8ms 
    @Override
    public void actionPerformed(ActionEvent e){
        if(play){                                      //only update is game is active
         ball.move();                               // update the ball position
         checkCollisions();                          // check for collisions
         repaint();                                // request redraw of the panel 
        }
    }
    // check for collisions btw ball paddle bricks and walls 
private void checkCollisions() {

    // Ball and paddle collision
    if (new Rectangle(ball.getX(), ball.getY(), 20, 20).intersects(
            new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), 10))) {
        ball.setDy(-ball.getDy()); // reverse ball vertical direction
    }

    // Ball and brick collision
    for (int i = 0; i < bricks.size(); i++) {
        Brick brick = bricks.get(i); // correct method and reference

        Rectangle brickRect = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight()); // fixed typo

        if (new Rectangle(ball.getX(), ball.getY(), 20, 20).intersects(brickRect)) {
            bricks.remove(i); // remove brick
            totalBricks--;
            score += 5;

            // Determine collision side
            if (ball.getY() + 19 < brick.getY() || ball.getY() + 1 > brick.getY() + brick.getHeight()) {
                ball.setDy(-ball.getDy()); // vertical bounce
            } else {
                ball.setDx(-ball.getDx()); // horizontal bounce
            }
            break; // Only one brick hit per frame
        }
    }

    // Wall collisions
    if (ball.getX() < 0 || ball.getX() > 770) { // side walls
        ball.setDx(-ball.getDx());
    }
    if (ball.getY() < 0) { // top wall
        ball.setDy(-ball.getDy());
    }
}

    //handle key presses
    @Override
    public void keyPressed(KeyEvent e) {
      if(e.getKeyCode() == KeyEvent.VK_RIGHT){         //right arrow key
          paddle.moveRight();                          // move paddle right 
      }
      if(e.getKeyCode() == KeyEvent.VK_LEFT) {        //left arrow key
          paddle.moveLeft();                           // move paddle left
      }
      if(e.getKeyCode() == KeyEvent.VK_ENTER) {            // enter key
          if(!play) {                                       // if game is paused game over or win
            play = true ;                               //resume game
            ball = new Ball();      //reset ball
            paddle = new Paddle();  // reset paddle
            bricks = new ArrayList<>();  // reset bricks list
            initBricks(); // reinitialize bricks 
            score = 0; // reset score 
            totalBricks = 21; //reset brick count 
            repaint();  // redraw panel 
          }
      }
}
    //required by KeyListener but not used 
    @Override
    public void keyTyped(KeyEvent e){}
    
    
    @Override
    public void keyReleased(KeyEvent e) {}
}
