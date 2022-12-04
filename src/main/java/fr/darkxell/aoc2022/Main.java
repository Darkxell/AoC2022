package fr.darkxell.aoc2022;

import java.util.ArrayList;

import fr.darkxell.aoc2022.solutions.DailySolution;
import fr.darkxell.aoc2022.solutions.day1.Day1;
import fr.darkxell.aoc2022.solutions.day2.Day2;
import fr.darkxell.aoc2022.solutions.day3.Day3;
import fr.darkxell.aoc2022.solutions.day4.Day4;

public class Main {

	public static void main(String[] args) {

		ArrayList<DailySolution> problems = new ArrayList<DailySolution>(30);
		problems.add(new Day1(1));
		problems.add(new Day2(2));
		problems.add(new Day3(3));
		problems.add(new Day4(4));

		long start = System.currentTimeMillis();
		
		System.out.println("Welcome to advent of code! Pre processing the inputs and closing files asap...");
		
		for (DailySolution d : problems) {
			d.preProcess();
		}
		
		System.out.println("Preprocessing complete. (" + (System.currentTimeMillis() - start) + "ms)");
		
		for (DailySolution d : problems) {	
			System.out.println(d);
			System.out.println(d.getAnswerPart1());
			System.out.println(d.getAnswerPart2());
		}
	}

}
