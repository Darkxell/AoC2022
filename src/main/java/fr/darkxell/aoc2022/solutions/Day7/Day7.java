package fr.darkxell.aoc2022.solutions.Day7;

import java.util.ArrayList;

import fr.darkxell.aoc2022.solutions.DailySolution;

public class Day7 extends DailySolution {

	private ArrayList<String> commands;

	public Day7(int day) {
		super(day);
	}

	public void preProcess() {
		commands = (ArrayList<String>) getInput(day);
	}

	public static final byte LINE_COMMAND = 0;
	public static final byte LINE_DIR = 1;
	public static final byte LINE_FILEINFO = 2;

	/**
	 * Utility method that returns the type of command/info held in this console
	 * line
	 */
	private byte getLineType(String line) {
		if (line.charAt(0) == '$')
			return LINE_COMMAND;
		if (line.charAt(0) >= '1' && line.charAt(0) <= '9')
			return LINE_FILEINFO;
		return LINE_DIR;
	}

	private ElvenFile Root = new ElvenFile("/", 0, true, null);
	private ElvenFile CurrentLocation = Root;

	@Override
	public String getAnswerPart1() {
		for (String line : commands) {
			// Edge case for the very first line, this makes no sense and should be ignored.
			// For this, we will assume no directory is named "/" or "..", that would be a
			// nightmare.
			if (line.equals("$ cd /"))
				continue;
			String[] tmp = line.split(" ");
			switch (getLineType(line)) {
			case LINE_COMMAND: // For command lines, just read the CD and move accordingly
				if (tmp[1].equals("cd"))
					CurrentLocation = tmp[2].equals("..") ? CurrentLocation.parent : CurrentLocation.getSub(tmp[2]);
				break;
			case LINE_FILEINFO: // For file infos, we add the file to the current directory (once)
				CurrentLocation.addSub(new ElvenFile(tmp[1], Integer.parseInt(tmp[0]), false, CurrentLocation));
				break;
			case LINE_DIR: // For Dir info, we add the dir to the current directory
				CurrentLocation.addSub(new ElvenFile(tmp[1], 0, true, CurrentLocation));
				break;
			}
		}
		// Now that we build the hierarchy tree, it's trivial to answer the question by
		// browsing it
		CurrentLocation = Root;
		return "Sum of total sizes of directories below 100000 : " + CurrentLocation.part1Recursive();
	}

	@Override
	public String getAnswerPart2() {
		return null;
	}

}
