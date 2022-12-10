use std::fs;

fn main() {
    // Read in the file to a string
    let contents = fs::read_to_string("../input.txt")
        .expect("Should have been able to read the file");

    // Split the string into useable pieces
    let new_line_split = contents.lines();
    let input_lines: Vec<&str> = new_line_split.collect();

    // Ignore first 10 lines because of stacks initializing
    let ignore_line_to: u128 = 10;

    // Array of vectors that hold the current cargo
    let mut cargo_stacks: [Vec<&str>; 9] = [vec!["W","B","D","N","C","F","J"], 
                                           vec!["P","Z","V","Q","L","S","T"], 
                                           vec!["P","Z","B","G","J","T"], 
                                           vec!["D","T","L","J","Z","B","H","C"], 
                                           vec!["G","V","B","J","S"], 
                                           vec!["P","S","Q"], 
                                           vec!["B","V","D","F","L","M","P","N"], 
                                           vec!["P","S","M","F","B","D","L","R"], 
                                           vec!["V","D","T","R"]];

    // Queue to hold current pieces of cargo to be moved
}