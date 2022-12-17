package com.mycompany.day11.solution;

import java.util.Vector;

public class Monkey {
    // Variables extracted in the constructor
    private Vector<Integer> items = new Vector<Integer>();
    private char operationSign;
    private String operationRightSide;
    private int divisibleBy;
    private int trueMonkey;
    private int falseMonkey;

    // Keep track of how many times the monkey inspected an item
    private int inspectedItems = 0;

    // For part 2 we need to keep the number smaller but also divisible
    private int modulo = 1;

    /**
     * Retreive the monkey data from the strings given. 
     * A lot of the input will be filler, but it makes it easier to take
     * care of in the constructor than in the main code. Please remove
     * all white space before sending a string through. For example:
     * startingItemString.replaceAll("\\s", "").
     * 
     * @param startingItems full string that contains the starting items
     * @param operation full string that contains the operation the monkey does for each item
     * @param divisibleString full string that contains the divisibility check to see which monkey the monkey will throw too
     * @param trueString full string of monkey the current monkey will throw to if check is true
     * @param falseString full string of monkey the current monkey will throw to if check is false
     */
    public Monkey(String startingItems, String operation, String divisibleString, String trueString, String falseString) {
        // Extract starting items
        String[] startingArr = startingItems.split(":");
        String[] itemsArr = startingArr[1].split(",");
        for (String item : itemsArr) {
            items.add(Integer.parseInt(item));
        }
        // Extract operation
        if (operation.contains("*")) {
            operationSign = '*';
        } else {
            operationSign = '+';
        }
        operationRightSide = operation.substring(operation.indexOf(operationSign) + 1);
        // Extract the "divisible by" number
        String[] divibleSplitString = divisibleString.split("by");
        divisibleBy = Integer.parseInt(divibleSplitString[1]);
        // Extract Monkey that is thrown to when true
        String[] trueSplitString = trueString.split("monkey");
        trueMonkey = Integer.parseInt(trueSplitString[1]);
        // Extract Monkey that is thrown to when false
        String[] falseSplitString = falseString.split("monkey");
        falseMonkey = Integer.parseInt(falseSplitString[1]);
    }

    /**
     * This process will include the inspection of each held item one at a time,
     * the monkey getting bored with the item, and then throwing the item to
     * a different monkey
     * 
     * @param monkeysToThrowTo Allows the monkey to properly throw the item
     * @param worryDrop Int that determins how much your worry drops when
     * the monkey gets bored with the item
     */
    public void inspectItems(Vector<Monkey> monkeysToThrowTo, int worryDrop) {
        for (int item : items) {
            // Inspect the item
            if (operationSign == '+') {
                if (operationRightSide.equals("old")) {
                    item += item;
                } else {
                    item += Integer.parseInt(operationRightSide);
                }
            } else if (operationSign == '*') {
                if (operationRightSide.equals("old")) {
                    item *= item;
                } else {
                    item *= Integer.parseInt(operationRightSide);
                }
            }
            inspectedItems++;
            // Get bored with the item
            if (worryDrop != 0) {
                item /= worryDrop;
            } else {
                item %= modulo;
            }
            // Throw Item
            if (0 == item % divisibleBy) {
                monkeysToThrowTo.get(trueMonkey).catchItem(item);
            } else {
                monkeysToThrowTo.get(falseMonkey).catchItem(item);
            }
        }
        items.removeAllElements();
    }

    /**
     * Sets the modulo required to keep part 2 within the int limit
     * 
     * @param mod int to set the modulo with
     */
    public void setModulo(int mod) {
        modulo = mod;
    }

    /**
     * Used when finding the modulo for part 2
     * 
     * @return divisibleBy int
     */
    public int getDivisibleBy() {
        return divisibleBy;
    }

    /**
     * Obtain a new item thrown by a different monkey
     * 
     * @param newItem new items worry value
     */
    public void catchItem(int newItem) {
        items.add(newItem);
    }

    /**
     * Get the number of items that the 
     * monkey has inspected up to this point
     * 
     * @return the total number inspected items
     */
    public int totalInspectedItems() {
        return inspectedItems;
    }
}