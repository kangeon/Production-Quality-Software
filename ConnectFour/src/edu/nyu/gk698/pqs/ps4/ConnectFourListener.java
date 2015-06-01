package edu.nyu.gk698.pqs.ps4;

/**
 * The ConnectFourListener interface contains the method signatures of all
 * the important events that may happen in the course of a ConnectFour game.
 * 
 * @author Geon
 * @version %I%, %G%
 */
public interface ConnectFourListener {
  
  /**
   * Method called when the ConnectFourListener hears that the game has
   * started.
   */
  public void gameStarted();
  
  /**
   * Method called when the ConnectFourListener hears that a piece has been 
   * dropped.
   * 
   * @param columnNumber
   * @param rowNumber
   * @param player
   */
  public void pieceDropped(int columnNumber, int rowNumber,
      IConnectFourPlayer player);
  
  /**
   * Method called when the ConnectFourListener hears that the game has been
   * won by a player.
   * 
   * @param player  Player who won the game.
   */
  public void gameWon(IConnectFourPlayer player);
  
  /**
   * Method called when the ConnectFourListener hears that the game has 
   * ended in a draw.
   */
  public void gameDrew();
  
  /**
   * Method called when the ConnectFourListener hears that the game has been
   * reset.
   */
  public void gameReset();
  
  /**
   * Method called when the ConnectFourListener hears that the type of
   * player2 has been set.
   * 
   * @param type
   */
  public void player2Set(ConnectFourPlayerType type);
}
