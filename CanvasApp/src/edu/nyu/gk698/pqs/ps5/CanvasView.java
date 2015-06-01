package edu.nyu.gk698.pqs.ps5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * CanvasView is the GUI of the Canvas App. 
 * It uses Java Swing for the GUI, and implements CanvasListener
 * to listen to event triggers sent by the model to update the GUI.
 * 
 * @author Geon
 * @version %I%, %G%
 */
public class CanvasView implements CanvasListener {
  
  private CanvasModel canvasModel;
  private JFrame mainFrame;
  private JPanel mainPanel;
  private CanvasPanel canvasPanel;
  private JPanel bottomPanel;
  private JButton clearButton;

  /**
   * The constructor for the CanvasView.
   * Initializes all the GUI components of the Canvas app; a canvas panel
   * which all drawing happens on, and a button to clear the canvas.
   * 
   * @param canvasModel
   */
  public CanvasView(CanvasModel canvasModel) {
    this.canvasModel = canvasModel;
    canvasModel.addCanvasListener(this);
    mainFrame = new JFrame("Canvas");
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    
    canvasPanel = new CanvasPanel();
    mainPanel.add(canvasPanel, BorderLayout.CENTER);
    
    bottomPanel = new JPanel();
    clearButton = new JButton("Clear Canvas");
    clearButton.addActionListener(new ActionListener () {
      @Override
      public void actionPerformed(ActionEvent event) {
        clearCanvas();
      }
    });
    bottomPanel.add(clearButton);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);

    mainFrame.getContentPane().add(mainPanel);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.pack();
    mainFrame.setVisible(true);
    mainFrame.setResizable(false);
  }

  private void clearCanvas() {
    canvasModel.clearCanvas();
  }
  
  /**
   * {@inheritDoc}
   * This method goes through all the list of Points in the canvas state
   * and draws a line between all the points for each list of Points.
   */
  @Override
  public void drawn(List<List<Point>> canvasState) {
    Graphics page = canvasPanel.getGraphics();
    for (List<Point> points : canvasState) {
      for (int i = 0; i < points.size() - 1; i++) {
        page.drawLine(points.get(i).getX(), points.get(i).getY(), 
            points.get(i + 1).getX(), points.get(i + 1).getY());
      }
    }
  }

  @Override
  public void canvasCleared() {
    canvasPanel.clear();
  }

  //CanvasPanel on which all drawing happens.
  private class CanvasPanel extends JPanel implements MouseListener, 
      MouseMotionListener {

    //referenced code from 
    //http://www.cs.dartmouth.edu/~cs5/lectures/0504/DoodleCanvas.java
    private static final long serialVersionUID = 1L;

    private final int CANVAS_WIDTH = 500;
    private final int CANVAS_HEIGHT = 500;

    private CanvasPanel() {
      addMouseListener(this);
      addMouseMotionListener(this);
      setBackground(Color.WHITE);
      setMinimumSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
      setMaximumSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
      setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
    }

    //clears the canvasPanel by repainting it.
    private void clear() {
      repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      canvasModel.addPoint(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) { 
      canvasModel.addPoint(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      canvasModel.mouseReleased();
    }
    
    //Unused MouseEvent methods.
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {  
    }
  }
}
