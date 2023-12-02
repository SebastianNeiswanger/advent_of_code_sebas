use regex::Regex;
use std::{collections::VecDeque, fs};

//
// Author: Sebastian Neiswanger
// Date: 12/02/2023
// A solution for Advent of Code 2023 day 2 in Rust
//

fn read_and_split(file_path: &str) -> Vec<String> {
    // Read in the file to a string
    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");
    // Split the string into useable pieces
    let new_line_split = contents.lines();
    let input_lines: Vec<String> = new_line_split.map(|line| line.to_string()).collect();

    return input_lines;
}

fn determine_valid_game(red_max: u32, green_max: u32, blue_max: u32, red: u32, green: u32, blue: u32) -> bool {
    if red > red_max || green > green_max || blue > blue_max {
        return false;
    }
    return true;
}

fn main() {
    let lines: Vec<String> = read_and_split("./input.txt");
    let mut game_total: u128 = 0;

    for line: &str in &lines {
        let re = Regex::new(r"[0-9]").unwrap();
        let re = Regex::new(r"(?<red>[0-9]+ red)|?<blue>([0-9]+ blue)|(?<green>[0-9]+ green)").unwrap();
        let first_split: Vec<&str> = line.split(":").collect();
        let game: u32 = re.captures(first_split[0]).unwrap()[0].parse().unwrap();
        let valid_game = true;
        let second_split: Vec<&str> = first_split[1].split(";").collect();
        let mut captures: VecDeque<String> = re
            .captures_iter(test_str)
            .map(|c| c.get(0).unwrap().as_str().to_owned())
            .collect();
        for cap in captures {
            println!("{}", cap);
        }
    }

   
}
