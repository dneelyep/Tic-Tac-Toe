package com.tic_tac_toe.game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

/** MainGUI.java - Class that starts the game, and displays game state 
 *              via graphics, etc.
 *
 * @author Daniel Neel */
public class MainGUI extends JFrame implements ActionListener { 

    /** topLeft/Mid/Right/etc.: A series of buttons that depict
     *  the 9 cells in a tic tac toe game. */
    private Cell topLeft  = new Cell(this, "topLeft");
    private Cell topMid   = new Cell(this, "topMid");
    private Cell topRight = new Cell(this, "topRight");
    private Cell midLeft  = new Cell(this, "midLeft");
    private Cell midMid   = new Cell(this, "midMid");
    private Cell midRight = new Cell(this, "midRight");
    private Cell botLeft  = new Cell(this, "botLeft");
    private Cell botMid   = new Cell(this, "botMid");
    private Cell botRight = new Cell(this, "botRight");

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
    
    /** Start a new game of tic-tac-toe, with an empty board and each
     *  player's win values set to 0. */
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

	// Make the fields that contain win stats un-editable.
	xCount.setEditable(false);
	oCount.setEditable(false);
	tiesCount.setEditable(false);

	quit.setBorder(null);
	quit.setRolloverIcon(new ImageIcon("../images/QuitRollover.png"));
	quit.addActionListener(this);

	GridBagConstraints constraints = new GridBagConstraints();
	
	// Add the Cells and quit button to the left side of the board.
	addComponent(topLeft,  constraints, 0, 0);
	addComponent(topMid,   constraints, 1, 0);
	addComponent(topRight, constraints, 2, 0);
	addComponent(midLeft,  constraints, 0, 1);
	addComponent(midMid,   constraints, 1, 1);
	addComponent(midRight, constraints, 2, 1);
	addComponent(botLeft,  constraints, 0, 2);
	addComponent(botMid,   constraints, 1, 2);
	addComponent(botRight, constraints, 2, 2);
	addComponent(quit,     constraints, 1, 3);

	// Add the vertical separator between the board and win stats.
	constraints.gridheight = 3;
	constraints.fill = GridBagConstraints.VERTICAL;
	constraints.insets = new Insets(0, 20, 0, 20);
	addComponent(new JSeparator(SwingConstants.VERTICAL), constraints, 3, 0);

	// Add the win stats labels and text fields to the right side of the board.
	constraints.gridheight = 1;
	constraints.insets = new Insets(0, 0, 0, 0);
	constraints.fill = SwingConstants.HORIZONTAL;
	addComponent(new JLabel("X wins:"), constraints, 4, 0);
	addComponent(new JLabel("O wins:"), constraints, 4, 1);
	addComponent(new JLabel("Ties:"), constraints, 4, 2);
	addComponent(xCount, constraints, 5, 0);
	addComponent(oCount, constraints, 5, 1);
	addComponent(tiesCount, constraints, 5, 2);
    }

    /** Whenever we receive an event, fire off the appropriate actions. */
    @Override
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
	    if (cellSourceButton.isOwned() == false) {
		cellSourceButton.setOwner('X');
	
		// If nobody has won yet, have the computer do a turn.
		if (checkForWinner() == 'N')
		    AIDoTurn();
	    }

	    // And, if a player won or there's a tie, pop up the game-ending window.
	    doGameEndActions();
	}
    }

    /** Add a given JComponent component to the board's topPanel
     *  with coordinates (x, y). */
    private void addComponent(JComponent component, GridBagConstraints gbc, int x, int y) {
	gbc.gridx = x;
	gbc.gridy = y;
	topPanel.add(component, gbc);
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
	Random randomRoller = new Random();
	int rollValue = randomRoller.nextInt(9);

	if (cells[rollValue].isOwned() == true)
	    AIDoTurn();
	else
	    cells[rollValue].setOwner('O');
    }

    /** If a player won the game or there is a tie, pop up
     *  the relevant dialog, increment the winner of the game's
     *  score, and reset the board so a new game can take place.
     *  If the game has not yet ended, do nothing. */
    public void doGameEndActions() {
	if (checkForWinner() == 'O' || checkForWinner() == 'X') {
	    JOptionPane.showMessageDialog(topPanel, "Player " + checkForWinner() + " wins!");
	    if (checkForWinner() == 'O')
		oCount.setText(Integer.toString(Integer.parseInt(oCount.getText()) + 1));
	    else
		xCount.setText(Integer.toString(Integer.parseInt(xCount.getText()) + 1));

	    resetBoard();
	}

	else if (checkForWinner() == 'T') {
	    JOptionPane.showMessageDialog(topPanel, "Tie game! No winner.");
	    tiesCount.setText(Integer.toString(Integer.parseInt(tiesCount.getText()) + 1));
	    
	    resetBoard();
	}
    }

    /** For three Cells a, b, and c, see if they all have the same owner, when
     *  the owner is player X or O. If they do, return true. Else, return false. */
    private boolean ownersAreEqual(Cell a, Cell b, Cell c) {
	if (a.getOwner() == b.getOwner() &&
	    b.getOwner() == c.getOwner() &&
	    a.isOwned()  == true)
	    return true;
	else
	    return false;
    }

    /** Reset the owners of all Cells to being unowned. */
    public void resetBoard() {
	topLeft.setOwner('N');
	midLeft.setOwner('N');
	botLeft.setOwner('N');
	topMid.setOwner('N');
	midMid.setOwner('N');
	botMid.setOwner('N');
	topRight.setOwner('N');
	midRight.setOwner('N');
	botRight.setOwner('N');
    }
}
