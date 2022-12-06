package fr.darkxell.aoc2022.solutions.day6;

import java.util.Arrays;
import java.util.List;

import fr.darkxell.aoc2022.solutions.DailySolution;

public class Day6 extends DailySolution {

	private String data;

	public Day6(int day) {
		super(day);
	}

	public void preProcess() {
		List<String> filedata = getInput(day);
		data = filedata.get(0);
	}

	private int lastPart1Found = 14;

	@Override
	public String getAnswerPart1() {
		for (int i = 3; i < data.length(); i++)
			if (data.charAt(i) != data.charAt(i - 1) && data.charAt(i - 1) != data.charAt(i - 2)
					&& data.charAt(i - 2) != data.charAt(i - 3) && data.charAt(i) != data.charAt(i - 2)
					&& data.charAt(i - 1) != data.charAt(i - 3) && data.charAt(i) != data.charAt(i - 3)) {
				lastPart1Found = i;
				return "First start of packet maker is detected at position : " + (i + 1);
			}
		return "No packet marker found...";
	}

	// 3496 too high

	@Override
	public String getAnswerPart2() {
		for (int i = lastPart1Found; i < data.length(); i++) {
			char[] chars = data.substring(i - 14, i).toCharArray();
			Arrays.sort(chars);
			if (chars[0] != chars[1] && chars[1] != chars[2] && chars[2] != chars[3] && chars[3] != chars[4]
					&& chars[4] != chars[5] && chars[5] != chars[6] && chars[6] != chars[7] && chars[7] != chars[8]
					&& chars[8] != chars[9] && chars[9] != chars[10] && chars[10] != chars[11] && chars[11] != chars[12]
					&& chars[12] != chars[13])
				return "First start of message marker is detected at position : " + i;
		}
		return "No packet maker found...";
	}

}
