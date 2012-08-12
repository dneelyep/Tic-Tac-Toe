package com.tic_tac_toe.game;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/** Cell.java - Class that represents a cell on the tic tac toe board.
 *              Contains an X, an O, or nothing, depending on which player
 *              has it marked.
 *
 * @author Daniel Neel */
public class Cell extends JButton {
    private String owner;

    /** Cell constructor. Set the image for this Cell, and
     *  set this Cell to have no owner. */
    public Cell(ImageIcon image) {
	super(image);
	owner = "-";
    }

    /** Change the owner of this cell to player X, player O, 
     *  or make it un-owned. */
    public void setOwner(char player) {
	if (player == 'X')
	    owner = "X";
	else if (player == 'O')
	    owner = "O";
	else if (player == '-')
	    owner = "-";
	else
	    System.out.println("Error: Tried to set owner to an invalid player.");
    }
}
