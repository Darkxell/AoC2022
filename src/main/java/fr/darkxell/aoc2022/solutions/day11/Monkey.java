package fr.darkxell.aoc2022.solutions.day11;

import java.util.ArrayList;

public class Monkey implements Cloneable {

	public int inspections = 0;

	public ArrayList<Long> items = new ArrayList<Long>(10);

	public String operation = "";

	public int test, test_true, test_false;

	public long processOperation(long old) {
		if (operation.equals("old*old"))
			return old * old;
		if (operation.startsWith("old*"))
			return old * Integer.parseInt(operation.split("\\*")[1]);
		if (operation.startsWith("old+"))
			return old + Integer.parseInt(operation.split("\\+")[1]);
		System.err.println("Day 11 Monkey operation has failed for string : " + operation
				+ "\nRest of the day compute might be wrong...");
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Monkey\nItems: ");
		for (Long i : items)
			sb.append(i + ",");
		sb.deleteCharAt(sb.length() - 1);
		sb.append("\nOperation: new = " + operation + "\nTest: divisible by " + test + "\nIf true: throw to monkey "
				+ test_true + "\nIf false: throw to monkey " + test_false);
		return sb.toString();
	}

	@Override
	protected Object clone() {
		Monkey toreturn = new Monkey();
		toreturn.inspections = inspections;
		for (Long i : items)
			toreturn.items.add(i);
		toreturn.operation = operation;
		toreturn.test = test;
		toreturn.test_true = test_true;
		toreturn.test_false = test_false;
		return toreturn;
	}

}
