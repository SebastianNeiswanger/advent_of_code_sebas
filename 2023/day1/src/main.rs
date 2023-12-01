use fancy_regex::Regex;
use std::{fs, collections::VecDeque};

//
// Author: Sebastian Neiswanger
// Date: 12/01/2023
// A solution for Advent of Code 2023 day 1 in Rust
//

fn read_and_split(file_path: &str) -> Vec<String> {
    // Read in the file to a string
    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");
    // Split the string into useable pieces
    let new_line_split = contents.lines();
    let input_lines: Vec<String> = new_line_split.map(|line| line.to_string()).collect();

    return input_lines;
}

fn replace_number_text(text: String) -> String {
    let mut temp: String = text;
    temp = temp.replace("one", "1");
    temp = temp.replace("two", "2");
    temp = temp.replace("three", "3");
    temp = temp.replace("four", "4");
    temp = temp.replace("five", "5");
    temp = temp.replace("six", "6");
    temp = temp.replace("seven", "7");
    temp = temp.replace("eight", "8");
    temp = temp.replace("nine", "9");
    return temp;
}

fn custom_regex(text: String) -> VecDeque<String> {
    let splice: &str = &text.clone()[..];
    let mut nums: VecDeque<String> = [].into();
    let re = Regex::new(r"^([0-9]|one|two|three|four|five|six|seven|eight|nine)").unwrap();
    for i in 0..splice.len() {
        let cur: &str = &splice[i..];
        match re.captures(cur).unwrap() {
            Some(cap) => {
                nums.append(&mut [cap.get(0).unwrap().as_str().to_owned()].into());
            },
            None => continue,
        }
    }
    return nums;
}

fn calculate_calibration(regex: &str, lines: Vec<String>, part: u128) {
    let mut calibration: u128 = 0;
    let re = Regex::new(regex).unwrap();

    for line in lines {
        let mut nums: VecDeque<String>;
        if part == 1 {
            nums = re.captures_iter(&line).map(|c| c.unwrap().get(0).unwrap().as_str().to_owned()).collect();
        } else {
            nums = custom_regex(line);
        }
        let mut val: String = "0".to_owned();
        if nums.len() == 0 {
            val = "0".to_owned();
        } else if nums.len() == 1 {
            val = nums.pop_back().unwrap().to_owned();
            val = format!("{}{}", val, val);
        } else {
            val = format!("{}{}", nums.pop_front().unwrap().to_owned(), nums.pop_back().unwrap().to_owned());
        }
        calibration += replace_number_text(val).parse::<u128>().unwrap();
    }

    println!("Part {}: {}", part, calibration);
}

fn main() {
    let input_lines: Vec<String> = read_and_split("./input.txt");

    calculate_calibration(r"[0-9]", input_lines.clone(), 1);
    calculate_calibration(r"(?=([0-9]|one|two|three|four|five|six|seven|eight|nine))", input_lines.clone(), 2);
}
