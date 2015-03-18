/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		for (int i=0; i<nPlayers; i++) {
			display.printMessage(playerNames[i] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
			display.waitForPlayerToClickRoll(1);
			rollDice();
			display.printMessage("Select the dice you wish to re-roll and press \"Roll Again\".");
			selectDice();
			display.printMessage("Last re-roll!. Select the dice you wish to re-roll and press \"Roll Again\".");
			selectDice();
			display.printMessage("Select a category.");
			category = display.waitForPlayerToSelectCategory();
//		int player = 1;
//		int score = 0;
//		display.updateScorecard(category, player, score);
		}
	}
		
private void selectDice() {
		display.waitForPlayerToSelectDice();
		for (int i=0; i < N_DICE; i++) {
			int diceRoll = rgen.nextInt(1, 6);
			if(display.isDieSelected(i) == true) {
				dice[i] = diceRoll;
			}
		display.displayDice(dice);
		}
	}

private void rollDice() {
		for (int i=0; i<N_DICE; i++) {
			int diceRoll = rgen.nextInt(1, 6);
			println(diceRoll);
			dice[i] = diceRoll;
		}
		display.displayDice(dice);
	}

/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	int[] dice = new int[N_DICE];
	private RandomGenerator rgen = new RandomGenerator();
	int category;
}
