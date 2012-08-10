package com.tic_tac_toe.game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.*;

/** MainGUI.java - Class that starts the game, and displays game state 
 *              via graphics, etc.
 *
 * @author Daniel Neel */
public class MainGUI extends JFrame implements ActionListener { 

    // TODO: Misleading comment here.
    /** Bring up the main menu, allowing the player to 
     *  start a game or quit. */
    public static void main(String args[]) {
	new MainGUI();
    }

    /** Create a new MainGUI object by creating Swing
     *  components and initializing various values. */
    public MainGUI() {
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
	JButton top_left = new JButton(new ImageIcon("/home/daniel/Programming/Java/tic_tac_toe/src/com/tic_tac_toe/game/images/Empty.png"));
	JButton quit = new JButton("Quit");

	top_left.addActionListener(this);
	quit.addActionListener(this);
	
	add(top_left);
	add(quit);
    }

    /** Whenever we receive an event, fire off the appropriate actions. */
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals("Quit")) {
	    dispose();
	    System.out.println("Goodbye.");
	}
    }
}
