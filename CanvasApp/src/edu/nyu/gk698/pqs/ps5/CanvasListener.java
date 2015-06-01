package edu.nyu.gk698.pqs.ps5;

import java.util.List;

/**
 * The CanvasListener interface contains the method signatures of all
 * the important events that may happen in the course of drawing in a
 * Canvas app.
 * 
 * @author Geon
 * @version %I%, %G%
 */
public interface CanvasListener {
  
  /**
   * Method called when the CanvasListener hears that points have been drawn
   * on the Canvas. CanvasListener draws the state of the Canvas when it 
   * hears this event.
   * 
   * @param points  a two-dimensional List of Point objects. Each List of Points
   *                represents a new connection of Points.
   */
  public void drawn(List<List<Point>> points);
  
  /**
   * Method called when the CanvasListener hears that the Canvas has been 
   * cleared. CanvasListener clears the canvas when it hears this event.
   */
  public void canvasCleared(); 
}
