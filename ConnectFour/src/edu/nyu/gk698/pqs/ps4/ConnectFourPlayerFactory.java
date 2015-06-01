package edu.nyu.gk698.pqs.ps4;

/**
 * Factory class that returns the appropriate ConnectFourPlayer given
 * a ConnectFourPlayerType.
 *  
 * @author Geon
 * @version %I%, %G%
 */
public class ConnectFourPlayerFactory {

  //private constructor to prevent instantiation
  private ConnectFourPlayerFactory(){
  }
  
  /**
   * This method returns a Connect Four Player of the given type.
   * 
   * @param playerType
   * @return a ConnectFourHumanPlayer of type PLAYER1 or PLAYER2
   *         or a ConnectFourComputerPlayer
   * @throws IllegalArgumentException if the playerType given is null.
   */
  public static IConnectFourPlayer getConnectFourPlayer
      (ConnectFourPlayerType playerType) {
    if(playerType == null) {
      throw new IllegalArgumentException("playerType cannot be null.");
    }
    else if(playerType == ConnectFourPlayerType.PLAYER1) {
      IConnectFourPlayer player1 = new ConnectFourHumanPlayer.
          Builder(playerType, 1).build();
      return player1;
    }
    else if(playerType == ConnectFourPlayerType.PLAYER2) {
      IConnectFourPlayer player2 = new ConnectFourHumanPlayer.
          Builder(playerType, 2).build();
      return player2;
    }
    else {
      IConnectFourPlayer player2 = new ConnectFourComputerPlayer();
      return player2;
    }
  }  
}
