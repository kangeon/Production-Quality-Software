package edu.nyu.gk698.pqs.ps4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 * junit tests for ConnectFourComputerPlayer
 * 
 * @author Geon Kang
 * @version %I%, %G%
 */
public class ConnectFourComputerPlayerTest {
  ConnectFourBoard testBoard;
  ConnectFourComputerPlayer computer;
  
  @Before
  public void setup() {
    testBoard = ConnectFourBoard.getConnectFourBoard();
    //need reset with each setup because ConnectFourBoard is a singleton.
    testBoard.resetBoard();
    computer = new ConnectFourComputerPlayer();
  }
  
  @Test
  public void testGetNextDrop_winningMoveExists() {
    testBoard.dropPiece(0, computer);
    testBoard.dropPiece(1, computer);
    testBoard.dropPiece(2, computer);
    assertEquals(3, computer.getNextDrop(testBoard));
  }
}
