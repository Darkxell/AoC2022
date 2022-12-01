package fr.darkxell.aoc2022;

import fr.darkxell.aoc2022.solutions.DailySolution;
import fr.darkxell.aoc2022.solutions.day1.Day1;

public class Main {

	public static void main(String[] args) {

		DailySolution d1 = new Day1();
		d1.preProcess();

		System.out.println("Day 1 part 1 : " + d1.getAnswerPart1());
		System.out.println("Day 1 part 2 : " + d1.getAnswerPart2());
	}

}
