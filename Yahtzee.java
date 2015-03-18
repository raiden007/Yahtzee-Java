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
					category = display.waitForPlayerToSelectCategory();
					updateScoreCard(i);
					checkBonus(i);
				}
				score = 0;
			}
		
		display.printMessage("GAME OVER");	
	}
		

private void checkBonus(int i) {
		println("nPlayers: " + nPlayers);
		display.updateScorecard(7, i+1, upperScore);
		if (upperScore > 63) {
			upperBonus = 35;
			display.updateScorecard(8, i+1, upperBonus);
		}
		display.updateScorecard(16, i+1, lowerScore);
		totalScore = upperScore + lowerScore + upperBonus;
		display.updateScorecard(17, i+1, totalScore);
	}

private void updateScoreCard(int i) {
		categoryIsValid = YahtzeeMagicStub.checkCategory(dice, category);
		if (categoryIsValid == false) {
			score = 0;
			display.updateScorecard(category, i+1, score);
			categoriesTotalNumber--;
			println(categoriesTotalNumber);
			lowerScore += score;
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
			} else if (category == 2) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 2) {
						score = score + 2;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore += score;
			} else if (category == 3) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 3) {
						score = score + 3;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore += score;
			} else if (category == 4) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 4) {
						score = score + 4;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore += score;
			} else if (category == 5) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 5) {
						score = score + 5;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore += score;
			} else if (category == 6) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 6) {
						score = score + 6;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore += score;
			} else if (category == 9) {
				score = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore += score;
			} else if (category == 10) {
				score = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore += score;
			} else if (category == 11) {
				score = 25;
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore += score;
			} else if (category == 12) {
				score = 30;
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore += score;
			} else if (category == 13) {
				score = 40;
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore += score;
			} else if (category == 14) {
				score = 50;
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore += score;
			} else if (category == 15) {
				score = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore += score;
			}
		}
	}

private boolean checkThreeOfAKind() {
	// TODO Auto-generated method stub
	return false;
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
	int upperBonus;
}
