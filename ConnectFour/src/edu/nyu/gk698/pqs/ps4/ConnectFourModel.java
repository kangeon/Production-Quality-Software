package edu.nyu.gk698.pqs.ps4;

import java.util.LinkedList;
import java.util.List;

/**
 * ConnectFourModel which contains the game logic for the Connect Four game.
 * It contains the major methods of the game and fires events to
 * ConnectFourListeners in its ConnectFourListener List to update the GUI
 * when events happen.
 * 
 * @author Geon
 * @version %I%, %G%
 */
public class ConnectFourModel {
  private IConnectFourPlayer player1;
  private IConnectFourPlayer player2;
  private IConnectFourPlayer currentPlayer;
  private ConnectFourBoard board;
  private List<ConnectFourListener> listeners;
  private boolean gameStarted;
  
  //helper function to change the current player
  private void switchTurn() {
    if(currentPlayer == player1) {
      currentPlayer = player2;
    }
    else {
      currentPlayer = player1;
    }
  }
  
  /**
   * Constructor for ConnectFourModel.
   * Default mode is playing against human player.
   */
  public ConnectFourModel() {
    player1 = ConnectFourPlayerFactory.getConnectFourPlayer
        (ConnectFourPlayerType.PLAYER1);
    player2 = ConnectFourPlayerFactory.getConnectFourPlayer
        (ConnectFourPlayerType.PLAYER2);
    currentPlayer = player1;
    board = ConnectFourBoard.getConnectFourBoard();
    gameStarted = false;
    listeners = new LinkedList<ConnectFourListener>();
  }
  
  /**
   * Creates player2 of the appropriate type which is passed in.
   * 
   * @param type
   * @throws IllegalArgumentException  if the type passed in is null or 
   *                                   is ConnectFourPlayerType.PLAYER1
   */
  public void setPlayer2(ConnectFourPlayerType type) {
    if (type == null) {
      throw new IllegalArgumentException("player type cannot be null");
    }
    if (type == ConnectFourPlayerType.PLAYER1) {
      throw new IllegalArgumentException("player2 cannot be of type PLAYER1");
    }
    if(type == ConnectFourPlayerType.PLAYER2) {
      player2 = ConnectFourPlayerFactory.getConnectFourPlayer
          (ConnectFourPlayerType.PLAYER2);
    }
    else {
      player2 = ConnectFourPlayerFactory.getConnectFourPlayer
          (ConnectFourPlayerType.COMPUTER);
    }
    firePlayer2SetEvent(player2.getPlayerType());
  }
  
  /**
   * Marks a piece drop in the specified column in the ConnectFourBoard object.
   * If the game has not started, the first drop will trigger the start of the 
   * game. This method checks for win conditions and game ties with each drop
   * and fires the appropriate event in the respective case. If the drop does
   * not result in a win or a tie, this method switches player turn and gets
   * the computer's drop if player2 is a computer player.
   * 
   * @param columnNumber Column number in which a piece is to be dropped.
   */
  public void drop(int columnNumber) {
    if(gameStarted == false) {
      gameStarted = true;
      fireGameStartedEvent();
    }
    if(board.isValidColumnNumber(columnNumber)) {
      board.dropPiece(columnNumber, currentPlayer);
      int rowNumber = board.getRowSize() - 
          board.getNumberOfPiecesInColumn(columnNumber); 
      firePieceDroppedEvent(columnNumber, rowNumber, currentPlayer);
    }
    else {
      throw new IllegalArgumentException("Invalid column number.");
    }
    if(board.checkWin(currentPlayer)) {
      fireGameWonEvent(currentPlayer);
    }
    else if(board.isFull()) {
      fireGameDrewEvent();
    }
    else {
      switchTurn();
      if(currentPlayer.getPlayerType() == ConnectFourPlayerType.COMPUTER) {
        ConnectFourComputerPlayer computerPlayer = 
            (ConnectFourComputerPlayer) currentPlayer;
        int computerDropColumn = computerPlayer.getNextDrop(board);
        drop(computerDropColumn);
      }
    }
  }
  
  /**
   * Resets the game.
   * <p>
   * Player1 always goes first in this implementation, so this method sets the
   * currentPlayer to player1. The board is cleared by calling the resetBoard
   * method in the ConnectFourBoard class. The gameStarted boolean is set to 
   * false, and the event is fired to all the listeners.
   */
  public void resetGame() {
    currentPlayer = player1;
    board.resetBoard();
    gameStarted = false;
    fireGameResetEvent();
  }
  
  /**
   * Method to add ConnectFourListeners to the list of ConnectFourListeners
   * of the ConnectFourModel.
   * 
   * @param connectFourListener
   */
  public void addConnectFourListener(ConnectFourListener connectFourListener) {
    listeners.add(connectFourListener);
  }
  
  /**
   * Getter method for player1.
   */
  public IConnectFourPlayer getPlayer1() {
    return this.player1;
  }
  
  /**
   * Getter method for player2.
   */
  public IConnectFourPlayer getPlayer2() {
    return this.player2;
  }
  
  /**
   * Getter method for the current player.
   */
  public IConnectFourPlayer getCurrentPlayer() {
    return this.currentPlayer;
  }
  
  /**
   * Getter method for the Connect Four board.
   */
  public ConnectFourBoard getBoard() {
    return this.board;
  }
  
  /**
   * Getter method for the gameStarted boolean.
   */
  public boolean getGameStarted() {
    return this.gameStarted;
  }
  
  private void fireGameStartedEvent() {
    for(ConnectFourListener listener : listeners) {
      listener.gameStarted();
    }
  }
  private void firePieceDroppedEvent(int column, int row, 
      IConnectFourPlayer player) {
    for(ConnectFourListener listener : listeners) {
      listener.pieceDropped(column, row, player);
    }
  }
  
  private void fireGameWonEvent(IConnectFourPlayer player) {
    for(ConnectFourListener listener : listeners) {
      listener.gameWon(player);
    }
  }
  
  private void fireGameDrewEvent() {
    for(ConnectFourListener listener : listeners) {
      listener.gameDrew();
    }
  }

  private void fireGameResetEvent() {
    for(ConnectFourListener listener : listeners) {
      listener.gameReset();
    }
  }
  
  private void firePlayer2SetEvent(ConnectFourPlayerType type) {
    for(ConnectFourListener listener : listeners) {
      listener.player2Set(type);
    }
  }
}
