package fr.darkxell.aoc2022.solutions.day4;

import java.util.ArrayList;
import java.util.List;

import fr.darkxell.aoc2022.solutions.DailySolution;

public class Day4 extends DailySolution {

	private ArrayList<int[]> data;

	public Day4(int day) {
		super(day);
	}

	@Override
	public void preProcess() {
		List<String> tempon = getInput(day);
		data = new ArrayList<int[]>(tempon.size());
		for (String line : tempon)
			if (line.length() > 0) {
				String[] cut = line.split("(-|,)");
				data.add(new int[] { Integer.parseInt(cut[0]), Integer.parseInt(cut[1]), Integer.parseInt(cut[2]),
						Integer.parseInt(cut[3]) });
			}
	}

	/** Predicate that returns true if X is within the range A to B. */
	private boolean inRange(int x, int a, int b) {
		return x >= a && x <= b;
	}

	@Override
	public String getAnswerPart1() {
		int count = 0;
		for (int[] is : data)
			if ((is[0] <= is[2] && is[1] >= is[3]) || (is[0] >= is[2] && is[1] <= is[3]))
				count++;
		return "Assignement range fully contain the other in " + count + " pairs.";
	}

	@Override
	public String getAnswerPart2() {
		int count = 0;
		for (int[] is : data)
			if (inRange(is[0], is[2], is[3]) || inRange(is[1], is[2], is[3]) || inRange(is[2], is[0], is[1])
					|| inRange(is[3], is[0], is[1]))
				count++;
		return "Assignement ranges somewhat overlaps in " + count + " pairs.";
	}

}
