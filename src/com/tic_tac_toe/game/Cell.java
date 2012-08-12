package com.tic_tac_toe.game;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/** Cell.java - Class that represents a cell on the tic tac toe board.
 *              Contains an X, an O, or nothing, depending on which player
 *              has it marked.
 *
 * @author Daniel Neel */
public class Cell extends JButton {
    private char owner;

    /** Cell constructor. Set the image for this Cell, and
     *  set this Cell to have no owner. */
    public Cell(ImageIcon image) {
	super(image);
	owner = '-';
    }

    /** Change the owner of this cell to player X, player O, 
     *  or make it un-owned. In the process, change regular and
     *  rollover images associated with this Cell. */
    public void setOwner(char player) {
	if (player == 'X') {
	    owner = 'X';
	    setIcon(new ImageIcon("../images/X.png"));
	    setRolloverIcon(new ImageIcon("../images/XRollover.png"));
	}
	else if (player == 'O') {
	    owner = 'O';
	    setIcon(new ImageIcon("../images/O.png"));
	    setRolloverIcon(new ImageIcon("../images/ORollover.png"));
	}
	else if (player == '-') {
	    owner = '-';
	    setIcon(new ImageIcon("../images/Empty.png"));
	    setRolloverIcon(new ImageIcon("../images/EmptyRollover.png"));
	}
	else {
	    System.out.println("Error: Tried to set owner to an invalid player.");
	}
    }

    /** Return a char (X, O, or -) representing the owner 
     *  of this Cell. */
    public char getOwner() {
	return owner;
    }

    /** Return true if this Cell is owned by X or O.
     *  If not, return false.*/
    public boolean isOwned() {
	if (owner == 'X' || owner == 'O')
	    return true;
	else
	    return false;
    }
}
