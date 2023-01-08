package fr.darkxell.aoc2022.solutions.day11;

import java.util.ArrayList;
import java.util.List;

import fr.darkxell.aoc2022.solutions.DailySolution;

public class Day11 extends DailySolution {

	public Day11(int day) {
		super(day);
	}

	private ArrayList<Monkey> monkeys = new ArrayList<Monkey>(10);
	private ArrayList<Monkey> backupdata = new ArrayList<Monkey>(10);

	private static long pgcd = 1;

	@Override
	public void preProcess() {
		List<String> text = getInput(day);
		for (String line : text) {
			line = line.replaceAll(" ", "");
			if (line.equals(""))
				continue;
			if (line.startsWith("Monkey")) {
				monkeys.add(new Monkey());
			} else if (line.startsWith("Startingitems")) {
				Monkey cur = monkeys.get(monkeys.size() - 1);
				String[] itm = line.split(":")[1].split(",");
				for (String s : itm)
					cur.items.add(Long.parseLong(s));
			} else if (line.startsWith("Operation")) {
				Monkey cur = monkeys.get(monkeys.size() - 1);
				cur.operation = line.split("=")[1];
			} else if (line.startsWith("Test")) {
				Monkey cur = monkeys.get(monkeys.size() - 1);
				cur.test = Integer.parseInt(line.split("divisibleby")[1]);
			} else if (line.startsWith("Iftrue:throw")) {
				Monkey cur = monkeys.get(monkeys.size() - 1);
				cur.test_true = Integer.parseInt(line.split("tomonkey")[1]);
			} else if (line.startsWith("Iffalse:throw")) {
				Monkey cur = monkeys.get(monkeys.size() - 1);
				cur.test_false = Integer.parseInt(line.split("tomonkey")[1]);
			}
		}

		// Computes the common denominator and creates a backup copy of the monkeys for
		// part 2
		for (Monkey m : monkeys) {
			backupdata.add((Monkey) m.clone());
			pgcd *= m.test;
		}
	}

	public void processRound() {
		for (int i = 0; i < monkeys.size(); i++) {
			Monkey current = monkeys.get(i);
			for (Long item : current.items) {
				item = current.processOperation(item);
				item /= 3;
				current.inspections++;
				monkeys.get(item % current.test == 0 ? current.test_true : current.test_false).items.add(item);
			}
			current.items.clear();
		}
	}

	public void processRoundP2() {
		for (int i = 0; i < monkeys.size(); i++) {
			Monkey current = monkeys.get(i);
			for (Long item : current.items) {
				item = current.processOperation(item);
				item %= pgcd;
				current.inspections++;
				monkeys.get(item % current.test == 0 ? current.test_true : current.test_false).items.add(item);
			}
			current.items.clear();
		}
	}

	@Override
	public String getAnswerPart1() {
		for (int i = 0; i < 20; i++)
			processRound();

		long top1 = 0, top2 = 0;
		for (Monkey m : monkeys)
			if (m.inspections >= top1) {
				top2 = top1;
				top1 = m.inspections;
			} else if (m.inspections >= top2) {
				top2 = m.inspections;
			}
		return "Most two active monkeys inspected " + top1 + " and " + top2 + " items total. Monkey business levels : "
				+ (top1 * top2);
	}

	@Override
	public String getAnswerPart2() {
		monkeys = backupdata;

		for (int i = 0; i < 10000; i++)
			processRoundP2();

		long top1 = 0, top2 = 0;
		for (Monkey m : monkeys)
			if (m.inspections >= top1) {
				top2 = top1;
				top1 = m.inspections;
			} else if (m.inspections >= top2) {
				top2 = m.inspections;
			}
		return "Monkey business levels after a thousand stressful rounds: " + top1 + " * " + top2 + " = "
				+ (top1 * top2);
		// 17271 * 17156 = 296301276 is still too low???
	}

}
