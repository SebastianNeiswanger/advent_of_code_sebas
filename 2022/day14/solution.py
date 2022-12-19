###
### Author: Sebastian Neiswanger
### Date: 12/18/2022
### Solution to day 14 of Advent of Code 2022
###

#add structure from input to grid
def addLine(line: str, grid):
    line.replace(' ', '')
    points = line.split('->')
    structure = []
    for point in points:
        current = point.split(',')
        structure.append((int(current[0]), int(current[1])))
    startPoint = structure[0]
    #add the walls
    for wall in structure:
        if wall == startPoint:
            continue
        if startPoint[0] == wall[0]:
            #verticle wall
            if wall[1] > startPoint[1]:
                for i in range((wall[1]+1)-startPoint[1]):
                    grid[wall[0]][startPoint[1]+i] = '#'
            else:
                for i in range((startPoint[1]+1)-wall[1]):
                    grid[wall[0]][startPoint[1]-i] = '#'
        else:
            #horizontal wall
            if wall[0] > startPoint[0]:
                for i in range((wall[1]+1)-startPoint[1]):
                    grid[startPoint[0]+i][wall[1]] = '#'
            else:
                for i in range((startPoint[1]+1)-wall[1]):
                    grid[startPoint[0]-i][wall[1]] = '#'
    return grid

#read in input
lines = []
with open('test-input.txt') as f:
    lines = f.readlines()

caveGrid = [['.' for i in range(504)] for j in range(10)]

#add all structures to grid
for line in lines:
    caveGrid = addLine(line.rstrip(), caveGrid)

print(caveGrid)