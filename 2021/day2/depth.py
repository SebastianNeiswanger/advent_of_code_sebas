lines = []
with open('input.txt') as f:
    lines = f.readlines()

horizontal = 0
depth = 0
for line in lines:
    record = line.split(" ")
    if record[0] == "forward":
        horizontal+=int(record[1])
    elif record[0] == "down":
        depth+=int(record[1])
    elif record[0] == "up":
        depth-=int(record[1])

total = horizontal * depth

print("Puzzle 1: ", total)

horizontal = 0
depth = 0
aim = 0
for line in lines:
    record = line.split(" ")
    if record[0] == "forward":
        horizontal+=int(record[1])
        depth+=(aim*int(record[1]))
    elif record[0] == "down":
        aim+=int(record[1])
    elif record[0] == "up":
        aim-=int(record[1])

total = horizontal * depth

print("Puzzle 2: ", total)