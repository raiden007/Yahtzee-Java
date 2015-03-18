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
		score = 0;
		upperScore = 0;
		totalScore = 0;
		lowerScore = 0;
		categoriesTotalNumber = 13 * nPlayers;
			while (categoriesTotalNumber > 0) {
				for (int i=0; i<nPlayers; i++) {
					display.printMessage(playerNames[i] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
					display.waitForPlayerToClickRoll(1);
					rollDice();
					display.printMessage("Select the dice you wish to re-roll and press \"Roll Again\".");
					selectDice();
					display.printMessage("Last chance!!!. Select the dice you wish to re-roll and press \"Roll Again\".");
					selectDice();
					display.printMessage("Select a category.");
					println(dice[4]);
					category = display.waitForPlayerToSelectCategory();
					updateScoreCard(i);
					checkBonus(i);
				}
			println ("Categories Left: " + categoriesTotalNumber);
			}

	}
		

private void checkBonus(int i) {
		
		display.updateScorecard(7, i+1, upperScore);
		if (upperScore > 63) {
			int upperBonus = upperScore + 35;
			display.updateScorecard(8, i+1, upperBonus);
			totalScore += upperBonus;
		}
		display.updateScorecard(16, i+1, lowerScore);
		display.updateScorecard(17, i+1, score);
	}

private void updateScoreCard(int i) {
		categoryIsValid = YahtzeeMagicStub.checkCategory(dice, category);
		println(categoryIsValid);
		while (categoryIsValid == false) {
			display.updateScorecard(category, i+1, 0);
		}
		if (categoryIsValid == true) {
			score = 0;
			if (category == 1) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 1) {
						score = score + 1;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore += score;
				totalScore += score;
			} else if (category == 2) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 2) {
						score = score + 2;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore += score;
				totalScore += score;
			} else if (category == 3) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 3) {
						score = score + 3;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore += score;
				totalScore += score;
			} else if (category == 4) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 4) {
						score = score + 4;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore += score;
				totalScore += score;
			} else if (category == 5) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 5) {
						score = score + 5;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore += score;
				totalScore += score;
			} else if (category == 6) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 6) {
						score = score + 6;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore += score;
				totalScore += score;
			}
		}
	}

private void selectDice() {
		display.waitForPlayerToSelectDice();
		for (int i=0; i < N_DICE; i++) {
			if(display.isDieSelected(i) == true) {
				int diceRoll = rgen.nextInt(1, 6);
				dice[i] = diceRoll;
			}
		}
		display.displayDice(dice);
	}

private void rollDice() {
		for (int i=0; i<N_DICE; i++) {
			int diceRoll = rgen.nextInt(1, 6);
			dice[i] = diceRoll;
//			println(dice[i]);
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
	int score;
	int categoriesTotalNumber;
	boolean categoryIsValid;
	int upperScore;
	int totalScore;
	int lowerScore;
}
