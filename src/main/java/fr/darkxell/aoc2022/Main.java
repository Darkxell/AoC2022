package fr.darkxell.aoc2022;

import java.util.ArrayList;

import fr.darkxell.aoc2022.solutions.DailySolution;
import fr.darkxell.aoc2022.solutions.Day7.Day7;
import fr.darkxell.aoc2022.solutions.day1.Day1;
import fr.darkxell.aoc2022.solutions.day2.Day2;
import fr.darkxell.aoc2022.solutions.day3.Day3;
import fr.darkxell.aoc2022.solutions.day4.Day4;
import fr.darkxell.aoc2022.solutions.day5.Day5;
import fr.darkxell.aoc2022.solutions.day6.Day6;
import fr.darkxell.aoc2022.solutions.day8.Day8;
import fr.darkxell.aoc2022.solutionsDay9.Day9;

public class Main {

	/**
	 * Total computation time for AoC problems in this program instance. This number
	 * is increased by each DailySolution when they finish.
	 */
	public static long TOTALCOMPUTATIONTIME = 0l;

	public static void main(String[] args) {

		ArrayList<DailySolution> problems = new ArrayList<DailySolution>(30);
		problems.add(new Day1(1));
		problems.add(new Day2(2));
		problems.add(new Day3(3));
		problems.add(new Day4(4));
		problems.add(new Day5(5));
		problems.add(new Day6(6));
		problems.add(new Day7(7));
		problems.add(new Day8(8));
		problems.add(new Day9(9));

		long start = System.currentTimeMillis();

		System.out.println("Welcome to advent of code! Pre processing the inputs and closing files asap...");

		for (DailySolution d : problems)
			d.preProcess();

		long processingtime = System.currentTimeMillis() - start;
		System.out.println("Preprocessing complete. (" + processingtime + "ms)");

		for (DailySolution d : problems)
			d.computeAndPrint();

		System.out.println("Total computation times : " + TOTALCOMPUTATIONTIME + "ms ("
				+ (TOTALCOMPUTATIONTIME + processingtime) + "ms including file pre processing)");
	}

}
