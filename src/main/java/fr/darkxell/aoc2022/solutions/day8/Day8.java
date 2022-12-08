package fr.darkxell.aoc2022.solutions.day8;

import java.util.List;

import fr.darkxell.aoc2022.solutions.DailySolution;

public class Day8 extends DailySolution {

	/**
	 * Array of trees, with the first array index being the horizontal X position
	 * and the second the vertical Y.
	 */
	private int[][] trees;

	public Day8(int day) {
		super(day);
	}

	@Override
	public void preProcess() {
		List<String> text = getInput(day);
		trees = new int[text.get(0).length()][text.size()];
		for (int i = 0; i < text.size(); i++)
			for (int j = 0; j < text.get(i).length(); j++)
				trees[i][j] = Integer.parseInt(text.get(i).charAt(j) + "");
	}

	/**
	 * Predicate that returns true if the tree at the parameter coordinates is
	 * visible from the outside of the grove.
	 */
	private boolean isVisible(int x, int y) {
		int height = trees[x][y];

		boolean visibleleft = true;
		for (int i = 0; i < x; i++)
			if (trees[i][y] >= height) {
				visibleleft = false;
				break;
			}
		if (visibleleft)
			return true;

		boolean visibleright = true;
		for (int i = x + 1; i < trees.length; i++)
			if (trees[i][y] >= height) {
				visibleright = false;
				break;
			}
		if (visibleright)
			return true;

		boolean visibleup = true;
		for (int i = 0; i < y; i++)
			if (trees[x][i] >= height) {
				visibleup = false;
				break;
			}
		if (visibleup)
			return true;

		boolean visibledown = true;
		for (int i = y + 1; i < trees[0].length; i++)
			if (trees[x][i] >= height) {
				visibledown = false;
				break;
			}
		if (visibledown)
			return true;

		return false;
	}

	/**
	 * Computes the scenic score for the tree at the given position in the grove
	 */
	private int scenicScore(int x, int y) {
		int height = trees[x][y];
		int scenicScore = 1;

		int viewcount = 0;
		for (int i = x - 1; i >= 0; i--) {
			viewcount++;
			if (trees[i][y] >= height)
				break;
		}
		scenicScore *= viewcount;

		viewcount = 0;
		for (int i = x + 1; i < trees.length; i++) {
			viewcount++;
			if (trees[i][y] >= height)
				break;
		}
		scenicScore *= viewcount;

		viewcount = 0;
		for (int i = y - 1; i >= 0; i--) {
			viewcount++;
			if (trees[x][i] >= height)
				break;
		}
		scenicScore *= viewcount;

		viewcount = 0;
		for (int i = y + 1; i < trees[0].length; i++) {
			viewcount++;
			if (trees[x][i] >= height)
				break;
		}
		scenicScore *= viewcount;

		return scenicScore;
	}

	@Override
	public String getAnswerPart1() {
		int counter = 0;
		for (int i = 0; i < trees.length; i++)
			for (int j = 0; j < trees[0].length; j++)
				if (isVisible(i, j))
					counter++;
		return "There are " + counter + " trees visible in the grove.";
	}

	@Override
	public String getAnswerPart2() {
		int score = 0;
		for (int i = 0; i < trees.length; i++)
			for (int j = 0; j < trees[0].length; j++) {
				int scorelocale = scenicScore(i, j);
				if (scorelocale >= score)
					score = scorelocale;
			}
		return "The tree with the most scenic view has a score of " + score + ".";
	}

}
