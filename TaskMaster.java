import javafx.scene.canvas.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.*;


public class TaskMaster extends TimerTask {
    
    GraphicsContext gc;
    Game game;
    Timer timer;
    
    //List<Sprite> list = new ArrayList<Sprite>();
    
    /** The width of the game screen */
    public static final int WIDTH = 800;
    /** The height of the game screen */
    public static final int HEIGHT = 600;
    
    public TaskMaster(GraphicsContext ngc, Game newgame, Timer newtimer){
        
        gc = ngc;
        game = newgame;
        timer = newtimer;
        //list = newlist;
        
    }
    
    int i = 0;
    int j = 0;
    
    String[] dialogue = {"Remember Me?","I am Clippy...","and I'll ask the questions now",
    "We're going to play a game", "A Brain Game"};
    
    String [] questions = {"What lobe holds your personality?",
    "What lobe deals with sensation?","What helps maintain your balance?", 
    "What lobe deals with sight?","What lobe deals with hearing?"};
    
    @Override
    public void run() {
        
        while(!game.spritelist.isEmpty()&&!game.gameOver){
        
            while(i!=(dialogue.length)&&!game.gameOver){
                
                System.out.println("Timer task started at:"+new Date());
                completeTask();
                System.out.println("Timer task finished at:"+new Date());
                
                // draw some text
                gc.setFill(Color.WHITE);
                //gc.setStroke(Color.BLACK);
                gc.setLineWidth(2);
                Font dFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
                gc.setFont(dFont);
                gc.fillText(dialogue[i], 100, 50);
                //gc.strokeText(, 80, 25);
                
                i++;
                
                completeTask();
                
                
            }
            
            while(j!=questions.length&&!game.gameOver){
                
                //System.out.println("Timer task started at:"+new Date());
                completeTask();
                //System.out.println("Timer task finished at:"+new Date());
                
                gc.setFill(Color.YELLOW);
                //gc.setStroke(Color.BLACK);
                gc.setLineWidth(2);
                Font qFont = Font.font("Times New Roman", FontWeight.BOLD, 40);
                gc.setFont(qFont);
                gc.fillText(questions[j], 100, 50);
                
                while(game.comparison()&&!game.gameOver){
                    
                    completeTask();
                    System.out.println("Timer task finished at:"+new Date());
                    
                    // draw some text
                    gc.setFill(Color.MAGENTA);
                    //gc.setStroke(Color.BLACK);
                    gc.setLineWidth(2);
                    Font wFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
                    gc.setFont(wFont);
                    gc.fillText("Correct!", 100, 50);
                    //gc.strokeText(, 80, 25);
                    
                    j++;
                    
                    
                    completeTask();
                    System.out.println("Timer task finished at:"+new Date());
                }
                
                //flag = false
                
                
                
                
            }
            timer.cancel();
            timer.purge();
        }

        System.out.println("Timer task started at:"+new Date());
        completeTask();
        System.out.println("Timer task finished at:"+new Date());
        
        // draw some text
        gc.setFill(Color.MAGENTA);
        //gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font wFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(wFont);
        gc.fillText("You Win!", 100, 50);
        //gc.strokeText(, 80, 25);
        
        completeTask();
        completeTask();
        
        System.out.println("The End");
        
        try {
            game.finish(gc);
        } 
        catch (InterruptedException e) {
            System.exit(0);
        }
         
    }

    private void completeTask() {
        try {
           
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        game.draw(gc);
        
    }


    public boolean click(double x, double y, Sprite sprite) {
        if(sprite.containsPoint(x,y)) {
          return true;
        }

        return false;
        }
}
