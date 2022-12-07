package fr.darkxell.aoc2022.solutions.Day7;

import java.util.ArrayList;

public class ElvenFile {

	public ElvenFile(String name, int size, boolean isdir, ElvenFile parent) {
		this.name = name;
		this.size = size;
		this.isDir = isdir;
		this.parent = parent;
	}

	public int size;
	public String name;
	public ElvenFile parent;

	public boolean isDir;
	public ArrayList<ElvenFile> subDirs = new ArrayList<ElvenFile>();

	/**
	 * Smart method that computes the size of this file, even if it is a directory.
	 */
	public int size() {
		if (!isDir)
			return size;
		int toreturn = 0;
		for (ElvenFile sub : subDirs)
			toreturn += sub.size();
		return toreturn;
	}

	/** Returns the subfolder of that name, if it exists. Null otherwise. */
	public ElvenFile getSub(String name) {
		for (ElvenFile sub : subDirs)
			if (sub.name.equals(name))
				return sub;
		return null;
	}

	/**
	 * Adds a file or folder of that name to the directory. Will not override a file
	 * that already exists, and will do nothing if the file is a duplicate.
	 */
	public void addSub(ElvenFile sub) {
		if (getSub(sub.name) != null)
			return;
		subDirs.add(sub);
	}

	/** Returns the sum of the sizes of directories with a size at most 100000. */
	public long part1Recursive() {
		if (!isDir)
			return 0;
		long toreturn = 0;
		int localesize = size();
		if (localesize <= 100000)
			toreturn += localesize;
		for (ElvenFile sub : subDirs)
			toreturn += sub.part1Recursive();
		return toreturn;
	}

	/** Returns the smallest directory size with a size above minsize */
	public int part2Recursive(int smallest, int minsize) {
		int localesize = size();
		if (localesize < minsize || !isDir)
			return Integer.MAX_VALUE;
		if (localesize < smallest)
			smallest = localesize;
		for (ElvenFile sub : subDirs) {
			int subsize = sub.part2Recursive(smallest, minsize);
			if (subsize < smallest)
				smallest = subsize;
		}
		return smallest;
	}

	@Override
	public String toString() {
		if (!isDir)
			return " - " + name + " (file, size=" + size + ")";
		String toreturn = name + " (dir)";
		for (ElvenFile sub : subDirs)
			toreturn += "\n" + sub;
		return toreturn;
	}

}
