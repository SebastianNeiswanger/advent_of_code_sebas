lines = []
with open('input.txt') as f:
    lines = f.readlines()

count = -1
lastnumber = 0
for line in lines:
    num = int(line)
    if num > lastnumber:
        count+=1
    lastnumber = num

print("Puzzle 1: ", count)

count = -1
lastnumber = 0
place = 0
for line in lines:
    if place == 1998:
        break
    num = int(line)
    num += int(lines[place+1])
    num += int(lines[place+2])
    if num > lastnumber:
        count+=1
    lastnumber = num
    place+=1

print("Puzzle 2: ", count)