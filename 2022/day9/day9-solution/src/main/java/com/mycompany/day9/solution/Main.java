package com.mycompany.day9.solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author  Sebastian Neiswanger
 * @date    12/14/2022
 * @purpose solution to Advent of Code 2022 day 9 in Java 
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Turns input into a scanner that can be walked through
        Scanner inputFile = new Scanner(new File("input.txt"));

        // Create a new rope grid
        RopeGrid part1 = new RopeGrid(1000, 1);
        RopeGrid part2 = new RopeGrid(1000, 9);

        // Walk through the input moving the rope with every step
        while(inputFile.hasNext()) {
            String direction = inputFile.next();
            int distance = inputFile.nextInt();
            part1.moveHead(direction, distance);
            part2.moveHead(direction, distance);
        }

        // Print answers
        System.out.println("Solution 1: " + part1.visitedTailSpaces() + " visited spaces\n");
        System.out.println("Solution 2: " + part2.visitedTailSpaces() + " visited spaces\n");
    }
}
