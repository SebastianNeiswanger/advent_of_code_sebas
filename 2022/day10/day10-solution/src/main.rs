use std::fs;

//
// Author: Sebastian Neiswanger
// Date: 12/14/2022
// A solution for Advent of Code 2022 day 10 in Rust
//

fn main() {
    // Read in the file to a string
    let contents = fs::read_to_string("../input.txt")
        .expect("Should have been able to read the file");

    // Split the string into useable pieces
    let new_line_split = contents.lines();
    let input_lines: Vec<&str> = new_line_split.collect();

    // Keep track of the computer cycles
    let mut cycle: u128 = 0;
    // Register X. addx increases this value
    let mut regX: u128 = 1;
    // The cycle that indicates signal strength
    let mut signalCycle: u128 = 20;
    let mut signalStrength: u128 = 0;

    // Walk through the input lines
    for line in input_lines {
        cycle += 1;
        let mut split_line = line.split_ascii_whitespace();
        // Figure out the command
        let command: &str = split_line.next().unwrap();
        // If command == "noop" then nothing new happens this cycle
    }

    println!("Solution 1: {} signal strength", signalStrength);
}