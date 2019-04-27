import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Random;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 * @author William "Alex" Morgan
 */
public class Game {

  /** The name of the game */
  public static final String TITLE = "THE BRAIN GAME";
  /** The width of the game screen */
  public static final int WIDTH = 800;
  /** The height of the game screen */
  public static final int HEIGHT = 600;


  /** An unpredictable Game?..... */
  private Sprite frontalReg;
  private Sprite parietalReg;
  private Sprite temporalReg;
  private Sprite occipitalReg;
  private Sprite cerebellumReg;
  private Sprite brain;
  private Sprite clippy;
  
  private Sprite endclippy1;
  private Sprite endclippy2;
  private Sprite endclippy3;
  private Sprite endclippy4;
  
  private Sprite angryclippy;
  

  /** A background sprite */
  private Sprite background;

  /**
   * Constructs a new Game.
   */
   
   public List<Sprite> spritelist = new ArrayList<Sprite>();
   
   boolean flag;
   
   boolean gameOver=false;
   
   boolean stopmusic = false;
    
    
    
  public Game() {
      
    background = new Sprite(WIDTH, HEIGHT, "gfx/background.png");
    
    endclippy1 = new Sprite(WIDTH, HEIGHT, "gfx/clippy2.png");
    endclippy2 = new Sprite(WIDTH, HEIGHT, "gfx/clippy4.png");
    endclippy3 = new Sprite(WIDTH, HEIGHT, "gfx/clippy1.png");
    endclippy4 = new Sprite(WIDTH, HEIGHT, "gfx/clippy3.png");
    
    
    
    
    
    
    
    brain = new Sprite(500, 400, "gfx/brain.png", 150, 100);
    
    frontalReg = new Sprite(150, 100, "gfx/FrontalRegister.png", 200, 202);
    parietalReg = new Sprite(120, 100, "gfx/ParietalRegister.png", 400, 150);
    cerebellumReg = new Sprite(75, 50, "gfx/CerebellumRegister.png",450, 411);
    occipitalReg = new Sprite(50, 50, "gfx/OccipitalRegister.png", 580, 300);
    temporalReg = new Sprite(100, 100, "gfx/TemporalRegister.png", 390, 290);
    
    spritelist.add(new Sprite(150, 100, "gfx/FrontalRegister.png", 200, 202));
    spritelist.add(new Sprite(120, 100, "gfx/ParietalRegister.png", 400, 150));
    spritelist.add(new Sprite(75, 50, "gfx/CerebellumRegister.png",450, 411));
    spritelist.add(new Sprite(50, 50, "gfx/OccipitalRegister.png", 580, 300));
    spritelist.add(new Sprite(100, 100, "gfx/TemporalRegister.png", 390, 290));
    
    
    
    
    
    
    
    clippy = new Sprite(100, 100, "gfx/Clippy.png",0, 0);
    angryclippy= new Sprite(500, 500, "gfx/angryclippy.png",WIDTH/2, HEIGHT/2);
    
    String filepath = "gfx/Dire.wav";
      
    play(filepath, 0, 1);
    
    
  }

  /**
   * This method tells the game what to do when the player
   * clicks on the screen.
   * @param x the x coordinate of the mouse click
   * @param y the y coordinate of the mouse click
   * @return true if the screen needs to be updated after the click;
   *         false, otherwise
   */
   /*
 public boolean click(double x, double y) {
    if(frontalLobe.containsPoint(x,y)) {
      Random rand = new Random();

      // move the marble to a random spot on the board
      double nx = rand.nextDouble() * (WIDTH - marble.WIDTH);
      double ny = rand.nextDouble() * (HEIGHT - marble.HEIGHT);
      marble.setPosition(nx, ny);

      return true;
    }

    return false;
  }*/
  
  /**
   * Updates the entire screen. Usually this means drawing a bunch of
   * sprites to the screen! You should think about the GraphicsContext gc
   * as the screen.
   * @param gc the graphics context we are drawing on
   */
  public void draw(GraphicsContext gc) {
    // draw the sprites
    background.draw(gc);
    
    
    brain.draw(gc);
    
    frontalReg.draw(gc);
    parietalReg.draw(gc);
    temporalReg.draw(gc);
    occipitalReg.draw(gc);
    cerebellumReg.draw(gc);
    
    clippy.draw(gc);
    
    //frontalLobe.setPosition(300, 200);
    //parietalLobe.setPosition(200, 400);
    //temporalLobe.setPosition(150, 300);
    //occipitalLobe.setPosition(175, 500);
    //cerebellum.setPosition(100, 450);

    
  }
  
    public boolean comparison(){
        
        if(flag){
            
            Sprite sprite = spritelist.remove(0);
            
            flag = false;
            
            return true;
        }
        
        else{
            
            return false;
        }
    }
  
   public boolean click(double x, double y) {
       
        if(spritelist.get(0).containsPoint(x,y)) {
            
            //boolean flag = true;
            
            return true;
        }
        
        else{
            
           return false; 
        }
    }
    
    public void finish(GraphicsContext gc) throws InterruptedException {
        
        //TaskMaster.destroy();
        
        stopmusic = true;
        
        long start = System.currentTimeMillis();
        
        endclippy1.draw(gc);
        
        String filepath = "gfx/Dire.wav";
        String creepy = "gfx/creepy.wav";
        
        stopmusic = false;
        
        Thread.sleep(4000);
        
        endclippy2.draw(gc);
        
        Thread.sleep(4000);
        
        endclippy3.draw(gc);
        
        Thread.sleep(4000);
        
        endclippy4.draw(gc);
       
        play(filepath, 0, 1);
        play(creepy,0,1);
        Thread.sleep(1000);
        
        play(filepath, 0, 10);
        Thread.sleep(1000);
        play(filepath, 0, 10);
        Thread.sleep(1000);
        play(filepath, 0, 1);
        Thread.sleep(1000);
        angryclippy.draw(gc);
        Thread.sleep(1000);
        endclippy4.draw(gc);
        angryclippy.setPosition(WIDTH/4, HEIGHT/4);
        angryclippy.draw(gc);
        
        
        
        System.exit(0);
        
        
    }
    
    public void play(String path, int delay, int numberOfLoops) {
    
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
  }
  
  /*
  public void failure(){
    
    angryclippy.draw(gc);
      
    gc.setFill(Color.RED);
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(2);
    Font theFont = Font.font("Castellar", FontWeight.BOLD, 48);
    gc.setFont(theFont);
    gc.fillText("Clippy disliked that....", WIDTH/2, HEIGHT/2);
    gc.strokeText("Clippy disliked that....", WIDTH/2, HEIGHT/2);

    String filepath = "gfx/Dramatic.wav";
    
    musicStuff musicObject = new musicStuff();
    musicObject.playMusic(filepath);
  }
  
  public static void musicStuff(String musiclocation){
      
        File musicPath = new File(musicLocation);
        
        try{
          
            AudioInputStream audioinput = AudioSystem.getAudioInputStream(musicpath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            
            JOptionPane.showMessageDialog(frame,"Computer Cannot Contain Clippy!",
            "BeWaRe Of ClIpPy!",JOptionPane.WARNING_MESSAGE);
        }
        catch(Exception e){
          
            JOptionPane.showMessageDialog(null,"Error");
        }
  }

*/
}
