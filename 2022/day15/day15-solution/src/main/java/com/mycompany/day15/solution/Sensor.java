package com.mycompany.day15.solution;

public class Sensor {
    private int[] cord = new int[2];
    private int[] beacon = new int[2];

    public Sensor(String inputLine) {
        inputLine.replaceAll("\n", "");
        String[] splitInput = inputLine.split("=");
        cord[0] = Integer.parseInt(splitInput[1].substring(0, splitInput[1].indexOf(",")));
        cord[1] = Integer.parseInt(splitInput[2].substring(0, splitInput[2].indexOf(":")));
        beacon[0] = Integer.parseInt(splitInput[3].substring(0, splitInput[3].indexOf(",")));
        beacon[1] = Integer.parseInt(splitInput[4]);
    }

    
}
