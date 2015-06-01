package edu.nyu.gk698.pqs.ps4;

/**
 * Class representing a ConnectFour Computer opponent.
 * In addition to the methods in the interface IConnectFourPlayer,
 * ConnectFourComputer player provides a method which calculates the 
 * Computer opponent's next move. 
 * 
 * @author Geon
 * @version %I%, %G%
 */
public class ConnectFourComputerPlayer implements IConnectFourPlayer {
  
  private ConnectFourPlayerType playerType;
  private int playerNumber;
  
  /**
   * Constructor for ConnectFourComputerPlayer.
   * The player type is set to COMPUTER and the player number is set to 2.
   * (The computer always goes second.)
   */
  public ConnectFourComputerPlayer() {
    this.playerType = ConnectFourPlayerType.COMPUTER;
    this.playerNumber = 2;
  }
  
  /**
   * {@inheritDoc}
   */
  public ConnectFourPlayerType getPlayerType() {
    return this.playerType;
  }
  
  /**
   * {@inheritDoc}
   */
  public int getPlayerNumber() {
    return this.playerNumber;
  }
  
  /**
   * This method computes the next drop for the ConnectFourComputerPlayer.
   * If there is a single drop that will allow the computer to win, this method
   * will return the column index of the winning drop. Otherwise, it will
   * just return a random valid column index.
   *
   * @return int representing the next column index the computer player should
   *             drop its piece.
   */
  public int getNextDrop(ConnectFourBoard board) {
    for(int i = 0; i < board.getColumnSize(); i++) {
      board.dropPiece(i, this);
      if(board.checkWin(this)) {
        board.removePiece(i);
        return i;
      }
      board.removePiece(i);
    }
    int nextColumnNumber = (int)(Math.random()*board.getColumnSize());
    while(!board.isValidColumnNumber(nextColumnNumber)) {
      nextColumnNumber = (int)(Math.random()*board.getColumnSize());
    }
    return nextColumnNumber;
  }
  
  /**
   * {@inheritDoc}
   * Two ConnectFourComputerPlayers are considered equal if the have the same
   * playerType and playerNumber.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ConnectFourComputerPlayer)) {
      return false;
    }
    ConnectFourComputerPlayer playerToCompare = (ConnectFourComputerPlayer) o;
    if(playerToCompare.playerType == this.playerType &&
        playerToCompare.playerNumber == this.playerNumber) {
      return true;
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + playerType.hashCode() + (int)playerNumber;
    return result;    
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ConnectFourComputerPlayer: " + "\n");
    sb.append("Player Type: " + this.playerType + "\n");
    sb.append("Player Number: " + this.playerNumber + "\n");
    return sb.toString();
  }
}
