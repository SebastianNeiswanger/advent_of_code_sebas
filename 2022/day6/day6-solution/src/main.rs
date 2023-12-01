use std::fs;

//
// Author: Sebastian Neiswanger
// Date: 12/10/2022
// A solution for Advent of Code 2022 day 6 in Rust
//

fn main() {
    // Read in the file to a string
    let contents =
        fs::read_to_string("../input.txt").expect("Should have been able to read the file");

    // Split the string into useable pieces
    let new_line_split = contents.lines();
    let input_lines: Vec<&str> = new_line_split.collect();
    
    // position of starts
    let mut start_of_packet: u128 = 0;
    let mut start_of_message: u128 = 0;

    // Only the first line is needed on this day
    // We will walk through until we find a unique stream of 4 chars and break
    for buffer_position in 0..input_lines[0].len() - 4 {
        if unique_chars(&input_lines[0][buffer_position..buffer_position + 4], 4) {
            start_of_packet = (u128::try_from(buffer_position).unwrap()) + 4;
            break;
        }
    }

    //Now we look for the start of message by doing the same thing to a length of 14
    for buffer_position in 0..input_lines[0].len() - 14 {
        if unique_chars(&input_lines[0][buffer_position..buffer_position + 14], 14) {
            start_of_message = (u128::try_from(buffer_position).unwrap()) + 14;
            break;
        }
    }

    println!("Solution 1: {}", start_of_packet);
    println!("Solution 2: {}", start_of_message);
}

fn unique_chars(possible_unique_str: &str, length: usize) -> bool {
    for i in 0..length {
        for j in i + 1..length {
            if possible_unique_str.as_bytes()[i] as char
                == possible_unique_str.as_bytes()[j] as char
            {
                return false;
            }
        }
    }
    return true;
}
