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

fn determine_valid_game(
    red_max: u32,
    green_max: u32,
    blue_max: u32,
    red: u32,
    green: u32,
    blue: u32,
) -> bool {
    if red > red_max || green > green_max || blue > blue_max {
        return false;
    }
    return true;
}

fn main() {
    let lines: Vec<String> = read_and_split("./input.txt");
    let mut game_total: u128 = 0;

    for line in &lines {
        let first_split: VecDeque<&str> = line.split(":").collect();
        let second_split: VecDeque<&str> = first_split[1].split(";").collect();
        let re = Regex::new(r"[0-9]").unwrap();
        let game: u128 = re.captures(first_split[0]).unwrap()[0].parse().unwrap();
        let re =
            Regex::new(r"(?<red>[0-9]+ red)|(?<blue>[0-9]+ blue)|(?<green>[0-9]+ green)").unwrap();
        let mut valid_game = true;
        for s in second_split {
            // let mut captures: VecDeque<String> = re
            //     .captures_iter(s)
            //     .map(|c| c.get(0).unwrap().as_str().to_owned())
            //     .collect();
            let Some(caps) = re.captures(s) else {
                continue;
            };
            let mut red: u32 = 0;
            let mut green: u32 = 0;
            let mut blue: u32 = 0;
            let caps = re.captures_iter(s);
            for cap in caps {
                println!("{}", cap.get(0).unwrap().as_str());
            }
            if !determine_valid_game(
                12, 
                13, 
                14, 
                red, 
                green, 
                blue
            ) {
                valid_game = false;
                break;
            }
        }
        if valid_game {
            game_total += game;
        }
    }
    println!("Part 1: {}", game_total)
}
