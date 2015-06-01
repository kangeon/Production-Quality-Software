package edu.nyu.gk698.pqs.ps4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * ConnectFourView is the GUI of the ConnectFour game. 
 * It uses Java Swing for the GUI, and implements ConnectFourListener
 * to listen to event triggers sent by the model to update the GUI.
 * 
 * @author Geon
 * @version %I%, %G%
 */
public class ConnectFourView implements ConnectFourListener {
  private ConnectFourModel connectFourModel;
  private final int rowSize = 6;
  private final int columnSize = 7;
  private JFrame mainFrame;
  private JPanel mainPanel;
  private JPanel topPanel;
  private JPanel centerPanel;
  private JPanel bottomPanel;
  private JPanel mainPanelTopPanel;
  private JRadioButton vsHumanButton;
  private JRadioButton vsComputerButton;
  private ButtonGroup radioButtonGroup;
  private JButton[] dropButtonGrid;
  private JPanel[][] squarePanelGrid;
  private GridLayout dropButtonGridLayout;
  private GridLayout squarePanelGridLayout;
  private JLabel gameInfo;
  private JButton resetButton;
  
  private JLabel opponentPrompt;
  private JPanel mainPanelCenterPanel;
  
  /**
   * The constructor for the ConnectFourView.
   * Contains all the GUI components of the ConnectFour game, their layouts,
   * along with appropriate ActionListeners.
   * 
   * @param connectFourModel
   */
  public ConnectFourView(ConnectFourModel connectFourModel) {
    this.connectFourModel = connectFourModel;
    connectFourModel.addConnectFourListener(this);
    
    mainFrame = new JFrame("Connect Four");
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanelCenterPanel = new JPanel();
    mainPanelCenterPanel.setLayout(new BorderLayout());
    
    topPanel = new JPanel();
    dropButtonGridLayout = new GridLayout(0, columnSize);
    topPanel.setLayout(dropButtonGridLayout);
    dropButtonGrid = new JButton[columnSize];
    
    /* referenced code from:
       http://stackoverflow.com/questions/23330562
       /local-variable-i-is-accessed-within-an-inner-class-fix
       on how to create&add JButtons with actions in a for loop. 
    */
    for(int i = 0; i < columnSize; i++) {
      dropButtonGrid[i] = new JButton("Drop");
      final int index = i;
      dropButtonGrid[i].addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
          drop(index);
        }
      });
      topPanel.add(dropButtonGrid[i]);
    }
    
    centerPanel = new JPanel();
    squarePanelGridLayout = new GridLayout(rowSize, columnSize, 2, 2);
    centerPanel.setLayout(squarePanelGridLayout);
    squarePanelGrid = new JPanel[rowSize][columnSize];
    for(int i = 0; i < rowSize; i++) {
      for(int j = 0; j < columnSize; j++) {
        squarePanelGrid[i][j] = createSquareJPanel(Color.lightGray, 50);
        centerPanel.add(squarePanelGrid[i][j]);
      }
    }
    
    bottomPanel = new JPanel();
    bottomPanel.setLayout(new BorderLayout());
    gameInfo = new JLabel("Player1's Turn (Yellow)");
    bottomPanel.add(gameInfo, BorderLayout.WEST);
    resetButton = new JButton("Reset");
    resetButton.addActionListener(new ActionListener () {
      @Override
      public void actionPerformed(ActionEvent event) {
        resetGame();
      }
    });
    bottomPanel.add(resetButton, BorderLayout.EAST);
    
    mainPanelTopPanel = new JPanel();
    mainPanelTopPanel.setLayout(new GridLayout(0, 3));
    opponentPrompt = new JLabel("Play vs:");
    vsHumanButton = new JRadioButton("Human");
    vsHumanButton.setSelected(true);
    vsHumanButton.addActionListener(new ActionListener () {
      @Override
      public void actionPerformed(ActionEvent event) {
        selectedHuman();
      }
    });
    vsComputerButton = new JRadioButton("Computer");
    vsComputerButton.addActionListener(new ActionListener () {
      @Override
      public void actionPerformed(ActionEvent event) {
        selectedComputer();
      }
    });
    radioButtonGroup = new ButtonGroup();
    radioButtonGroup.add(vsHumanButton);
    radioButtonGroup.add(vsComputerButton);
    mainPanelTopPanel.add(opponentPrompt);
    mainPanelTopPanel.add(vsHumanButton);
    mainPanelTopPanel.add(vsComputerButton);

    mainPanelCenterPanel.add(topPanel, BorderLayout.NORTH);
    mainPanelCenterPanel.add(centerPanel, BorderLayout.CENTER);
    mainPanelCenterPanel.add(bottomPanel, BorderLayout.SOUTH);
    mainPanel.add(mainPanelCenterPanel, BorderLayout.CENTER);
    mainPanel.add(mainPanelTopPanel, BorderLayout.NORTH);
    mainFrame.getContentPane().add(mainPanel);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.pack();
    mainFrame.setVisible(true); 
    
  }
  
  /* Helper function to create the grid panels that represent a space on the
     Connect Four board.
     referenced code from:
     http://www.macs.hw.ac.uk/cs/java-swing-guidebook/?name=Layouts&page=6
  */
  private JPanel createSquareJPanel(Color color, int size) {
    JPanel tempPanel = new JPanel();
    tempPanel.setBackground(color);
    tempPanel.setMinimumSize(new Dimension(size, size));
    tempPanel.setMaximumSize(new Dimension(size, size));
    tempPanel.setPreferredSize(new Dimension(size, size));
    return tempPanel;
  }
  
  
  //Helper function used to communicate to the ConnectFourModel object that a 
  //drop action has been performed.
  private void drop(int columnNumber) {
    connectFourModel.drop(columnNumber);
  }
  
  //Helper function used to communicate to the ConnectFourModel object that the 
  //game reset action has been performed.
  private void resetGame() {
    connectFourModel.resetGame();
  }
  
  //Helper function used to communicate to the ConnectFourModel object that the 
  //select Human opponent action has been performed.
  private void selectedHuman() {
    connectFourModel.setPlayer2(ConnectFourPlayerType.PLAYER2);
  }
  
  //Helper function used to communicate to the ConnectFourModel object that the 
  //select Computer opponent action has been performed.
  private void selectedComputer() {
    connectFourModel.setPlayer2(ConnectFourPlayerType.COMPUTER);
  }
  
  /**
   * {@inheritDoc}
   * Disables the radio buttons used to select opponent type so that opponent
   * type cannot be changed mid-game.
   */
  public void gameStarted() {
    vsHumanButton.setEnabled(false);
    vsComputerButton.setEnabled(false);
  }

  /**
   * {@inheritDoc}
   * Changes the color of the panel at the specified position in the grid
   * to yellow or red depending on the number of the player that performed
   * the drop. If the column is full, the drop button for that column is 
   * disabled.
   * The row index of the grid is not intuitive due to the way the JPanels were
   * added to the grid. The index of the top row visually is 0.
   */
  public void pieceDropped(int columnNumber, int rowNumber, 
      IConnectFourPlayer player) {
    if(player == null) {
      throw new IllegalArgumentException("player cannot be null");
    }
    if(player.getPlayerNumber() == 1) {
      squarePanelGrid[rowNumber][columnNumber].setBackground(Color.yellow);
      gameInfo.setText("Player2's Turn. (Red)");
    }
    else if(player.getPlayerNumber() == 2) {
      squarePanelGrid[rowNumber][columnNumber].setBackground(Color.red);
      gameInfo.setText("Player1's Turn. (Yellow)");
    }
    if(rowNumber == 0) {
      dropButtonGrid[columnNumber].setEnabled(false);
    }
  }

  /**
   * {@inheritDoc}
   * Sets the text in the gameInfo JLabel to display the appropriate message
   * depending on the player that won the game. 
   * Disables all the  drop buttons so that no more pieces can be dropped.
   */
  public void gameWon(IConnectFourPlayer player) {
    if(player == null) {
      throw new IllegalArgumentException("player cannot be null");
    }
    if(player.getPlayerType() == ConnectFourPlayerType.PLAYER1) {
      gameInfo.setText("Player1 (Yellow) Wins!");
    }
    else if(player.getPlayerType() == ConnectFourPlayerType.PLAYER2) {
      gameInfo.setText("Player2 (Red) Wins!");
    }
    else if(player.getPlayerType() == ConnectFourPlayerType.COMPUTER) {
      gameInfo.setText("Computer (Red) Wins!");
    }
    for(int i = 0; i < columnSize; i++) {
      dropButtonGrid[i].setEnabled(false);
    }
  }

  /**
   * {@inheritDoc}
   * Sets the text in the gameInfo JLabel to display the appropriate message
   * depending on the player that won the game.
   * As a precaution, disables all the drop buttons so that no more pieces 
   * can be dropped. (Can be unnecessary because full column drop buttons are
   * disabled in the piecesDropped() method. 
   */
  public void gameDrew() {
    gameInfo.setText("Game Draw");
    for(int i = 0; i < columnSize; i++) {
      dropButtonGrid[i].setEnabled(false);
    }
  }

  /**
   * {@inheritDoc}
   * Sets the text in the gameInfo JLabel to display that the game has been
   * reset and it is player1's turn. Sets the color of all the JPanels in the
   * game grid to lightGray, enables all the drop buttons, and enables the 
   * radio buttons to specify the opponent type.
   */
  public void gameReset() {
    gameInfo.setText("Game Reset: Player1's Turn. (Yellow)");
    for(int i = 0; i < rowSize; i++) {
      for(int j = 0; j < columnSize; j++) {
        squarePanelGrid[i][j].setBackground(Color.lightGray);
      }
    }
    for(int i = 0; i < columnSize; i++) {
      dropButtonGrid[i].setEnabled(true);
    }
    vsHumanButton.setEnabled(true);
    vsComputerButton.setEnabled(true);
  }
  
  /**
   * {@inheritDoc}
   * Sets the radio button of the specified player type (Human or Computer)
   * to selected. This method is necessary to update all views of an opponent
   * type selected in one view.
   */
  public void player2Set(ConnectFourPlayerType type) {
    if(type == null) {
      throw new IllegalArgumentException("player type cannot be null");
    }
    if(type == ConnectFourPlayerType.PLAYER2) {
      vsHumanButton.setSelected(true);
    }
    else if(type == ConnectFourPlayerType.COMPUTER) {
      vsComputerButton.setSelected(true);
    }
    else {
      throw new IllegalArgumentException("Invalid player type");
    }
  }
}
