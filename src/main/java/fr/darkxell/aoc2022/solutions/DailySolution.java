package fr.darkxell.aoc2022.solutions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Daily solution abstraction. Contains all the logic to solve a day of AoC,
 * from parsing the input data to outputting the solution in text format.
 */
public abstract class DailySolution {

	/**
	 * Returns a List of String containing the content of the input file, each list
	 * entry being one line, in order. No web requests, files are locally hosted.
	 * AoC inputs are always fairly short and will fit nicely in a String, but may
	 * need processing by the DailySolution implementation in order to be efficient.
	 */
	public List<String> getInput(int day) {
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("day" + day);
			InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(streamReader);
			List<String> toreturn = new ArrayList<String>();
			for (String line; (line = reader.readLine()) != null;)
				toreturn.add(line);
			return toreturn;
		} catch (Exception e) {
			System.err.println("Could not read file for day " + day
					+ ". Processing with empty input data, which may result in errors."
					+ "\nMake sure the input files are present in src/main/resources, are named \"dayX\" and have no file extension.");
			return new ArrayList<String>(0);
		}
	}

	/**
	 * Event called to prepare the day, not counted in total computation times. This
	 * is usually input formatting logic and other utility tied to setting up the
	 * algorithms, not the solutions themselves.
	 */
	public void preProcess() {
	}

	/**Processes and returns the answer to part 1 of the daily problem.*/
	public abstract String getAnswerPart1();

	/**Processes and returns the answer to part 2 of the daily problem. Will always be called after <code>getAnswerPart1()</code>*/
	public abstract String getAnswerPart2();

}
