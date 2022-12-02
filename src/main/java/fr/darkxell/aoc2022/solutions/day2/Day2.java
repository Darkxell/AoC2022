package fr.darkxell.aoc2022.solutions.day2;

import java.util.ArrayList;
import java.util.List;

import fr.darkxell.aoc2022.solutions.DailySolution;

public class Day2 extends DailySolution {

	private static final byte WIN = 6;
	private static final byte DRAW = 3;
	private static final byte LOOSE = 0;
	private ArrayList<char[]> data;

	public Day2(int day) {
		super(day);
	}

	@Override
	public void preProcess() {
		List<String> textdata = getInput(day);
		data = new ArrayList<char[]>(textdata.size());
		for (int i = 0; i < textdata.size(); i++) {
			String[] tmp = textdata.get(i).split(" ");
			data.add(new char[] { tmp[0].charAt(0), tmp[1].charAt(0) });
		}
	}

	/**
	 * Computes the outcome of a rock/paper/scissors game and returns the score it
	 * would yield for part 1.
	 */
	private byte computeGame(char opponent, char response) {
		switch (opponent) {
		case 'A': // Opponent rock
			switch (response) {
			case 'X':
				return DRAW;
			case 'Y':
				return WIN;
			case 'Z':
				return LOOSE;
			}
		case 'B':// Opponent paper
			switch (response) {
			case 'X':
				return LOOSE;
			case 'Y':
				return DRAW;
			case 'Z':
				return WIN;
			}
		case 'C': // Opponent scissors
			switch (response) {
			case 'X':
				return WIN;
			case 'Y':
				return LOOSE;
			case 'Z':
				return DRAW;
			}
		}
		throw new IllegalArgumentException();
	}

	/** Returns the guaranteed score of playing something */
	private int playScore(char p) {
		switch (p) {
		case 'A':
			return 1;
		case 'B':
			return 2;
		case 'C':
			return 3;
		case 'X':
			return 1;
		case 'Y':
			return 2;
		case 'Z':
			return 3;
		}
		throw new IllegalArgumentException();
	}

	/** Returns the player score given a game outcome */
	private byte gameScore(char g) {
		switch (g) {
		case 'X':
			return LOOSE;
		case 'Y':
			return DRAW;
		case 'Z':
			return WIN;
		}

		throw new IllegalArgumentException();
	}

	/**
	 * Computes the points the player would get from guaranteed play if following
	 * the strategy guide in part 2.
	 */
	private int p2score(char opponent, char response) {
		switch (opponent) {
		case 'A': // Opponent rock
			switch (response) {
			case 'X': // Loose
				return 3;
			case 'Y': // Draw
				return 1;
			case 'Z': // Win
				return 2;
			}
		case 'B':// Opponent paper
			switch (response) {
			case 'X':
				return 1;
			case 'Y':
				return 2;
			case 'Z':
				return 3;
			}
		case 'C': // Opponent scissors
			switch (response) {
			case 'X':
				return 2;
			case 'Y':
				return 3;
			case 'Z':
				return 1;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public String getAnswerPart1() {
		int score = 0;
		for (int i = 0; i < data.size(); i++)
			score += playScore(data.get(i)[1]) + computeGame(data.get(i)[0], data.get(i)[1]);
		return "If following the strateguy guide as expected, you could expect " + score + " points.";
	}

	@Override
	public String getAnswerPart2() {
		int score = 0;
		for (int i = 0; i < data.size(); i++)
			score += gameScore(data.get(i)[1]) + p2score(data.get(i)[0], data.get(i)[1]);
		return "If following the strateguy guide as explained, you could expect " + score + " points.";
	}

}
