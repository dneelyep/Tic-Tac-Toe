package com.tic_tac_toe.game;

/** Main.java - Class that starts the game, pulls up 
 *              the menus, allows the player to quit, etc.
 *
 * @author Daniel Neel */
public class Main { 

    /** Bring up the main menu, allowing the player to 
     *  start a game or quit. */
    public static void main(String args[]) {
	System.out.println("Welcome to tic-tac-toe!");
	System.out.println("1) Start");
	System.out.println("2) Quit");

	Cell c = new Cell();
	c.setOwner('X');
	new Main();
    }

    /** Create a new object of the Main class. */
    public Main() {
	startGame();
    }

    /** Start a new game of tic tac toe, using
     *  players x and o. */
    public void startGame() {
	System.out.println("");
	System.out.println("==^=^=^=^=^===============^=^=^=^=^==");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("|-----------+-----------+-----------|");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("|-----------+-----------+-----------|");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("| x x x x x | x x x x x | x x x x x |");
	System.out.println("==v=v=v=v=v===============v=v=v=v=v==");
    }
}
