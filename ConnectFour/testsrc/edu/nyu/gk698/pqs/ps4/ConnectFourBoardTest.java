package edu.nyu.gk698.pqs.ps4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 * junit tests for ConnectFourBoard
 * 
 * @author Geon Kang
 * @version %I%, %G%
 */
public class ConnectFourBoardTest {
  ConnectFourBoard testBoard;
  IConnectFourPlayer player1;
  IConnectFourPlayer player2;
  IConnectFourPlayer computer;
  
  @Before
  public void setup() {
    testBoard = ConnectFourBoard.getConnectFourBoard();
    //need reset with each setup because ConnectFourBoard is a singleton.
    testBoard.resetBoard();
    player1 = ConnectFourPlayerFactory.
        getConnectFourPlayer(ConnectFourPlayerType.PLAYER1);
    player2 = ConnectFourPlayerFactory.
        getConnectFourPlayer(ConnectFourPlayerType.PLAYER2);
    computer = ConnectFourPlayerFactory.
        getConnectFourPlayer(ConnectFourPlayerType.COMPUTER);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testDropPiece_invalidColumnLessThanZero() {
    testBoard.dropPiece(-1, player1);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testDropPiece_invalidColumnTooBig() {
    testBoard.dropPiece(testBoard.getColumnSize(), player1);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testDropPiece_fullColumn() {
    testBoard.dropPiece(2, player1);
    testBoard.dropPiece(2, player1);
    testBoard.dropPiece(2, player1);
    testBoard.dropPiece(2, player1);
    testBoard.dropPiece(2, player1);
    testBoard.dropPiece(2, player1);
    testBoard.dropPiece(2, player1);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testDropPiece_nullPlayer() {
    testBoard.dropPiece(5, null);
  }
  
  @Test
  public void testDropPiece() {
    testBoard.dropPiece(1, player1);
    assertEquals(1, testBoard.getBoard()[0][1]);
    testBoard.dropPiece(2, player2);
    assertEquals(2, testBoard.getBoard()[0][2]);
    testBoard.dropPiece(1, computer);
    assertEquals(2, testBoard.getBoard()[1][1]);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testRemovePiece_emptyColumn() {
    testBoard.removePiece(1);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testRemovePiece_invalidColumnLessThanZero() {
    testBoard.removePiece(-1);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testRemovePiece_invalidColumnTooBig() {
    testBoard.removePiece(testBoard.getColumnSize());
  }
  
  @Test
  public void testRemovePiece() {
    testBoard.dropPiece(3, player1);
    assertEquals(1, testBoard.getBoard()[0][3]);
    testBoard.removePiece(3);
    assertEquals(0, testBoard.getBoard()[0][3]);
  }
  
  @Test
  public void testCheckWin_rowWin() {
    testBoard.dropPiece(1, player1);
    testBoard.dropPiece(2, player1);
    testBoard.dropPiece(3, player1);
    testBoard.dropPiece(4, player1);
    assertEquals(true, testBoard.checkWin(player1));
  }
  
  @Test
  public void testCheckWin_columnWin() {
    testBoard.dropPiece(3, player2);
    testBoard.dropPiece(3, player2);
    testBoard.dropPiece(3, player2);
    testBoard.dropPiece(3, player2);
    assertEquals(true, testBoard.checkWin(player2));
  }
  
  @Test
  public void testCheckWin_upDiagonalWin() {
    testBoard.dropPiece(0, player1);
    testBoard.dropPiece(1, player2);
    testBoard.dropPiece(1, player1);
    testBoard.dropPiece(2, player2);
    testBoard.dropPiece(2, player2);
    testBoard.dropPiece(2, player1);
    testBoard.dropPiece(3, player2);
    testBoard.dropPiece(3, player2);
    testBoard.dropPiece(3, player2);
    testBoard.dropPiece(3, player1);
    assertEquals(true, testBoard.checkWin(player1));
  }
  
  @Test
  public void testCheckWin_downDiagonalWin() {
    testBoard.dropPiece(3, player2);
    testBoard.dropPiece(3, player1);
    testBoard.dropPiece(3, player1);
    testBoard.dropPiece(3, player1);
    testBoard.dropPiece(4, player2);
    testBoard.dropPiece(4, player1);
    testBoard.dropPiece(4, player1);
    testBoard.dropPiece(5, player2);
    testBoard.dropPiece(5, player1);
    testBoard.dropPiece(6, player2);
    assertEquals(true, testBoard.checkWin(player2));
  }
  
  @Test
  public void testCheckWin_noWinner() {
    testBoard.dropPiece(3, player2);
    testBoard.dropPiece(2, player1);
    testBoard.dropPiece(3, player1);
    testBoard.dropPiece(5, player1);
    testBoard.dropPiece(4, player2);
    assertEquals(false, testBoard.checkWin(player1));
    assertEquals(false, testBoard.checkWin(player2));
  }
  
  @Test
  public void testIsFull() {
    for(int i = 0; i < testBoard.getColumnSize(); i++) {
      for(int j = 0; j < testBoard.getRowSize(); j++) {
        testBoard.dropPiece(i, player1);
      }
    }
    assertEquals(true, testBoard.isFull());
  }
  
  @Test
  public void testIsFull_notFull() {
    for(int i = 0; i < testBoard.getColumnSize(); i++) {
      for(int j = 0; j < testBoard.getRowSize()-1; j++) {
        testBoard.dropPiece(i, player1);
      }
    }
    assertEquals(false, testBoard.isFull());
  }
  
  @Test
  public void testGetNumberOfPiecesInColumn() {
    testBoard.dropPiece(3, player1);
    testBoard.dropPiece(3, player2);
    testBoard.dropPiece(3, player2);
    testBoard.dropPiece(6, player2);
    testBoard.dropPiece(6, player1);
    testBoard.dropPiece(6, player1);
    testBoard.dropPiece(6, player1);
    testBoard.dropPiece(6, player2);
    testBoard.dropPiece(6, player2);
    assertEquals(3, testBoard.getNumberOfPiecesInColumn(3));
    assertEquals(6, testBoard.getNumberOfPiecesInColumn(6));
  }
}
