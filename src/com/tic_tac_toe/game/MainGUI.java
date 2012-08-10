package com.tic_tac_toe.game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.*;

/** MainGUI.java - Class that starts the game, and displays game state 
 *              via graphics, etc.
 *
 * @author Daniel Neel */
public class MainGUI extends JFrame implements ActionListener { 

    /** Bring up the main menu, allowing the player to 
     *  start a game or quit. */
    public static void main(String args[]) {
	System.out.println("Welcome to tic-tac-toe!");
	System.out.println("1) Start");
	System.out.println("2) Quit");

	Cell c = new Cell();
	c.setOwner('X');
	MainGUI game = new MainGUI();
	game.pack();
	game.setVisible(true);
    }

    /** Create a new object of the Main class. */
    public MainGUI() {
        setSize(400, 300);
	setTitle("Developing the First Swing Application");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	addControls();
    }

    /** Add all controls and visual components to the board so that the
     *  player can start playing, quit, etc. */
    public void addControls() {
	JButton quit = new JButton("Quit");
	quit.addActionListener(this);
	add(quit);
    }

    /** Whenever we receive an event, fire off the appropriate actions. */
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals("Quit")) {
	    this.dispose();
	    System.out.println("Goodbye.");
	}
    }
}
