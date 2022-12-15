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
    let mut cycle: i128 = 0;
    // Register X. addx increases this value
    let mut reg_x: i128 = 1;
    // The cycle that indicates signal strength
    let mut signal_cycle: i128 = 20;
    let mut signal_strength: i128 = 0;
    // Keep track of the image drawn by the CRT
    let mut crt: [[&str; 40]; 6] = [["."; 40]; 6];

    // Walk through the input lines
    for line in input_lines {
        cycle += 1;
        test_for_pixel(&cycle, &reg_x, &mut crt);
        let mut split_line = line.split_ascii_whitespace();
        // Figure out the command
        let command: &str = split_line.next().unwrap();
        // If command == "noop" then nothing new happens this cycle
        test_signal(&cycle, &mut signal_cycle, &mut reg_x, &mut signal_strength);
        if command == "addx" {
            cycle += 1;
            test_for_pixel(&cycle, &reg_x, &mut crt);
            test_signal(&cycle, &mut signal_cycle, &mut reg_x, &mut signal_strength);
            let add_value = split_line.next().unwrap().parse::<i128>().unwrap();
            reg_x += add_value;
        }
    }

    // Results
    println!("Solution 1: {} signal strength\n", signal_strength);
    println!("Solution 2: CRT drawing");
    print_crt(crt);
}

fn test_signal(cycle: &i128, sig_cycle :&mut i128, x: &mut i128, signal_strength: &mut i128) {
    if cycle == sig_cycle {
        *signal_strength += *x * *sig_cycle;
        *sig_cycle += 40;
    }
}

fn test_for_pixel(cycle: &i128, x: &i128, crt: &mut [[&str; 40]; 6]) {
    let lit_pixels: [i128; 3] = [x-1, *x, x+1];
    if lit_pixels.contains(&((*cycle-1) % 40)) {
        crt[usize::try_from(cycle / 40).unwrap()][usize::try_from((*cycle-1) % 40).unwrap()] = "#";
    }
}

fn print_crt(crt: [[&str; 40]; 6]) {
    for (_i, row) in crt.iter().enumerate() {
        for (_j, col) in row.iter().enumerate() {
            print!("{}", col);
        }
        print!("\n");
    }
    print!("\n");
}