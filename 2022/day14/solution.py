###
### Author: Sebastian Neiswanger
### Date: 12/19/2022
### Solution to day 14 of Advent of Code 2022
###

#add structure from input to grid
def addLine(line: str, grid):
    line.replace(' ', '')
    points = line.split('->')
    structure = []
    for point in points:
        current = point.split(',')
        structure.append((int(current[1]), int(current[0])))
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
                for i in range((wall[0]+1)-startPoint[0]):
                    grid[startPoint[0]+i][wall[1]] = '#'
            else:
                for i in range((startPoint[0]+1)-wall[0]):
                    grid[startPoint[0]-i][wall[1]] = '#'
        startPoint = wall

    return grid

def sandSimulation(grid):
    #drop sand until it falls out of the grid
    #returns the number of times the sand did not leave the grid
    restingSand = 0
    abyssSand = False
    while not abyssSand:
        currentRested = False
        currentSand = [0, 500]
        while not currentRested:
            if ((currentSand[0] + 1) not in range(0, len(grid))) or ((currentSand[1] - 1) not in range(0, len(grid[0]))):
                abyssSand = True
                break
            elif grid[currentSand[0]+1][currentSand[1]] == '.':
                #South of sand particle is open
                currentSand[0] += 1
            elif grid[currentSand[0]+1][currentSand[1]-1] == '.':
                #South West of sand particle is open
                currentSand[0] += 1
                currentSand[1] -= 1
            elif grid[currentSand[0]+1][currentSand[1]+1] == '.':
                #South East of sand particle is open
                currentSand[0] += 1
                currentSand[1] += 1
            else:
                #sand can't fall anymore so it is rested
                if grid[0][500] == 'o':
                    #sand was already full
                    abyssSand = True
                    break
                grid[currentSand[0]][currentSand[1]] = 'o'
                currentRested = True
        # testing the test file
        # for row in range(12):
        #     print(grid[row][492:506])
        restingSand += 1
    return restingSand - 1

#read in input
lines = []
with open('input.txt') as f:
    lines = f.readlines()

caveGrid = [['.' for i in range(1000)] for j in range(700)]

#add all structures to grid
for line in lines:
    caveGrid = addLine(line.rstrip(), caveGrid)

part1 = sandSimulation(caveGrid)
print("Solution 1:", part1, "sand particles before falling into the abyss")

#Part 2 add a line at the floor and run simulation again
floor = 0
for row in reversed(range(700)):
    if '#' in caveGrid[row]:
        floor = max(row + 2, floor)

infiniFloor = "0," + str(floor) + "->999," + str(floor)
caveGrid = addLine(infiniFloor, caveGrid)

print("Solution 2:", sandSimulation(caveGrid) + part1, "sand particles before filling to max")