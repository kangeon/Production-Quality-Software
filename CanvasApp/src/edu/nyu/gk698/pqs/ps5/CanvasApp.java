package edu.nyu.gk698.pqs.ps5;

/**
 * The Canvas app which allows for drawing with mouse click and drag.
 * 
 * The Canvas app launches two views of the model. Both views can be used
 * to draw on the Canvas.
 * 
 * @author Geon
 * @version %I%, %G%
 */
public class CanvasApp {
  public static void main(String[] args) {
    CanvasModel model = new CanvasModel();
    new CanvasView(model);
    new CanvasView(model);
  }
}
