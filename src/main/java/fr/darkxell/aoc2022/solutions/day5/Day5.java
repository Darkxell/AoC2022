package fr.darkxell.aoc2022.solutions.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import fr.darkxell.aoc2022.solutions.DailySolution;

public class Day5 extends DailySolution {

	public Day5(int day) {
		super(day);
	}

	/** Data about the moves */
	private ArrayList<int[]> data;

	private ArrayList<Stack<Character>> stacks;
	private ArrayList<Stack<Character>> stacksBackup;

	@Override
	public void preProcess() {
		List<String> tempon = getInput(day);
		boolean dataparsing = false;

		// Initialize the stacks and data array to empty objects
		data = new ArrayList<int[]>(tempon.size());
		stacks = new ArrayList<Stack<Character>>(9);
		stacksBackup = new ArrayList<Stack<Character>>(9);
		for (int i = 0; i < 9; i++) {
			stacks.add(new Stack<Character>());
			stacksBackup.add(new Stack<Character>());
		}

		// Reads the input file
		for (String line : tempon) {
			if (line.length() == 0) {
				dataparsing = true;
				continue;
			}
			if (dataparsing) {
				line = line.replaceAll("\\s+", "");
				String[] numbers = line.split("(move|from|to)");
				int[] num3 = new int[3];
				for (int i = 0; i < num3.length; i++)
					num3[i] = Integer.parseInt(numbers[i + 1]);
				data.add(num3);
			} else {
				for (int i = 0; i < 9; i++) {
					char toadd = line.charAt(1 + i * 4);
					if (toadd >= 'A' && toadd <= 'Z') {
						stacks.get(i).add(0, toadd);
						stacksBackup.get(i).add(0, toadd);
					}
				}
			}
		}
	}

	@Override
	public String getAnswerPart1() {
		for (int[] order : data)
			for (int i = 0; i < order[0]; i++) {
				char crane = stacks.get(order[1] - 1).pop();
				stacks.get(order[2] - 1).push(crane);
			}
		String code = "";
		for (Stack<Character> st : stacks)
			code += st.peek();
		return "The stacks form the following code after prediction : " + code;
	}

	@Override
	public String getAnswerPart2() {
		for (int[] order : data) {
			ArrayList<Character> crane = new ArrayList<Character>();
			for (int i = 0; i < order[0]; i++)
				crane.add(stacksBackup.get(order[1] - 1).pop());
			for (int i = crane.size() - 1; i >= 0; i--)
				stacksBackup.get(order[2] - 1).push(crane.get(i));
		}

		String code = "";
		for (Stack<Character> st : stacksBackup)
			code += st.peek();
		return "The stacks form the following code after moving : " + code;
	}

}
