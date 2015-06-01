package edu.nyu.gk698.pqs.ps5;

import java.util.LinkedList;
import java.util.List;

/**
 * The CanvasModel contains the logic for the Canvas app and stores the state
 * of the Canvas as a two-dimensional List of all drawn points. The CanvasModel
 * fires reset and draw events to all CanvasListeners listening to update the
 * views.
 * 
 * @author Geon
 */
public class CanvasModel {
  private boolean mousePressed;
  private List<List<Point>> canvasState;
  private List<CanvasListener> listeners;
  
  /**
   * Constructor for CanvasModel.
   * Mouse is set to not pressed, and the canvas state and listener list
   * is initialized.
   */
  public CanvasModel() {
    this.mousePressed = false;
    this.canvasState = new LinkedList<List<Point>>();
    this.listeners = new LinkedList<CanvasListener>();
  }

  /**
   * addPoint takes in two ints which represent an x and y coordinate and
   * adds the point having those coordinates to the canvasState. 
   * 
   * If the mouse is not pressed, this method adds the Point to a new list of 
   * Points to represent a new mouse click-drag draw sequence. 
   * If the mouse is pressed, this method adds the Point to the very 
   * last list of Points in the canvasState to represent a continuing mouse
   * click-drag draw sequence.
   * 
   * @param x  x coordinate of the point to be added to the canvasState.
   * @param y  y coordinate of the point to be added to the canvasState.
   */
  public void addPoint(int x, int y) {
    if(mousePressed == false) {
      mousePressed = true;
      List<Point> newPointSequence = new LinkedList<Point>();
      newPointSequence.add(new Point(x, y));
      canvasState.add(newPointSequence);
    }
    else {
      canvasState.get(canvasState.size()-1).add(new Point(x, y));
    }
    fireDrawnEvent();
  }
  
  /**
   * Indicates that the mouse click has been released by setting the 
   * mousePressed boolean to false.
   */
  public void mouseReleased() {
    mousePressed = false;
  }
  
  /**
   * Clears the canvasState and fires the canvasClearedEvent to all 
   * listening CanvasListeners.
   */
  public void clearCanvas() {
    canvasState.clear();
    fireCanvasClearedEvent();
  }
  
  /**
   * Getter method for the canvas state.
   * 
   * This method is only included for Junit assertion purposes.
   * As it returns a direct reference to the canvas state which allows for
   * the canvas state to become modified from another class, it should be used
   * with caution.
   *  
   * @return List<List<Point>>  two-dimensional List of Points representing
   *                            the canvas state.
   */
  public List<List<Point>> getCanvasState() {
    return canvasState;
  }
  
  /**
   * Getter method for mousePressed.
   * 
   * @return boolean  true if mouse is currently pressed. false otherwise.
   */
  public boolean getMousePressed() {
    return mousePressed;
  }
  
  /**
   * Adds a CanvasListeners to the list of CanvasListeners of the CanvasModel.
   * 
   * @param CanvasListener
   */
  public void addCanvasListener(CanvasListener canvasListener) {
    listeners.add(canvasListener);
  }
  
  private void fireDrawnEvent() {
    for(CanvasListener listener : listeners) {
      listener.drawn(canvasState);
    }
  }
  
  private void fireCanvasClearedEvent() {
    for(CanvasListener listener : listeners) {
      listener.canvasCleared();
    }
  }
}
