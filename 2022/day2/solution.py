###
### Author: Sebastian Neiswanger
### Date: 12/8/2022
### Solution to day 2 of Advent of Code 2022
###

#read in input
lines = []
with open('input.txt') as f:
    lines = f.readlines()

#Part1

#constant variables
rock_play = 1
paper_play = 2
sissor_play = 3
lost_round = 0
tie_round = 3
win_round = 6
winning_vaiations = ("A Y\n", "B Z\n", "C X\n")
losing_vaiations = ("A Z\n", "B X\n", "C Y\n")

#changing vaiables
total_score = 0
current_score = 0

#Each line in lines denotes a round
#We need to find the score from each round and add them all together
for line in lines:
    if "X" in line:
        current_score += rock_play
    elif "Y" in line:
        current_score += paper_play
    elif "Z" in line:
        current_score += sissor_play

    if line in winning_vaiations:
        current_score += win_round
    elif line in losing_vaiations:
        current_score += lost_round
    else:
        current_score += tie_round

    total_score += current_score
    current_score = 0

print("Solution 1: " + str(total_score) + " points\n")

#Part 2

#dictionaries that show what points you get per win, lose, or tie
#Z is a win, X is a lose, Y is a tie
#A is the opponent Rock, B is the opponent Paper, C is the opponent Sissors
distributed_points = {"Z":{"A":win_round+paper_play, "B":win_round+sissor_play, "C":win_round+rock_play}, 
                        "X":{"A":lost_round+sissor_play, "B":lost_round+rock_play, "C":lost_round+paper_play},
                        "Y":{"A":tie_round+rock_play, "B":tie_round+paper_play, "C":tie_round+sissor_play}}

#changing variables
total_score = 0

#We will check each line for a X, Y, or Z then see what the opponent played to get the score we need to add to our score
for line in lines:
    total_score += distributed_points.get(line[2]).get(line[0])

print("Solution 2: " + str(total_score) + " points")