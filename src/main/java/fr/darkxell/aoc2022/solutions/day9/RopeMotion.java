package fr.darkxell.aoc2022.solutions.day9;

public class RopeMotion {

	public static final char LEFT = 'L';
	public static final char RIGHT = 'R';
	public static final char UP = 'U';
	public static final char DOWN = 'D';

	public char direction;
	public int length;

	public RopeMotion(char direction, int length) {
		this.direction = direction;
		this.length = length;
	}
}
