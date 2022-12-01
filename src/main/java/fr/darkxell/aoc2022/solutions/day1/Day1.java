package fr.darkxell.aoc2022.solutions.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.darkxell.aoc2022.solutions.DailySolution;

public class Day1 extends DailySolution {

	/**
	 * Calories count of each entry in the input data. -1 is a line separator,
	 * meaning the next entries are owned by a different deer.
	 */
	private ArrayList<Integer> data;

	private ArrayList<Integer> processor = new ArrayList<Integer>();

	@Override
	public void preProcess() {
		List<String> textdata = getInput(1);
		data = new ArrayList<Integer>(textdata.size());
		for (int i = 0; i < textdata.size(); i++)
			data.add(textdata.get(i).length() == 0 ? -1 : Integer.parseInt(textdata.get(i)));
	}

	@Override
	public String getAnswerPart1() {
		int current = 0;
		for (int i = 0; i < data.size(); i++)
			if (data.get(i) == -1) {
				processor.add(current);
				current = 0;
			} else {
				current += data.get(i);
			}
		Collections.sort(processor);
		return "The elf carrying the most calories is carrying " + processor.get(processor.size() - 1) + " cal.";
	}

	@Override
	public String getAnswerPart2() {
		return "The three elf carrying the most have " + (processor.get(processor.size() - 1)
				+ processor.get(processor.size() - 2) + processor.get(processor.size() - 3)) + " cal.";
	}

}
