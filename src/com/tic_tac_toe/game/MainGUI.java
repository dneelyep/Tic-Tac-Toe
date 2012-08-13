package com.tic_tac_toe.game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.util.Random;

/** MainGUI.java - Class that starts the game, and displays game state 
 *              via graphics, etc.
 *
 * @author Daniel Neel */
public class MainGUI extends JFrame implements ActionListener { 

    /** topLeft/Mid/Right/etc.: A series of buttons that depict
     *  the 9 cells in a tic tac toe game. */
    private Cell topLeft  = new Cell(new ImageIcon("../images/Empty.png", "topLeft"));
    private Cell topMid   = new Cell(new ImageIcon("../images/Empty.png", "topMid"));
    private Cell topRight = new Cell(new ImageIcon("../images/Empty.png", "topRight"));
    private Cell midLeft  = new Cell(new ImageIcon("../images/Empty.png", "midLeft"));
    private Cell midMid   = new Cell(new ImageIcon("../images/Empty.png", "midMid"));
    private Cell midRight = new Cell(new ImageIcon("../images/Empty.png", "midRight"));
    private Cell botLeft  = new Cell(new ImageIcon("../images/Empty.png", "botLeft"));
    private Cell botMid   = new Cell(new ImageIcon("../images/Empty.png", "botMid"));
    private Cell botRight = new Cell(new ImageIcon("../images/Empty.png", "botRight"));

    /** The button used to exit out of the game. */
    private JButton quit = new JButton(new ImageIcon("../images/Quit.png", "Quit"));

    /** Text field that holds the number of times player X has won. */
    private JTextField xCount = new JTextField("0");

    /** Text field that holds the number of times player O has won. */
    private JTextField oCount = new JTextField("0");

    /** Text field that holds the number of times both players have tied. */
    private JTextField tiesCount = new JTextField("0");


    /** The top-level panel that holds all components in this object. */
    private JPanel topPanel = new JPanel(new GridBagLayout());

    
    // TODO: Misleading comment here.
    /** Bring up the main menu, allowing the player to 
     *  start a game or quit. */
    public static void main(String args[]) {
	new MainGUI();
    }

    /** Create a new MainGUI object by creating Swing
     *  components and initializing various values. */
    public MainGUI() {
	// Set window parameters.
        setSize(400, 300);
	setTitle("Tic tac toe!");
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	addControls();

	pack();
	setVisible(true);
    }

    /** Add all controls and visual components to the board so that the
     *  player can see game state and interact with the game. */
    public void addControls() {
	add(topPanel);

	GridBagConstraints constraints = new GridBagConstraints();

	quit.setBorder(null);
	quit.setRolloverIcon(new ImageIcon("../images/QuitRollover.png"));
	
	// Add the buttons to the top-level panel.
	createControl(topLeft,  constraints, 0, 0);
	createControl(topMid,   constraints, 1, 0);
	createControl(topRight, constraints, 2, 0);
	createControl(midLeft,  constraints, 0, 1);
	createControl(midMid,   constraints, 1, 1);
	createControl(midRight, constraints, 2, 1);
	createControl(botLeft,  constraints, 0, 2);
	createControl(botMid,   constraints, 1, 2);
	createControl(botRight, constraints, 2, 2);
	createControl(quit,     constraints, 1, 3);

	// Add the vertical separator between the board and win stats.
	constraints.gridx = 3;
	constraints.gridy = 0;
	constraints.gridheight = 5;
	constraints.fill = GridBagConstraints.VERTICAL;
	constraints.insets = new Insets(0, 20, 0, 0);
	JSeparator boardStatsSeparator = new JSeparator(SwingConstants.VERTICAL);
	boardStatsSeparator.setPreferredSize(new Dimension(50, 50));
	topPanel.add(boardStatsSeparator, constraints);

	constraints.gridheight = 1;
	constraints.gridx = 4;
	constraints.gridy = 0;
	constraints.insets = new Insets(0, 0, 0, 0);
	constraints.fill = SwingConstants.HORIZONTAL;
	JLabel xLabel = new JLabel("X wins:");
	topPanel.add(xLabel, constraints);

	constraints.gridx = 5;
	xCount.setEditable(false);
	topPanel.add(xCount, constraints);

	constraints.gridx = 4;
	constraints.gridy = 1;
	JLabel oLabel = new JLabel("O wins:");
	topPanel.add(oLabel, constraints);
	
	constraints.gridx = 5;
	oCount.setEditable(false);
	topPanel.add(oCount, constraints);

	constraints.gridx = 4;
	constraints.gridy = 2;
	JLabel tiesLabel = new JLabel("Ties:");
	topPanel.add(tiesLabel, constraints);

	constraints.gridx = 5;
	tiesCount.setEditable(false);
	topPanel.add(tiesCount, constraints);
    }

    /** Whenever we receive an event, fire off the appropriate actions. */
    public void actionPerformed(ActionEvent e) {
	JButton sourceButton = (JButton) e.getSource();
	ImageIcon sourceIcon = (ImageIcon) sourceButton.getIcon();

	if (sourceIcon.getDescription().equals("Quit")) {
	    dispose();
	    System.out.println("Goodbye.");
	}
	else {
	    // If we're not referring to the Quit button, we're referring to a Cell.
	    Cell cellSourceButton = (Cell) sourceButton;

	    // Make sure the Cell does not already have an owner.
	    if (cellSourceButton.isOwned() == false)
		cellSourceButton.setOwner('X');

	    // If nobody has won yet, have the computer do a turn.
	    if (checkForWinner() == 'N')
		AIDoTurn();

	    // And, if a player won or there's a tie, pop up the game-ending window.
	    doGameEndActions();
	}
    }

    /** Given a JButton button, some GridBagConstraints gbc, 
     *  and some x/y coordinates, add button to the topPanel with gridx/y as x and y.
     *  Also add an ActionListener to the button, so it can listen for events. */
    public void createControl(JButton button, GridBagConstraints gbc, int x, int y) {
	button.addActionListener(this);
	gbc.gridx = x;
	gbc.gridy = y;
	topPanel.add(button, gbc);
    }

    /** Check to see if player X or O has won this game. */
    public char checkForWinner() {
	// One player owns the top row.
	if (ownersAreEqual(topLeft, topMid, topRight))
	    return topLeft.getOwner();

	// One player owns the middle row.
	else if (ownersAreEqual(midLeft, midMid, midRight))
	    return midLeft.getOwner();

	// One player owns the bottom row.
	else if (ownersAreEqual(botLeft, botMid, botRight))
	    return botLeft.getOwner();

	// One player owns the left column.
	else if (ownersAreEqual(topLeft, midLeft, botLeft))
	    return topLeft.getOwner();

	// One player owns the middle column.
	else if (ownersAreEqual(topMid, midMid, botMid))
	    return topMid.getOwner();

	// One player owns the right column.
	else if (ownersAreEqual(topRight, midRight, botRight))
	    return topRight.getOwner();

	// One player owns from top-left to bottom-right.
	else if (ownersAreEqual(topLeft, midMid, botRight))
	    return topLeft.getOwner();

	// One player owns from bottom-left to top-right.
	else if (ownersAreEqual(botLeft, midMid, topRight))
	    return botLeft.getOwner();

	// All Cells are owned, but no winner has been previously declared.
	// In this case, we have a tie.
	else if (topLeft.isOwned()  == true &&
		 midLeft.isOwned()  == true &&
		 botLeft.isOwned()  == true &&
		 topMid.isOwned()   == true &&
		 midMid.isOwned()   == true &&
		 botMid.isOwned()   == true &&
		 topRight.isOwned() == true &&
		 midRight.isOwned() == true &&
		 botRight.isOwned() == true)
	    return 'T';

	// No winner so far.
	else
	    return 'N';
    }

    /** Have the computer player randomly select a Cell and attempt
     *  to own it. */
    public void AIDoTurn() {
	Cell[] cells = {topLeft,
			midLeft,
			botLeft,
			topMid,
			midMid,
			botMid,
			topRight,
			midRight,
			botRight};

	// Select a random number for the cell. If it's owned, try again.
	// Else, buy the cell.
	Random roll = new Random();
	int num = roll.nextInt(9);

	if (cells[num].isOwned() == true)
	    AIDoTurn();
	else
	    cells[num].setOwner('O');
    }

    /** If a player won the game or there is a tie, pop up
     *  the relevant dialog and increment the correct win score.
     *  If the game is not yet ended, do nothing. */
    public void doGameEndActions() {
	if (checkForWinner() == 'O' || checkForWinner() == 'X') {
	    JOptionPane.showMessageDialog(topPanel, "Player " + checkForWinner() + " wins!");
	    if (checkForWinner() == 'O')
		oCount.setText(Integer.toString(Integer.parseInt(oCount.getText()) + 1));
	    else
		xCount.setText(Integer.toString(Integer.parseInt(xCount.getText()) + 1));
	}

	// TODO: Get rid of this duplication.
	else if (checkForWinner() == 'T') {
	    JOptionPane.showMessageDialog(topPanel, "Tie game! No winner.");
	    tiesCount.setText(Integer.toString(Integer.parseInt(tiesCount.getText()) + 1));
	}
    }

    /** For three Cells a, b, and c, see if they all have the same owner, when
     *  the owner is player X or O. If they do, return true. Else, return false. */
    private boolean ownersAreEqual(Cell a, Cell b, Cell c) {
	if (a.getOwner() == b.getOwner() &&
	    b.getOwner() == c.getOwner() &&
	    a.getOwner() != '-')
	    return true;
	else
	    return false;
    }
}
