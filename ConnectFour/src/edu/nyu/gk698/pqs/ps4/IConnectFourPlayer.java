package edu.nyu.gk698.pqs.ps4;

/**
 * Interface for ConnectFourPlayer.
 * A ConnectFourPlayer has a playerType (PLAYER1, PLAYER2, COMPUTER)
 * and playerNumber which differentiates the turn and pieces of players.
 * 
 * @author Geon Kang
 * @version %I%, %G%
 */
public interface IConnectFourPlayer {
  
  /**
   * Getter method that returns the player type.
   * ConnectFourPlayerType is either PLAYER1, PLAYER2, or COMPUTER.
   * 
   * @return ConnectFourPlayerType enum that represents player type.
   */
  public ConnectFourPlayerType getPlayerType();
  
  /**
   * Getter method that returns the player number.
   * The player number will be 1 for PLAYER1, 
   * and 2 for either PLAYER2 or COMPUTER
   * 
   * @return int 1 or 2 representing player number
   */
  public int getPlayerNumber();
}
