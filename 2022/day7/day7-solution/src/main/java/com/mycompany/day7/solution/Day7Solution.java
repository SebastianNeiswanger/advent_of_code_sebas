package com.mycompany.day7.solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author  Sebastian Neiswanger
 * @date    12/10/2022
 * @purpose solution to Advent of Code 2022 day 7 in Java 
 */
public class Day7Solution {

    public static void main(String[] args) throws FileNotFoundException {
        // Turns input into a scanner that can be walked through
        Scanner inputFile = new Scanner(new File("input.txt"));

        int FILE_SYSTEM_TOTAL_SPACE = 70_000_000;
        int REQUIRED_SPACE_FOR_UPDATE = 30_000_000;

        // Mutable variables
        Vector<directory> allDirectories = new Vector<directory>();
        directory currentDirectory = new directory(inputFile.nextLine().replaceAll("\n", "").substring(5));
        allDirectories.add(currentDirectory);
        String line = "";

        // Iterate through the input file
        while(inputFile.hasNext()) {
            line = inputFile.nextLine().replaceAll("\n", "");
            if (line.charAt(0) == '$') {
                // If the first character is '$' then it is calling a command
                if (line.contains("cd")) {
                    // We are moving through the filesystem
                    currentDirectory = currentDirectory.moveDirectory(line.substring(5));
                } else if (line.contains("ls")) {
                    // It just opened the directory to look at contents. Nothing to do on this line
                }
            } else if (line.contains("dir")) {
                // If the line starts with "dir" then we need to possibly create a new directory
                directory newDir = currentDirectory.newChildDirectory(line.substring(4));
                if (newDir != null) {
                    allDirectories.add(newDir);
                }
            } else {
                // If we didn't cause anything else then we are possibly making a new file
                int spacePosition;
                for (spacePosition = 0; spacePosition < line.length(); spacePosition++) {
                    // Find the " " to for substrings around it
                    if (line.charAt(spacePosition) == ' ') { break; }
                }
                int fileSize = Integer.parseInt(line.substring(0, spacePosition));
                String fileName = line.substring(spacePosition + 1);
                currentDirectory.newChildFile(fileSize, fileName);
            }
        }

        // Find sum sizes of directories with size less than 100,000 (part 1)
        int solutionSize = 0;
        for (directory dir : allDirectories) {
            if (dir.getSize() <= 100000) {
                solutionSize += dir.getSize();
            }
        }

        // Find smallest directory that if deleted would free up enough space for the update (part 2)
        int moreSpaceNeeded = REQUIRED_SPACE_FOR_UPDATE - (FILE_SYSTEM_TOTAL_SPACE - allDirectories.get(0).getSize());
        directory removableDirectory = allDirectories.get(0);
        for (directory dir : allDirectories) {
            if (dir.getSize() >= moreSpaceNeeded && dir.getSize() < removableDirectory.getSize()) {
                removableDirectory = dir;
            }
        }

        System.out.println("Solution 1: " + solutionSize + " total size");
        System.out.println("Solution 2: " + removableDirectory.getSize() + " directory size");
    }
}
