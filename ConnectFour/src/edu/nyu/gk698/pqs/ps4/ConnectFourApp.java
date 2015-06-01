package edu.nyu.gk698.pqs.ps4;

/**
 * The Connect Four game app.
 * Creates a new ConnectFourModel and launches two views of the model.
 * 
 * @author Geon
 * @version %I%, %G%
 */
public class ConnectFourApp {
  public static void main(String[] args) {
    ConnectFourModel model = new ConnectFourModel();
    new ConnectFourView(model);
    new ConnectFourView(model);
  }
}
