import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

/**
 * A basic sprite class that handles the position and collision detection
 * for a rectangular image. You should only use png image files! For example,
 * let's say you have some png images called "image1.png" and "image2.png"
 * in the folder called "gfx". Let's assume both of these images are
 * 120 pixels wide and 80 pixels tall. Here is an example:
 *
 * <pre>{@code

  // create two sprites
  // create a sprite at position (0.0,0.0)
  Sprite mySprite = new Sprite(120, 80, "gfx/image1.png");
  // create a sprite at position (30.0, 50.0)
  Sprite otherSprite = new Sprite(120, 80, "gfx/image2.png", 30.0, 50.0);

  // draw the sprites to the screen (assume gc is a GraphicsContext)
  mySprite.draw(gc);
  otherSprite.draw(gc);

  // move the other sprite somewhere else
  otherSprite.setPosition(180.0, 203.0);

  // check to see if the two sprites are intersecting
  if(mySprite.intersects(otherSprite)) {
    // Do something! They are intersecting!
  } else {
    // Do something else! The are not intersecting!
  }

  // check to see if a point is inside of a sprite
  if(mySprite.containsPoint(15.0, 25.0)) {
    // Do something! The point (15.0, 25.0) is inside the sprite!
  } else {
    // Do something else! The point (15.0, 25.0) is not inside the sprite!
  }

  
public class Sprite {

  /** The sprite's width in pixels */
  public final int WIDTH;
  /** The sprite's height in pixels */
  public final int HEIGHT;

  /**
   * The filename of the image representing the sprite.
   * This is assumed to be a .png file (do not include the extension .png).
   */
  private String filename;
  /** The Image of the sprite */
  private Image image;
  /** The x coordinate of the upper-left corner */
  private double x;
  /** The y coordinate of the upper-left corner */
  private double y;

  /**
   * Constructs a new Sprite from a png file. This will set the position to
   * be (0.0, 0.0).
   * @param width the width of the sprite in pixels
   * @param height the height of the sprite in pixels
   * @param filename the png file containing the sprite image
   */
  public Sprite(int width, int height, String filename) {
    this(width, height, filename, 0.0, 0.0);
  }

  /**
   * Constructs a new Sprite from a png file. If the width and height do not
   * match the image's dimensions, then the image will be stretched or
   * compressed to fit (and the aspect ratio will be destroyed).
   * @param width the width of the sprite in pixels
   * @param height the height of the sprite in pixels
   * @param filename the png file containing the sprite image
   * @param x the x coordinate of the upper-left corner of the sprite
   * @param y the y coordinate of the upper-left corner of the sprite
   */
  public Sprite(int width, int height, String filename, double x, double y) {
    WIDTH = width;
    HEIGHT = height;
    this.filename = filename;
    image = new Image(filename, WIDTH, HEIGHT, false, false);
    setPosition(x,y);
  }

  /**
   * Gets the filename of the image used by the sprite
   * @return the filename of the image used by the sprite
   */
  public String getFilename() {
    return filename;
  }

  /**
   * Sets the (x,y) position of the sprite.
   * @param x the new x coordinate
   * @param y the new y coordinate
   */
  public void setPosition(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Sets the x coordinate of the sprite
   * @param x the new x coordinate
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Sets the y coordinate of the sprite
   * @param y the new y coordinate
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * Gets the x coordinate of the sprite
   * @return the x coordinate of the sprite
   */
  public double getX() {
      return x;
  }

  /**
   * Gets the y coordinate of the sprite
   * @return the y coordinate of the sprite
   */
  public double getY() {
      return y;
  }

  /**
   * Draws the sprite on the graphics context. You should call this method
   * whenever you want to draw the sprite on the screen.
   * @param gc the graphics context on which to draw the sprite
   */
  public void draw(GraphicsContext gc) {
    gc.drawImage(image, x, y);
  }

  /**
   * Determines if the bounding rectangle of the sprite contains the point (a,b)
   * @param a the horizontal coordinate of the point
   * @param b the vertical coordinate of the point
   * @return true if the point is in the bounding rectangle; false, otherwise
   */
  public boolean containsPoint(double a, double b) {
    Rectangle2D rect = new Rectangle2D(x, y, WIDTH, HEIGHT);
    return rect.contains(a,b);
  }

  /**
   * Determines if this sprite is currently intersecting another sprite.
   * This is done by checking if their bounding rectangles overlap.
   * @param other the other sprite
   * @return true if the bounding rectangles of the sprites intersect;
   *         false, otherwise
   */
  public boolean intersects(Sprite other) {
    Rectangle2D thisRect = new Rectangle2D(x,y,WIDTH,HEIGHT);
    Rectangle2D otherRect =
      new Rectangle2D(other.x, other.y, other.WIDTH, other.HEIGHT);

    return thisRect.intersects(otherRect);
  }

  /**
   * Converts the sprite to a string of the form: filename @ (x,y)
   * @return "filename @ (x,y)"
   */
  public String toString() {
    return filename + "@" + "(" + x + ", " + y + ")";
  }

}
