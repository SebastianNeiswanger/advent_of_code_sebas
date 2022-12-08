###
### Author: Sebastian Neiswanger
### Date: 12/8/2022
### Solution to day 1 of Advent of Code 2022
###

#read in input
lines = []
with open('input.txt') as f:
    lines = f.readlines()

#create variables to hold different calorie values
best_calories = 0
second_best_calories = 0
third_best_calories = 0
current_calories = 0

#Walk through each line updating current_calories if there it is not a new line
#If there is a new line you will test current_calories against the 3 top values and
#update those if needed. Regardless of if it gets used or not the current value
#will be reset at the end
for line in lines:
    if line != '\n':
        current_calories += int(line)
    else:
        if current_calories > best_calories:
            third_best_calories = second_best_calories
            second_best_calories = best_calories
            best_calories = current_calories
        elif current_calories > second_best_calories:
            third_best_calories = second_best_calories
            second_best_calories = current_calories
        elif current_calories > third_best_calories:
            third_best_calories = current_calories
        current_calories = 0

print("Solution 1: " + str(best_calories) + " calories\n")
print("Solution 2: " + str(best_calories + second_best_calories + third_best_calories) + " calories")