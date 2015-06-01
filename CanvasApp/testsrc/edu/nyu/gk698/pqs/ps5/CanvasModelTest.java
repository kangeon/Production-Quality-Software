package edu.nyu.gk698.pqs.ps5;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 * Junit tests for CanvasModel
 * 
 * @author Geon
 */
public class CanvasModelTest {

  CanvasModel testModel;
  
  @Before
  public void setup() {
    testModel = new CanvasModel();
  }
  
  @Test
  public void testAddPoint_singlePoint() {
    testModel.addPoint(333,333);
    assertEquals(testModel.getCanvasState().get(0).get(0).getX(), 333);
    assertEquals(testModel.getCanvasState().get(0).get(0).getY(), 333);
  }
  
  @Test
  public void testAddPoint_negativePoint() {
    testModel.addPoint(-6348,-1);
    assertEquals(testModel.getCanvasState().get(0).get(0).getX(), -6348);
    assertEquals(testModel.getCanvasState().get(0).get(0).getY(), -1);
  }
  
  
  @Test
  public void testAddPoint_multiplePoints() {
    testModel.addPoint(234,458);
    testModel.addPoint(8744,6302);
    testModel.addPoint(623, 7584);
    assertEquals(testModel.getCanvasState().get(0).get(0).getX(), 234);
    assertEquals(testModel.getCanvasState().get(0).get(0).getY(), 458);
    assertEquals(testModel.getCanvasState().get(0).get(1).getX(), 8744);
    assertEquals(testModel.getCanvasState().get(0).get(1).getY(), 6302);
    assertEquals(testModel.getCanvasState().get(0).get(2).getX(), 623);
    assertEquals(testModel.getCanvasState().get(0).get(2).getY(), 7584);
  }

  @Test
  public void testAddPoint_multiplePointsWithMouseRelease() {
    assertFalse(testModel.getMousePressed());
    testModel.addPoint(2784,7834);
    assertTrue(testModel.getMousePressed());
    testModel.addPoint(358,90173);
    //release mouse click
    testModel.mouseReleased();
    assertFalse(testModel.getMousePressed());
    //start a new draw with a new mouse click
    testModel.addPoint(32947,472);
    assertTrue(testModel.getMousePressed());
    testModel.addPoint(0, 0);
    assertEquals(testModel.getCanvasState().get(0).get(0).getX(), 2784);
    assertEquals(testModel.getCanvasState().get(0).get(0).getY(), 7834);
    assertEquals(testModel.getCanvasState().get(0).get(1).getX(), 358);
    assertEquals(testModel.getCanvasState().get(0).get(1).getY(), 90173);
    //Points after new mouse press should be in the next list of points.
    assertEquals(testModel.getCanvasState().get(1).get(0).getX(), 32947);
    assertEquals(testModel.getCanvasState().get(1).get(0).getY(), 472);
    assertEquals(testModel.getCanvasState().get(1).get(1).getX(), 0);
    assertEquals(testModel.getCanvasState().get(1).get(1).getY(), 0);
  }
  
  @Test
  public void testClearCanvas() {
    testModel.addPoint(5,632);
    testModel.addPoint(6782,890345);
    testModel.addPoint(1239, 83);
    assertFalse(testModel.getCanvasState().isEmpty());
    testModel.clearCanvas();
    assertTrue(testModel.getCanvasState().isEmpty());
  }
}
