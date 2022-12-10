use std::{fs, collections::VecDeque};

fn main() {
    // Read in the file to a string
    let contents = fs::read_to_string("../input.txt")
        .expect("Should have been able to read the file");

    // Split the string into useable pieces
    let new_line_split = contents.lines();
    let mut input_lines: Vec<&str> = new_line_split.collect();

    // Ignore first 10 lines because of stacks initializing
    let ignore_line_to: u128 = 10;

    // Array of vectors that hold the current cargo
    let mut cargo_stacks_part_1: [Vec<&str>; 9] = [vec!["W","B","D","N","C","F","J"], 
                                                   vec!["P","Z","V","Q","L","S","T"], 
                                                   vec!["P","Z","B","G","J","T"], 
                                                   vec!["D","T","L","J","Z","B","H","C"], 
                                                   vec!["G","V","B","J","S"], 
                                                   vec!["P","S","Q"], 
                                                   vec!["B","V","D","F","L","M","P","N"], 
                                                   vec!["P","S","M","F","B","D","L","R"], 
                                                   vec!["V","D","T","R"]];

    let mut cargo_stacks_part_2: [Vec<&str>; 9] = cargo_stacks_part_1.clone();

    // Queue to hold cargo being moved in part 1
    let mut cargo_queue: VecDeque<&str> = VecDeque::new();

    // Stack to hold cargo being moved in part 2
    let mut cargo_stack: VecDeque<&str> = VecDeque::new();

    // Remove starting lines that indicate the cargo stacks
    for _i in 0..ignore_line_to {
        input_lines.remove(0);
    }

    // Walk through the input and move cargo accordingly
    for line in input_lines {
        let mut split_line = line.split_ascii_whitespace();
        // Skip first word
        split_line.next();
        // The amount that needs moved
        let amount: usize = split_line.next().unwrap().parse().unwrap();
        // Ignore next word
        split_line.next();
        // Which pile are we taking from
        let from_stack: usize = split_line.next().unwrap().parse().unwrap();
        // Skip word
        split_line.next();
        // What pile are we adding to
        let to_stack: usize = split_line.next().unwrap().parse().unwrap();

        // Now that we have all the necissary information we will move the cargo
        for _i in 0..amount {
            cargo_queue.push_back(cargo_stacks_part_1[from_stack-1].pop().unwrap());
            cargo_stack.push_back(cargo_stacks_part_2[from_stack-1].pop().unwrap());
        }
        for _i in 0..amount {
            cargo_stacks_part_1[to_stack-1].push(cargo_queue.pop_front().unwrap());
            cargo_stacks_part_2[to_stack-1].push(cargo_stack.pop_back().unwrap());
        }
    }

    // Print solutions
    println!("Solution 1: {}{}{}{}{}{}{}{}{}", cargo_stacks_part_1[0].last().unwrap(),
                                               cargo_stacks_part_1[1].last().unwrap(),
                                               cargo_stacks_part_1[2].last().unwrap(),
                                               cargo_stacks_part_1[3].last().unwrap(),
                                               cargo_stacks_part_1[4].last().unwrap(),
                                               cargo_stacks_part_1[5].last().unwrap(),
                                               cargo_stacks_part_1[6].last().unwrap(),
                                               cargo_stacks_part_1[7].last().unwrap(),
                                               cargo_stacks_part_1[8].last().unwrap());

    println!("Solution 2: {}{}{}{}{}{}{}{}{}", cargo_stacks_part_2[0].last().unwrap(),
                                               cargo_stacks_part_2[1].last().unwrap(),
                                               cargo_stacks_part_2[2].last().unwrap(),
                                               cargo_stacks_part_2[3].last().unwrap(),
                                               cargo_stacks_part_2[4].last().unwrap(),
                                               cargo_stacks_part_2[5].last().unwrap(),
                                               cargo_stacks_part_2[6].last().unwrap(),
                                               cargo_stacks_part_2[7].last().unwrap(),
                                               cargo_stacks_part_2[8].last().unwrap());
}