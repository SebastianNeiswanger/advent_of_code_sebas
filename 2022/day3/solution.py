###
### Author: Sebastian Neiswanger
### Date: 12/8/2022
### Solution to day 3 of Advent of Code 2022
###

#read in input
lines = []
with open('input.txt') as f:
    lines = f.readlines()

#dictionary of priority values
item_priorities = {'a':1, 'A':27,
                   'b':2, 'B':28,
                   'c':3, 'C':29,
                   'd':4, 'D':30,
                   'e':5, 'E':31,
                   'f':6, 'F':32,
                   'g':7, 'G':33,
                   'h':8, 'H':34,
                   'i':9, 'I':35,
                   'j':10, 'J':36,
                   'k':11, 'K':37,
                   'l':12, 'L':38,
                   'm':13, 'M':39,
                   'n':14, 'N':40,
                   'o':15, 'O':41,
                   'p':16, 'P':42,
                   'q':17, 'Q':43,
                   'r':18, 'R':44,
                   's':19, 'S':45,
                   't':20, 'T':46,
                   'u':21, 'U':47,
                   'v':22, 'V':48,
                   'w':23, 'W':49,
                   'x':24, 'X':50,
                   'y':25, 'Y':51,
                   'z':26, 'Z':52}

#changing variables
part1_priority = 0
part2_priority = 0
first_half = ""
second_half = ""

#Part 1
#split each line in half
#search the two halves for the duplicate between them
#add that priority value to total priority
for line in lines:
    #split
    line = line.rstrip()
    line_length = len(line)
    first_half = line[0:int(line_length/2)]
    second_half = line[int(line_length/2):line_length]
    
    #search
    for i in range(int(line_length/2)):
        if second_half.__contains__(first_half[i]):
            #add to total
            part1_priority += item_priorities.get(first_half[i])
            break

#Part 2
for i in range(0,len(lines),3):
    group1 = lines[i]
    group2 = lines[i+1]
    group3 = lines[i+2]

    for i in range(len(group1)):
        if group2.__contains__(group1[i]) and group3.__contains__(group1[i]):
            part2_priority += item_priorities.get(group1[i])
            break

print("Solution 1: " + str(part1_priority) + " priority\n")

print("Solution 2: " + str(part2_priority) + " priority\n")