###
### Author: Sebastian Neiswanger
### Date: 12/18/2022
### Solution to day 12 of Advent of Code 2022
###

#read in input
lines = []
with open('input.txt') as f:
    lines = f.readlines()

#array to hold map
heightMap = [[0 for i in range(77)] for j in range(41)]
for row in range(41):
    for col in range(77):
        if lines[row][col] == 'S':
            start = (row, col)
        elif lines[row][col] == 'E':
            end = (row, col)
        heightMap[row][col] = lines[row][col]

#Part 1
#queue to conduct BFS
bfsQueue = []
found = []

#conduct BFS to find shortest path to E
bfsQueue.append((start, 0))
found.append((start, 0))
leastSteps = 0
while leastSteps == 0:
    current = bfsQueue.pop(0)
    height = heightMap[current[0][0]][current[0][1]]
    if height == 'S':
        height = 'a'

    north = (current[0][0]-1, current[0][1])
    south = (current[0][0]+1, current[0][1])
    east = (current[0][0], current[0][1]+1)
    west = (current[0][0], current[0][1]-1)
    directions = [north, south, east, west]

    #Check north, south, east, then west
    for direction in directions:
        if direction[0] in range(0, 41) and direction[1] in range(0, 77) and direction not in found:
            if heightMap[direction[0]][direction[1]] == 'E' and ord(height) + 1 >= ord('z'):
                leastSteps = current[1] + 1
                break
            if ord(height) + 1 >= ord(heightMap[direction[0]][direction[1]]) and heightMap[direction[0]][direction[1]] != 'E':
                bfsQueue.append((direction, current[1]+1))
                found.append(direction)

print("Solution 1: ", leastSteps, " is the least amount of steps from S to E")

#Part 2
#queue to conduct BFS
bfsQueue = []
found = []

#conduct BFS to find shortest path to a
bfsQueue.append((end, 0))
found.append((end, 0))
leastSteps = 0
while leastSteps == 0:
    current = bfsQueue.pop(0)
    height = heightMap[current[0][0]][current[0][1]]
    if height == 'E':
        height = 'z'

    north = (current[0][0]-1, current[0][1])
    south = (current[0][0]+1, current[0][1])
    east = (current[0][0], current[0][1]+1)
    west = (current[0][0], current[0][1]-1)
    directions = [north, south, east, west]

    #Check north, south, east, then west
    for direction in directions:
        if direction[0] in range(0, 41) and direction[1] in range(0, 77) and direction not in found:
            if ord(height) <= ord(heightMap[direction[0]][direction[1]]) + 1:
                if heightMap[direction[0]][direction[1]] == 'a':
                    leastSteps = current[1] + 1
                    break
                bfsQueue.append((direction, current[1]+1))
                found.append(direction)

print("Solution 2: ", leastSteps, " is the least amount of steps from a to E\n")