package fr.darkxell.aoc2022.solutions.day10;

import java.util.ArrayList;
import java.util.List;

import fr.darkxell.aoc2022.solutions.DailySolution;

public class Day10 extends DailySolution {

	public Day10(int day) {
		super(day);
	}

	public List<int[]> data = new ArrayList<int[]>(200);
	private ArrayList<Integer> signalStrength = new ArrayList<Integer>(200);
	private String screen = "";

	private static int NOOP = 1;
	private static int ADDX = 2;

	@Override
	public void preProcess() {
		List<String> text = getInput(day);
		for (String line : text)
			data.add(line.startsWith("noop") ? new int[] { NOOP, 0 }
					: new int[] { ADDX, Integer.parseInt(line.split(" ")[1]) });
	}

	@Override
	public String getAnswerPart1() {
		int X = 1, cycle = 1;
		signalStrength.add(Integer.MAX_VALUE);

		for (int i = 0; i < data.size(); i++)
			if (data.get(i)[0] == NOOP) {
				signalStrength.add(X * cycle);
				addCurrentToScreen(cycle, X);
				cycle++;
			} else {
				signalStrength.add(X * cycle);
				addCurrentToScreen(cycle, X);
				cycle++;
				signalStrength.add(X * cycle);
				addCurrentToScreen(cycle, X);
				cycle++;
				X += data.get(i)[1];
			}
		int sum = signalStrength.get(20) + signalStrength.get(60) + signalStrength.get(100) + signalStrength.get(140)
				+ signalStrength.get(180) + signalStrength.get(220);
		return "The sum of key signalstrengths for the input is : " + sum;
	}

	private void addCurrentToScreen(int cycle, int X) {
		int crt = (cycle - 1) % 40;
		screen += X >= crt - 1 && X <= crt + 1 ? '#' : '.';
	}

	@Override
	public String getAnswerPart2() {
		return "Screen display :\n" + screen.substring(0, 40) + "\n" + screen.substring(40, 80) + "\n"
				+ screen.substring(80, 120) + "\n" + screen.substring(120, 160) + "\n" + screen.substring(160, 200)
				+ "\n" + screen.substring(200, 240);
	}

}
