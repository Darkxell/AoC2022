package fr.darkxell.aoc2022.solutions.day3;

import java.util.ArrayList;

import fr.darkxell.aoc2022.solutions.DailySolution;

public class Day3 extends DailySolution {

	private ArrayList<String> data;

	public Day3(int day) {
		super(day);
	}

	@Override
	public void preProcess() {
		data = (ArrayList<String>) getInput(day);
	}

	/**
	 * If the two strings have a letter in common, the first occurrence of it in
	 * string a will be returned.
	 * 
	 * @return The letter in common, ' ' otherwise.
	 */
	private char sameLetter(String a, String b) {
		for (int i = 0; i < a.length(); i++)
			for (int j = 0; j < b.length(); j++) {
				if (a.charAt(i) == b.charAt(j))
					return a.charAt(i);
			}
		return ' ';
	}

	/**
	 * If the three strings have a letter in common, the first occurrence of it in
	 * string a will be returned.
	 * 
	 * @return The letter in common, ' ' otherwise.
	 */
	private char sameLetter(String a, String b, String c) {
		for (int i = 0; i < a.length(); i++)
			for (int j = 0; j < b.length(); j++)
				for (int k = 0; k < c.length(); k++) {
					if (a.charAt(i) == b.charAt(j) && a.charAt(i) == c.charAt(k))
						return a.charAt(i);
				}
		return ' ';
	}

	private int getPriority(char item) {
		if (item >= 'A' && item <= 'Z')
			return item - 38;
		return item - 96;
	}

	@Override
	public String getAnswerPart1() {
		int sum = 0;
		for (int i = 0; i < data.size(); i++) {
			String p1 = data.get(i).substring(0, data.get(i).length() / 2);
			String p2 = data.get(i).substring(data.get(i).length() / 2);
			sum += getPriority(sameLetter(p1, p2));
		}
		return "Sum of priorities : " + sum;
	}

	@Override
	public String getAnswerPart2() {
		int sum = 0;
		for (int i = 0; i < data.size(); i += 3)
			sum += getPriority(sameLetter(data.get(i), data.get(i + 1), data.get(i + 2)));
		return "Sum of badge priorities : " + sum;
	}

}
