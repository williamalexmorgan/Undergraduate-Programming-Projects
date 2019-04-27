import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author 
 */
public class Play extends Application {
    
  //List<Sprite> list = new ArrayList<Sprite>();

  public static void main(String[] args) {
      
      
      
      launch(args);
      
      
      
      
      
      
      
      
  }
  
  Timer timer;
  TaskMaster timerTask;
  
  Game game;
  

  @Override
  public void start(Stage theStage) {

    game = new Game();

    // Setup the Scene, Canvas, and GraphicsContext...
    theStage.setTitle(game.TITLE);

    Group root = new Group();
    Scene theScene = new Scene(root);
    theStage.setScene(theScene);

    Canvas canvas = new Canvas(game.WIDTH, game.HEIGHT);
    root.getChildren().add(canvas);

    // You will need to pass gc to any function that draws on the screen
    GraphicsContext gc = canvas.getGraphicsContext2D();
    game.draw(gc);
    
    theStage.show();
    
    timer = new Timer(true);
    
    timerTask = new TaskMaster(gc, game, timer);
      
    

    timer.schedule(timerTask, 0);
    
    
    
    //timer.schedule(timerTask, 4000);
    
    

    //System.out.println("TimerTask started");
    
    /*
    timer.cancel();
    
    //cancel after sometime
    try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    */
    game.draw(gc);
    
    
    // Mouse Click?
    
    theScene.setOnMouseClicked(
      new EventHandler<MouseEvent>() {
        public void handle(MouseEvent e) {
          // This is the turn-based click game format
          System.out.println("A click has occured.");
          boolean update = game.click(e.getX(), e.getY());
          
          if(update){
            
            game.flag = true;
        }
        
        else{
            
            game.flag = false;
        }
      }
     } 
    );

  
  
 }
 
 @Override
 public void stop(){
     
     
     
     game.gameOver=true;
     
     
     timerTask.cancel();
     timer.cancel();
     timer.purge();
     
     //Platform.exit();
     //
     
     System.exit(0);

     
 }
 
 
 /* public static void play(String path, int delay, int numberOfLoops) {
    for (int i = 0; i < numberOfLoops; i++) {
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = new File(path);
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(file));
                    clip.start();
                    Thread.sleep(clip.getMicrosecondLength());

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
  } */
 
 
}