package fr.darkxell.aoc2022;

import java.util.ArrayList;

import fr.darkxell.aoc2022.solutions.DailySolution;
import fr.darkxell.aoc2022.solutions.day1.Day1;
import fr.darkxell.aoc2022.solutions.day2.Day2;
import fr.darkxell.aoc2022.solutions.day3.Day3;

public class Main {

	public static void main(String[] args) {

		ArrayList<DailySolution> problems = new ArrayList<DailySolution>(30);
		problems.add(new Day1(1));
		problems.add(new Day2(2));
		problems.add(new Day3(3));
		
		for (DailySolution d : problems) {
			d.preProcess();
			System.out.println(d);
			System.out.println(d.getAnswerPart1());
			System.out.println(d.getAnswerPart2());
		}
	}

}
