package edu.nyu.gk698.pqs.ps4;

/**
 * Connect Four board class that provides a method to drop pieces to the board
 * as well as providing methods and fields that are helpful with checking
 * game conditions such as win and column full. The ConnectFourBoard implements 
 * the singleton and singleton factory methods. As such, there can only be one
 * ConnectFourBoard object instantiated.
 * 
 * @author Geon
 * @version %I%, %G%
 */
public class ConnectFourBoard {
  
  private final int rowSize = 6;
  private final int columnSize = 7;
  private int[][] board;
  private int[] piecesDroppedInColumn;
  
  //Singleton & Singleton Factory pattern used to fulfill assignment 
  //requirements
  //Implementing ConnectFourBoard as Singleton.
  private static ConnectFourBoard boardSingleton = null;
  
  /*
   * Private constructor to make ConnectFourBoard a singleton.
   * ConnectFourBoard cannot be instantiated by any other class.
   * The board has 6 rows and 7 columns, which is represented as
   * a two dimensional int array. piecesDroppedInColumn is an int array with
   * size equal to the columnSize which stores the number of pieces that have 
   * been dropped in a column.  
   */
  private ConnectFourBoard() {
    this.board = new int[this.rowSize][this.columnSize];
    this.piecesDroppedInColumn = new int[this.columnSize];    
  }
  
  //Singleton Factory Pattern that returns ConnectFourBoard singleton
  public static synchronized ConnectFourBoard getConnectFourBoard() {
    if(boardSingleton == null) {
      boardSingleton = new ConnectFourBoard();
    }
    return boardSingleton;
  }
  
  /**
   * This method checks if the given column number is a valid one to
   * drop a piece. If the column number is out of range, or the given column
   * is already full, this method returns false.
   * 
   * @param columnNumber
   * @return true if the column number is in range and the column is not full.
   *         false otherwise.
   */
  public boolean isValidColumnNumber(int columnNumber) {
    if(columnNumber < 0 || columnNumber >= columnSize) {
      return false;
    }
    else if(piecesDroppedInColumn[columnNumber] == rowSize) {
      return false;
    }
    return true;
  }
  
  /**
   * Drops the player's piece in the given column.
   * The piece is represented by the player's number, which is added to the 
   * two dimensional int array board.
   * 
   * @param columnNumber  column index in which piece is to be dropped.
   * @param player  the player making the drop.
   * @return true if successful, false if not.
   * @throws IllegalArgumentException if player is null.
   */
  public boolean dropPiece(int columnNumber, IConnectFourPlayer player) {
    if(!isValidColumnNumber(columnNumber)) {
      throw new IllegalArgumentException("Not a valid column number.");
    }
    else if(player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }
    else {
      board[piecesDroppedInColumn[columnNumber]][columnNumber] 
          = player.getPlayerNumber();
      piecesDroppedInColumn[columnNumber] += 1;
      return true;
    }
  }
  
  /**
   * Removes the player's piece in the given column.
   * The removal is represented by setting the appropriate array member to 0
   * in the two dimensional int array board.
   * This method is used to undo look ahead moves done when the computer AI
   * is searching for a winning move.
   * 
   * @param columnNumber column index in which piece is to be dropped.
   * @param player  the player removing the piece.
   * @return true if successful, false if not.
   * @throws IllegalArgumentException if player is null.
   */
  public boolean removePiece(int columnNumber) {
    if(columnNumber < 0 || columnNumber > columnSize - 1) {
      throw new IllegalArgumentException("Not a valid column number.");
    }
    else if(piecesDroppedInColumn[columnNumber] <= 0) {
      throw new IllegalArgumentException("Nothing to remove.");
    }
    else {
      board[piecesDroppedInColumn[columnNumber]-1][columnNumber]
          = 0;
      piecesDroppedInColumn[columnNumber] -= 1;
      return true;
    }
  }
  
  
  //helper function to check win condition
  //Four in a row horizontally.
  private boolean checkRowWin(IConnectFourPlayer player) {
    for(int i = 0; i < rowSize; i++) {
      for(int j = 0; j < columnSize-3; j++) {
        if(board[i][j] == player.getPlayerNumber() 
            && board[i][j+1] == player.getPlayerNumber()
            && board[i][j+2] == player.getPlayerNumber()
            && board[i][j+3] == player.getPlayerNumber()) {
          return true;
        }
      }
    }
    return false;
  }
  
  //helper function to check win condition
  //Four in a row vertically.
  private boolean checkColumnWin(IConnectFourPlayer player) {
    for(int i = 0; i < columnSize; i++) {
      for(int j = 0; j < rowSize - 3; j++) {
        if(board[j][i] == player.getPlayerNumber()
            && board[j+1][i] == player.getPlayerNumber()
            && board[j+2][i] == player.getPlayerNumber()
            && board[j+3][i] == player.getPlayerNumber()) {
          return true;
        }
      }
    }
    return false;
  }
  
  //helper function to check win condition
  //Four in a row diagonally slanting up.
  private boolean checkUpDiagonalWin(IConnectFourPlayer player) {
    for(int i = 0; i < rowSize - 3; i++) {
      for(int j = 0; j < columnSize - 3; j++) {
        if(board[i][j] == player.getPlayerNumber()
            && board[i+1][j+1] == player.getPlayerNumber()
            && board[i+2][j+2] == player.getPlayerNumber()
            && board[i+3][j+3] == player.getPlayerNumber()) {
          return true;
        }
      }
    }
    return false;
  }
  
  //helper function to check win condition
  //Four in a row diagonally slanting down.
  private boolean checkDownDiagonalWin(IConnectFourPlayer player) {
    for(int i = rowSize-1; i > 2; i--) {
      for(int j = 0; j < columnSize - 3; j++) {
        if(board[i][j] == player.getPlayerNumber()
            && board[i-1][j+1] == player.getPlayerNumber()
            && board[i-2][j+2] == player.getPlayerNumber()
            && board[i-3][j+3] == player.getPlayerNumber()) {
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * This method checks to see if the player has four in a row in any direction.
   * 
   * @param player
   * @return true if player has four in a row, false if not.
   */
  public boolean checkWin(IConnectFourPlayer player) {
    if(checkRowWin(player) || checkColumnWin(player) 
        || checkUpDiagonalWin(player) || checkDownDiagonalWin(player)) {
      return true;
    }
    else {
      return false;
    }
  }
  
  /**
   * This method resets the board by setting all the items of the 
   * two dimensional int array board to zero.
   */
  public void resetBoard() {
    for(int i = 0; i < rowSize; i++) {
      for(int j = 0; j < columnSize; j++) {
        board[i][j] = 0;
      }
    }
    for(int i = 0; i < columnSize; i++) {
      piecesDroppedInColumn[i] = 0;
    }
  }
  
  /**
   * This method checks to see if the board is full. 
   * 
   * @return true if there are any columns which have more spaces
   *         false if all columns contain the same number of pieces 
   *               as the rowSize
   */
  public boolean isFull() {
    for(int i = 0; i < columnSize; i++) {
      if(piecesDroppedInColumn[i] < rowSize) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Method that returns the number of pieces that have been dropped so far
   * in the specified column.
   * 
   * @param columnNumber  Number specifiying the column of interest.
   * @return int  The number of pieces that have been dropped in the column.
   */
  public int getNumberOfPiecesInColumn(int columnNumber) {
    return piecesDroppedInColumn[columnNumber];
  }
  
  /**
   * Getter method that returns the row size of the ConnectFourBoard
   * @return
   */
  public int getRowSize() {
    return this.rowSize;
  }
  
  /**
   * Getter method that returns the column size of the ConnectFourBoard
   * @return
   */
  public int getColumnSize() {
    return this.columnSize;
  }
  
  public int[][] getBoard() {
    return this.board;
  }
  
  /**
   * Returns String representation of the board.
   * The board is represented by traversing through the two dimensional int 
   * array board, and appending each int to the output String. Each int in a 
   * row is separated by a space, and each row is separated with a newline.
   * When traversing, the row indexes are traversed in a decreasing order 
   * to make the String a better representation of a physical Connect Four 
   * board.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(int i = rowSize-1; i >= 0; i--) {
      for(int j = 0; j < columnSize; j++) {
        sb.append(board[i][j] + " "); 
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
