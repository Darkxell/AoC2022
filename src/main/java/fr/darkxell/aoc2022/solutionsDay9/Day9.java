package fr.darkxell.aoc2022.solutionsDay9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.darkxell.aoc2022.solutions.DailySolution;

public class Day9 extends DailySolution {

	private ArrayList<RopeMotion> data = new ArrayList<RopeMotion>(100);
	private Set<Integer> tailpos = new HashSet<Integer>();

	private ArrayList<Pos> rope = new ArrayList<Pos>(11);

	public Day9(int day) {
		super(day);
	}

	@Override
	public void preProcess() {
		List<String> text = getInput(day);
		for (String line : text)
			data.add(new RopeMotion(line.charAt(0), Integer.parseInt(line.split(" ")[1])));
	}

	/**
	 * Predicate that returns true if the points x,y and a,b are directly adjacent.
	 */
	private boolean near(int x, int y, int a, int b) {
		return x <= a + 1 && x >= a - 1 && y <= b + 1 && y >= b - 1;
	}

	@Override
	public String getAnswerPart1() {
		int HEAD_X = 0, HEAD_Y = 0, TAIL_X = 0, TAIL_Y = 0;
		tailpos.add(0);
		for (RopeMotion rm : data) {
			// Move the head by the full length of the motion
			HEAD_X += rm.direction == RopeMotion.LEFT ? -rm.length : rm.direction == RopeMotion.RIGHT ? rm.length : 0;
			HEAD_Y += rm.direction == RopeMotion.UP ? rm.length : rm.direction == RopeMotion.DOWN ? -rm.length : 0;
			// Move the tail to stick to the head
			while (!near(HEAD_X, HEAD_Y, TAIL_X, TAIL_Y)) {
				switch (rm.direction) {
				case RopeMotion.LEFT:
					TAIL_X -= 2;
				case RopeMotion.RIGHT:
					TAIL_X++;
					if (HEAD_Y != TAIL_Y)
						TAIL_Y = HEAD_Y;
					break;
				case RopeMotion.UP:
					TAIL_Y += 2;
				case RopeMotion.DOWN:
					TAIL_Y--;
					if (HEAD_X != TAIL_X)
						TAIL_X = HEAD_X;
					break;
				}
				// Put the tail location in the pathing map
				// This stores 2 shorts in an int. Don't question it.
				tailpos.add((TAIL_X & 0xFFFF) | ((TAIL_Y & 0xFFFF) << 16));
			}
		}
		return "The tail visited " + tailpos.size() + " locations";
	}

	@Override
	public String getAnswerPart2() {
		int ropelength = 10;
		for (int i = 0; i < ropelength; i++)
			rope.add(new Pos(0, 0));
		tailpos = new HashSet<Integer>();
		tailpos.add(0);
		int[][] lookup_cross = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } },
				lookup_diag = new int[][] { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

		for (RopeMotion rm : data)
			for (int k = 0; k < rm.length; k++) {

				// Move the head, but only once
				rope.get(0).x += rm.direction == RopeMotion.LEFT ? -1 : rm.direction == RopeMotion.RIGHT ? 1 : 0;
				rope.get(0).y += rm.direction == RopeMotion.UP ? 1 : rm.direction == RopeMotion.DOWN ? -1 : 0;

				// Move the segment one step around itself, until it's near the previous segment
				for (int i = 1; i < ropelength; i++) {
					if (!near(rope.get(i - 1).x, rope.get(i - 1).y, rope.get(i).x, rope.get(i).y)) {
						int[][] lookup = rope.get(i - 1).x == rope.get(i).x || rope.get(i - 1).y == rope.get(i).y
								? lookup_cross
								: lookup_diag;
						for (int a = 0; a < lookup.length; a++)
							if (near(rope.get(i - 1).x, rope.get(i - 1).y, rope.get(i).x + lookup[a][0],
									rope.get(i).y + lookup[a][1])) {
								rope.get(i).x += lookup[a][0];
								rope.get(i).y += lookup[a][1];
								break;
							}
					} else
						break;
				}
				// Put the tail location in the pathing map
				tailpos.add((rope.get(ropelength - 1).x & 0xFFFF) | ((rope.get(ropelength - 1).y & 0xFFFF) << 16));
			}
		return "The " + ropelength + " length tail visited " + tailpos.size() + " locations";
	}

}
