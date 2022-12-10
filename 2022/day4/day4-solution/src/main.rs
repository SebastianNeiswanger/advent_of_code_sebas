use std::{fs, str::Split, ops::Range};

//
// Author: Sebastian Neiswanger
// Date: 12/9/2022
// A solution for Advent of Code 2022 day 4 in Rust
//

fn main() {
    // Read in the file to a string
    let contents = fs::read_to_string("../input.txt")
        .expect("Should have been able to read the file");

    // Split the string into useable pieces
    let new_line_split = contents.lines();
    let input_lines: Vec<&str> = new_line_split.collect();

    //Part 1
    // value to hold number of ranges within the other range
    let mut contained_pairs: u128 = 0;
    let mut overlapping_pairs: u128 = 0;

    for line in input_lines {
        if range_in_range(line) {
            contained_pairs += 1;
        }
        if overlapping_ranges(line) {
            overlapping_pairs += 1;
        }
    }

    println!("Solution 1: {} pairs", contained_pairs);
    println!("Solution 2: {} pairs", overlapping_pairs);
}

fn range_in_range(line: &str) -> bool {
    let ranges = create_ranges(line);

    // Check if a range is encapsilated by the other range
    if ranges[0].contains(&ranges[1].start) && ranges[0].contains(&(ranges[1].end-1)){
        return true;
    } else if ranges[1].contains(&ranges[0].start) && ranges[1].contains(&(ranges[0].end-1)) {
        return true;
    }
    return false;
}

fn overlapping_ranges(line: &str) -> bool {
    let ranges = create_ranges(line);
    
    // Check to see if ranges overlap at all
    if ranges[0].contains(&ranges[1].start) || ranges[0].contains(&(ranges[1].end-1)){
        return true;
    } else if ranges[1].contains(&ranges[0].start) || ranges[1].contains(&(ranges[0].end-1)) {
        return true;
    }
    return false;
}

fn create_ranges(line: &str) -> Vec<std::ops::Range<u128>>{
    // Split string at comma
    let line_split: Split<&str> = line.split(",");
    let two_range_vec: Vec<&str> = line_split.collect();

    // Break apart the two ranges into 4 individual parts
    let first_split_split = two_range_vec[0].split("-");
    let second_split_split = two_range_vec[1].split("-");
    let first_split_vector: Vec<&str> = first_split_split.collect();
    let second_split_vector: Vec<&str> = second_split_split.collect();
    
    // Create range objects
    let first_range: Range<u128> = first_split_vector[0].parse::<u128>().unwrap()..first_split_vector[1].parse::<u128>().unwrap()+1;
    let second_range: Range<u128> = second_split_vector[0].parse::<u128>().unwrap()..second_split_vector[1].parse::<u128>().unwrap()+1;    

    return vec![first_range, second_range];
}