package com.mycompany.day7.solution;

/**
 * File class that does little to nothing. 
 * It will have a name, and size.
 */
public class file {
    private int size;
    private String name;

    public file(int inputSize, String inputName) {
        size = inputSize;
        name = inputName;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
