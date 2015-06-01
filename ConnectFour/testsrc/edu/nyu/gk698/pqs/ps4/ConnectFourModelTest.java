package edu.nyu.gk698.pqs.ps4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 * junit tests for ConnectFourModel
 * 
 * @author Geon Kang
 * @version %I%, %G%
 */
public class ConnectFourModelTest {
  ConnectFourModel testModel;
  
  @Before
  public void setup() {
    testModel = new ConnectFourModel();
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSetPlayer2_nullPlayerType() {
    testModel.setPlayer2(null);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSetPlayer2_setPlayer2AsPlayer1() {
    testModel.setPlayer2(ConnectFourPlayerType.PLAYER1);
  }
  
  @Test
  public void testSetPlayer2_player1AndComputer() {
    testModel.setPlayer2(ConnectFourPlayerType.PLAYER2);
    assertEquals(testModel.getPlayer2().getPlayerType(), 
        ConnectFourPlayerType.PLAYER2);
    testModel.setPlayer2(ConnectFourPlayerType.COMPUTER);
    assertEquals(testModel.getPlayer2().getPlayerType(), 
        ConnectFourPlayerType.COMPUTER);
  }
  
  @Test
  public void testDrop_playerSwitchAfterDrop() {
    testModel.setPlayer2(ConnectFourPlayerType.PLAYER2);
    testModel.drop(0);
    assertEquals(testModel.getPlayer2(), testModel.getCurrentPlayer());
  }

  @Test(expected=IllegalArgumentException.class)
  public void testDrop_dropInInvalidColumnLessThanZero() {
    testModel.drop(-1);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testDrop_dropInInvalidColumnGreaterThanColumnSize() {
    //Column size is expected to be out of bounds because column index is from
    //zero to less than column size.
    testModel.drop(testModel.getBoard().getColumnSize());
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testDrop_dropInInvalidColumnFullColumn() {
    testModel.drop(3);
    testModel.drop(3);
    testModel.drop(3);
    testModel.drop(3);
    testModel.drop(3);
    testModel.drop(3);
    testModel.drop(3);
  }
}
