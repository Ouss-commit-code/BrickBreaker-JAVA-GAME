
package brickbreaker;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
    public GameFrame()
{setTitle("Brick Breaker");// Title 
setSize(800,600); // set window size 
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit game when the window is closed 
setResizable(false); //prevent resizing of te window 
setLocationRelativeTo(null); // center the window on the screen 
add(new GamePanel()); // add GamePanel to frame
setVisible(true); //make te window visible 
}
//main method launch 
    public static void main(String[] args) {
        new GameFrame(); // create a new instance of GameFrame to start the game 
    }
    }
