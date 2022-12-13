package com.mycompany.day8.solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author  Sebastian Neiswanger
 * @date    12/13/2022
 * @purpose solution to Advent of Code 2022 day 8 in Java 
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Turns input into a scanner that can be walked through
        Scanner inputFile = new Scanner(new File("input.txt"));

        // Create big array that holds every tree as the int represents their height
        int[][] meadowOfTrees = new int[99][99];
        for (int i = 0; i < 99; i++) {
            String rowOfTrees = inputFile.nextLine().replaceAll("\n", "");
            for (int j = 0; j < 99; j++) {
                meadowOfTrees[i][j] = Character.getNumericValue(rowOfTrees.charAt(j));
            }
        }
        inputFile.close();

        // Part 1: Walk through the meadow of trees and find each tree that is visible
        // Part 2: Walk through the meadow of trees to find the highest scenic score
        int visibleTrees = 0;
        int bestScenicScore = 0;
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                boolean visible = false;
                if (i == 0 || j == 0 || i == 98 || j == 98) {
                    //tree is on the edge and therefore visible
                    visible = true;
                }
                int currentTree = meadowOfTrees[i][j];
                
                // scenic scores
                int currentScenicScore = 0;
                int northScenicScore = 0;
                int southScenicScore = 0;
                int eastScenicScore = 0;
                int westScenicScore = 0;

                for (int northTree = j-1; northTree > -1; northTree--) {
                    northScenicScore++;
                    if (meadowOfTrees[i][northTree] >= currentTree) {
                        // There is a tree of the same size or larger to the North
                        break;
                    } else if (northTree == 0) {
                        // Made it to an edge without finding a blocking tree so it is visible
                        visible = true;
                    }
                }
                for (int southTree = j+1; southTree < 99; southTree++) {
                    southScenicScore++;
                    if (meadowOfTrees[i][southTree] >= currentTree) {
                        // There is a tree of the same size or larger to the South
                        break;
                    } else if (southTree == 98) {
                        // Made it to an edge without finding a blocking tree so it is visible
                        visible = true;
                    }
                }
                for (int eastTree = i+1; eastTree < 99; eastTree++) {
                    eastScenicScore++;
                    if (meadowOfTrees[eastTree][j] >= currentTree) {
                        // There is a tree of the same size or larger to the East
                        break;
                    } else if (eastTree == 98) {
                        // Made it to an edge without finding a blocking tree so it is visible
                        visible = true;
                    } 
                }
                for (int westTree = i-1; westTree > -1; westTree--) {
                    westScenicScore++;
                    if (meadowOfTrees[westTree][j] >= currentTree) {
                        // There is a tree of the same size or larger to the West
                        break;
                    } else if (westTree == 0) {
                        // Made it to an edge without finding a blocking tree so it is visible
                        visible = true;
                    }
                }
                if (visible) { visibleTrees++; }
                currentScenicScore = northScenicScore * southScenicScore * eastScenicScore * westScenicScore;
                bestScenicScore = Math.max(currentScenicScore, bestScenicScore);
            }
        }

        System.out.println("Solution 1: " + visibleTrees + " trees visible\n");
        System.out.println("Solution 2: " + bestScenicScore + " best scenic score\n");
    }
}