package com.tic_tac_toe.game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
    private JButton topLeft = new JButton(new ImageIcon("/home/daniel/Programming/Java/tic_tac_toe/src/com/tic_tac_toe/game/images/Empty.png"));
    private JButton topMid = new JButton(new ImageIcon("/home/daniel/Programming/Java/tic_tac_toe/src/com/tic_tac_toe/game/images/Empty.png"));
    private JButton topRight = new JButton(new ImageIcon("/home/daniel/Programming/Java/tic_tac_toe/src/com/tic_tac_toe/game/images/Empty.png"));
    private JButton midLeft = new JButton(new ImageIcon("/home/daniel/Programming/Java/tic_tac_toe/src/com/tic_tac_toe/game/images/Empty.png"));
    private JButton midMid = new JButton(new ImageIcon("/home/daniel/Programming/Java/tic_tac_toe/src/com/tic_tac_toe/game/images/Empty.png"));
    private JButton midRight = new JButton(new ImageIcon("/home/daniel/Programming/Java/tic_tac_toe/src/com/tic_tac_toe/game/images/Empty.png"));
    private JButton botLeft = new JButton(new ImageIcon("/home/daniel/Programming/Java/tic_tac_toe/src/com/tic_tac_toe/game/images/Empty.png"));
    private JButton botMid = new JButton(new ImageIcon("/home/daniel/Programming/Java/tic_tac_toe/src/com/tic_tac_toe/game/images/Empty.png"));
    private JButton botRight = new JButton(new ImageIcon("/home/daniel/Programming/Java/tic_tac_toe/src/com/tic_tac_toe/game/images/Empty.png"));

    /** The button used to exit out of the game. */
    private JButton quit = new JButton("Quit");

    
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

	// Add listeners to detect various events.
	topLeft.addActionListener(this);
	topMid.addActionListener(this);
	topRight.addActionListener(this);
	midLeft.addActionListener(this);
	midMid.addActionListener(this);
	midRight.addActionListener(this);
	botLeft.addActionListener(this);
	botMid.addActionListener(this);
	botRight.addActionListener(this);
	quit.addActionListener(this);

	GridBagConstraints constraints = new GridBagConstraints();
	
	// Add the buttons to the top-level panel.
	constraints.gridx = 0;
	constraints.gridy = 0;
	topPanel.add(topLeft, constraints);

	constraints.gridx = 1;
	topPanel.add(topMid, constraints);

	constraints.gridx = 2;
	topPanel.add(topRight, constraints);

	constraints.gridx = 0;
	constraints.gridy = 1;
	topPanel.add(midLeft, constraints);

	constraints.gridx = 1;
	topPanel.add(midMid, constraints);

	constraints.gridx = 2;
	topPanel.add(midRight, constraints);

	constraints.gridx = 0;
	constraints.gridy = 2;
	topPanel.add(botLeft, constraints);

	constraints.gridx = 1;
	topPanel.add(botMid, constraints);

	constraints.gridx = 2;
	topPanel.add(botRight, constraints);

	constraints.gridx = 1;
	constraints.gridy = 3;
	topPanel.add(quit, constraints);
    }

    /** Whenever we receive an event, fire off the appropriate actions. */
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals("Quit")) {
	    dispose();
	    System.out.println("Goodbye.");
	}
    }
}
