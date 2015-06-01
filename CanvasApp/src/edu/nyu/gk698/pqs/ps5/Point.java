package edu.nyu.gk698.pqs.ps5;

/**
 * The point class represents a single point in 2D space by encapsulating
 * the x and y coordinates of the point as an int.
 *  
 * @author Geon
 */
public class Point {
  
  private int x;
  private int y;
  
  /**
   * Constructs a point object with given x and y coordinates.
   * 
   * @param x
   * @param y
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Getter method for the x-coordinate of this point.
   * 
   * @return x  int representing the x-coordinate of this point.
   */
  public int getX() {
    return this.x;
  }
  
  /**
   * Getter method for the y-coordinate of this point
   * 
   * @return y  int representing the y-coordinate of this point.
   */
  public int getY() {
    return this.y;
  }
  
  /**
   * {@InheritDoc}
   * Example string output:
   * Point = x: 0 y: 0
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Point = " + "x: " + this.x + "y: " + this.y + "\n");
    return sb.toString();
  }
}
