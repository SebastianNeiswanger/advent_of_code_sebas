package com.mycompany.day11.solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author  Sebastian Neiswanger
 * @date    12/16/2022
 * @purpose solution to Advent of Code 2022 day 11 in Java 
 */
public class Day11Solution {
    public static void main(String[] args) throws FileNotFoundException {
        // Turns input into a scanner that can be walked through
        Scanner inputFile = new Scanner(new File("input.txt"));
        
        // Vector to hold Monkeys
        Vector<Monkey> part1Monkeys = new Vector<Monkey>();
        Vector<Monkey> part2Monkeys = new Vector<Monkey>();
        
        // Create the Monkey vectors
        while (inputFile.hasNextLine()) { // First line does not matter
            inputFile.nextLine();
            String startingItems = inputFile.nextLine().replaceAll("\\s", "");
            String operation = inputFile.nextLine().replaceAll("\\s", "");
            String divisibleString = inputFile.nextLine().replaceAll("\\s", "");
            String trueString = inputFile.nextLine().replaceAll("\\s", "");
            String falseString = inputFile.nextLine().replaceAll("\\s", "");
            part1Monkeys.add(new Monkey(startingItems, operation, divisibleString, trueString, falseString));
            part2Monkeys.add(new Monkey(startingItems, operation, divisibleString, trueString, falseString));
            // Skip blank line
            if (inputFile.hasNextLine()) { inputFile.nextLine(); }     
        }

        // Set the modulo that will keep the items lower in int size
        int mod = 1;
        for (Monkey monkey : part2Monkeys) {
            mod *= monkey.getDivisibleBy();
        }
        for (Monkey monkey : part2Monkeys) {
            monkey.setModulo(mod);
        }
        
        // Part 1: Run for 20 rounds and then find the two monkeys
        // who have the highest number of inspected items
        for (int round = 0; round < 20; round++) {
            for (Monkey monk : part1Monkeys) {
                monk.inspectItems(part1Monkeys, 3);
            }
        }

        int firstMonkeyInspection = 0;
        int secondMonkeyInspection = 0;

        for (Monkey monk : part1Monkeys) {
            if (monk.totalInspectedItems() > firstMonkeyInspection) {
                secondMonkeyInspection = firstMonkeyInspection;
                firstMonkeyInspection = monk.totalInspectedItems();
            } else if (monk.totalInspectedItems() > secondMonkeyInspection) {
                secondMonkeyInspection = monk.totalInspectedItems();
            }
        }
        BigInteger monkeyBusiness = new BigInteger(String.valueOf(firstMonkeyInspection * secondMonkeyInspection));

        System.out.println("Solution 1: " + monkeyBusiness + " monkey business");

        // Part 2: Run for 10,000 rounds and then find the two monkeys
        // who have the highest number of inspected items
        for (int round = 0; round < 10_000; round++) {
            for (Monkey monk : part2Monkeys) {
                monk.inspectItems(part2Monkeys, 0);
            }
        }
    
        firstMonkeyInspection = 0;
        secondMonkeyInspection = 0;
    
        for (Monkey monk : part2Monkeys) {
            if (monk.totalInspectedItems() > firstMonkeyInspection) {
                secondMonkeyInspection = firstMonkeyInspection;
                firstMonkeyInspection = monk.totalInspectedItems();
            } else if (monk.totalInspectedItems() > secondMonkeyInspection) {
                secondMonkeyInspection = monk.totalInspectedItems();
            }
        }
        monkeyBusiness = BigInteger.valueOf(firstMonkeyInspection);
        monkeyBusiness = monkeyBusiness.multiply(BigInteger.valueOf(secondMonkeyInspection));
    
        System.out.println("Solution 2: " + monkeyBusiness + " monkey business");
    }
}
