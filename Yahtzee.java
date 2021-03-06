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
		categoriesTotalNumber = 13 * nPlayers;
			while (categoriesTotalNumber > 0) {
				for (int i=0; i<nPlayers; i++) {
					playerRollsDice(i);
					playerSelectsCategory();
					updateScoreCard(i);
					checkBonus(i);
				}
			}
		
		display.printMessage("GAME OVER");	
	}
		

private void playerSelectsCategory() {
	display.printMessage("Select a category.");
	category = display.waitForPlayerToSelectCategory();
		
}

private void playerRollsDice(int i) {
	display.printMessage(playerNames[i] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
	display.waitForPlayerToClickRoll(i+1);
	rollDice();
	display.printMessage("Select the dice you wish to re-roll and press \"Roll Again\".");
	selectDice();
	display.printMessage("Last chance!!!. Select the dice you wish to re-roll and press \"Roll Again\".");
	selectDice();
}

private void checkBonus(int i) {
		println("nPlayers: " + nPlayers);
		display.updateScorecard(7, i+1, upperScore[i]);
		if (upperScore[i] > 63) {
			upperBonus[i] = 35;
			display.updateScorecard(8, i+1, upperBonus[i]);
		}
		display.updateScorecard(16, i+1, lowerScore[i]);
		totalScore[i] = upperScore[i] + lowerScore[i] + upperBonus[i];
		display.updateScorecard(17, i+1, totalScore[i]);
	}

private void updateScoreCard(int i) {
		categoryIsValid = YahtzeeMagicStub.checkCategory(dice, category);
//		categoryIsValid = checkCategory(dice, category);
		if (categoryIsValid == false) {
			score = 0;
			display.updateScorecard(category, i+1, score);
			categoriesTotalNumber--;
			println(categoriesTotalNumber);
			lowerScore[i] += score;
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
				upperScore[i] += score;
			} else if (category == 2) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 2) {
						score = score + 2;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore[i] += score;
			} else if (category == 3) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 3) {
						score = score + 3;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore[i] += score;
			} else if (category == 4) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 4) {
						score = score + 4;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore[i] += score;
			} else if (category == 5) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 5) {
						score = score + 5;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore[i] += score;
			} else if (category == 6) {
				for (int j=0; j < N_DICE; j++) {
					if (dice[j] == 6) {
						score = score + 6;
					}
				}
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				upperScore[i] += score;
			} else if (category == 9) {
				score = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore[i] += score;
			} else if (category == 10) {
				score = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore[i] += score;
			} else if (category == 11) {
				score = 25;
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore[i] += score;
			} else if (category == 12) {
				score = 30;
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore[i] += score;
			} else if (category == 13) {
				score = 40;
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore[i] += score;
			} else if (category == 14) {
				score = 50;
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore[i] += score;
			} else if (category == 15) {
				score = dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
				display.updateScorecard(category, i+1, score);
				categoriesTotalNumber--;
				lowerScore[i] += score;
			}
		}
	}

private boolean checkCategory(int[] dice, int category) {
	if (category == 1 || category == 2 || category == 3 || category == 4 || category == 5 || category == 6 || category == 15) {
		return true;
	} else if (category == 9) {
		boolean valid = checkThreeOfAKind(dice);
		// TODO Three of a kind
	} else if (category == 10) {
		// TODO 4 of a kind
	} else if (category == 11) {
		// TODO full house
	} else if (category == 12) {
		// TODO small straight
	} else if (category == 13) {
		// TODO large straight
	} else if (category == 14) {
		boolean valid = checkYahtzee(dice);
		if (valid == true) {
			return true;
		} else {
			return false;
		}
	}
	return false;
}

private boolean checkThreeOfAKind(int[] dice2) {
	if (true) {
		return true;
	}
	// TODO Auto-generated method stub
	return false;
}

private boolean checkYahtzee(int[] dice2) {
	if (dice[0] == dice[1] && dice[0]== dice [2] && dice[0 ]== dice [3] && dice[0 ]== dice[4]) {
		return true;
	} else {
		return false;
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
	int[] upperScore = new int [5];
	int[] totalScore = new int [5];
	int[] lowerScore = new int [5];
	int[] upperBonus = new int [5];
}
