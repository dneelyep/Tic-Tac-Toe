package com.tic_tac_toe.game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	// Create the top-level panel to hold all other components.
	JPanel topPanel = new JPanel(new GridBagLayout());
	add(topPanel);

	GridBagConstraints constraints = new GridBagConstraints();

	// TODO: Add this to createControl()?
	topLeft.setBorder(null);
	topMid.setBorder(null);
	topRight.setBorder(null);
	midLeft.setBorder(null);
	midMid.setBorder(null);
	midRight.setBorder(null);
	botLeft.setBorder(null);
	botMid.setBorder(null);
	botRight.setBorder(null);
	quit.setBorder(null);

	topLeft.setRolloverIcon(new ImageIcon("../images/EmptyRollover.png"));
	topMid.setRolloverIcon(new ImageIcon("../images/EmptyRollover.png"));
	topRight.setRolloverIcon(new ImageIcon("../images/EmptyRollover.png"));
	midLeft.setRolloverIcon(new ImageIcon("../images/EmptyRollover.png"));
	midMid.setRolloverIcon(new ImageIcon("../images/EmptyRollover.png"));
	midRight.setRolloverIcon(new ImageIcon("../images/EmptyRollover.png"));
	botLeft.setRolloverIcon(new ImageIcon("../images/EmptyRollover.png"));
	botMid.setRolloverIcon(new ImageIcon("../images/EmptyRollover.png"));
	botRight.setRolloverIcon(new ImageIcon("../images/EmptyRollover.png"));
	quit.setRolloverIcon(new ImageIcon("../images/QuitRollover.png"));
	
	// Add the buttons to the top-level panel.
	createControl(topPanel, topLeft,  constraints, 0, 0);
	createControl(topPanel, topMid,   constraints, 1, 0);
	createControl(topPanel, topRight, constraints, 2, 0);
	createControl(topPanel, midLeft,  constraints, 0, 1);
	createControl(topPanel, midMid,   constraints, 1, 1);
	createControl(topPanel, midRight, constraints, 2, 1);
	createControl(topPanel, botLeft,  constraints, 0, 2);
	createControl(topPanel, botMid,   constraints, 1, 2);
	createControl(topPanel, botRight, constraints, 2, 2);
	createControl(topPanel, quit,     constraints, 1, 3);

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
	JTextField xCount = new JTextField("0");
	xCount.setEditable(false);
	topPanel.add(xCount, constraints);

	constraints.gridx = 4;
	constraints.gridy = 1;
	JLabel oLabel = new JLabel("O wins:");
	topPanel.add(oLabel, constraints);
	
	constraints.gridx = 5;
	JTextField oCount = new JTextField("0");
	oCount.setEditable(false);
	topPanel.add(oCount, constraints);

	constraints.gridx = 4;
	constraints.gridy = 2;
	JLabel tiesLabel = new JLabel("Ties:");
	topPanel.add(tiesLabel, constraints);

	constraints.gridx = 5;
	JTextField tiesCount = new JTextField("0");
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

	    checkForWinner();
	    //	    AIDoTurn();
	    //      checkForWinner();
	}
    }

    /** Given a JPanel panel, a JButton button, some GridBagConstraints gbc, 
     *  and some x/y coordinates, add button to the panel with gridx/y as x and y.
     *  Also add an ActionListener to the button, so it can listen for events. */
    public void createControl(JPanel panel, JButton button, GridBagConstraints gbc, int x, int y) {
	button.addActionListener(this);
	gbc.gridx = x;
	gbc.gridy = y;
	panel.add(button, gbc);
    }

    /** Check to see if player X or O has won this game. */
    public void checkForWinner() {
	// One player owns all the Cells on the top row.
	if (topLeft.getOwner() == topMid.getOwner() && 
	    topMid.getOwner() == topRight.getOwner() && 
	    topLeft.getOwner() != '-') {
	    System.out.println("Winner: " + topLeft.getOwner());
	}

	// One player owns all the Cells on the middle row.
	else if (midLeft.getOwner() == midMid.getOwner() && 
	    midMid.getOwner() == midRight.getOwner() && 
	    midLeft.getOwner() != '-') {
	    System.out.println("Winner: " + midLeft.getOwner());
	}

	// One player owns all the Cells on the bottom row.
	else if (botLeft.getOwner() == botMid.getOwner() && 
	    botMid.getOwner() == botRight.getOwner() && 
	    botLeft.getOwner() != '-') {
	    System.out.println("Winner: " + botLeft.getOwner());
	}

	// One player owns all the Cells on the left column.
	else if (topLeft.getOwner() == midLeft.getOwner() && 
	    midLeft.getOwner() == botLeft.getOwner() && 
	    topLeft.getOwner() != '-') {
	    System.out.println("Winner: " + topLeft.getOwner());
	}

	// One player owns all the Cells on the middle column.
	else if (topMid.getOwner() == midMid.getOwner() && 
	    midMid.getOwner() == botMid.getOwner() && 
	    topMid.getOwner() != '-') {
	    System.out.println("Winner: " + topMid.getOwner());
	}

	// One player owns all the Cells on the right column.
	else if (topRight.getOwner() == midRight.getOwner() && 
	    midRight.getOwner() == botRight.getOwner() && 
	    topRight.getOwner() != '-') {
	    System.out.println("Winner: " + topRight.getOwner());
	}

	// One player owns all the Cells diagonally from top-left to bottom-right.
	else if (topLeft.getOwner() == midMid.getOwner() && 
	    midMid.getOwner() == botRight.getOwner() && 
	    topLeft.getOwner() != '-') {
	    System.out.println("Winner: " + topLeft.getOwner());
	}

	// One player owns all the Cells diagonally from bottom-left to top-right.
	else if (botLeft.getOwner() == midMid.getOwner() && 
	    midMid.getOwner() == topRight.getOwner() && 
	    botLeft.getOwner() != '-') {
	    System.out.println("Winner: " + botLeft.getOwner());
	}
    }
}
