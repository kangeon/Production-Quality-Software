package edu.nyu.gk698.pqs.ps4;

/**
 * Class representing a ConnectFour human player.
 * 
 * @author Geon
 * @version %I%, %G%
 */
public class ConnectFourHumanPlayer implements IConnectFourPlayer {

  private ConnectFourPlayerType playerType;
  private int playerNumber;
 
  /**
   * Constructor for ConnectFourHumanPlayer. Uses builder pattern.
   */
  public ConnectFourHumanPlayer(Builder builder) {
    this.playerType = builder.playerType;
    this.playerNumber = builder.playerNumber;
  }
  
  //Using builder pattern for ConnectFourHumanPlayer
  //to satisfy requirements of the assignment.
  /**
   * Static Builder class that is used to construct a ConnectFourHumanPlayer.
   */
  public static class Builder {
    private ConnectFourPlayerType playerType;
    private int playerNumber;
    
    /**
     * Default Builder constructor for the Builder class.
     * All fields are required.
     */
    public Builder(ConnectFourPlayerType playerType, int playerNumber) {
      this.playerType = playerType;
      this.playerNumber = playerNumber;
    }
    
    /**
     * Constructs a ConnectFourHumanPlayer object with a 
     * ConnectFourHumanPlayer Constructor using this builder 
     * object.
     * 
     * @return  a ConnectFourHumanPlayer object constructed from 
     *          this Builder object.
     */
    public ConnectFourHumanPlayer build() {
      return new ConnectFourHumanPlayer(this);  
    }
  }
  
  /**
   * {@inheritDoc}
   */
  public ConnectFourPlayerType getPlayerType() {
    return this.playerType;
  }
  
  /**
   * {inheritDoc}
   */
  public int getPlayerNumber() {
    return this.playerNumber;
  }
  
  /**
   * {@inheritDoc}
   * Two ConnectFourHumanPlayers are considered equal if the have the same
   * playerType and playerNumber.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ConnectFourHumanPlayer)) {
      return false;
    }
    ConnectFourHumanPlayer playerToCompare = (ConnectFourHumanPlayer) o;
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
    sb.append("ConnectFourHumanPlayer: " + "\n");
    sb.append("Player Type: " + this.playerType + "\n");
    sb.append("Player Number: " + this.playerNumber + "\n");
    return sb.toString();
  }
}
