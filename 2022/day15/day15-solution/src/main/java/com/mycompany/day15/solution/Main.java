package com.mycompany.day15.solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author  Sebastian Neiswanger
 * @date    12/23/2022
 * @purpose solution to Advent of Code 2022 day 15 in Java 
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Turns input into a scanner that can be walked through
        Scanner inputFile = new Scanner(new File("input.txt"));

        // Vector to hold all Sensors
        Vector<Sensor> sensors = new Vector<Sensor>();

        // Walk through the input file
        while(inputFile.hasNext()) {
            sensors.add(new Sensor(inputFile.nextLine()));
        }
    }
}