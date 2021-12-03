def BinToDec(value):
  try:
    return int(value, 2)
  except ValueError:
    return "Invalid binary Value"


lines = []
with open('input.txt') as f:
    lines = f.readlines()

digits = [0,0,0,0,0,0,0,0,0,0,0,0]
for line in lines:
    bits = BinToDec(line)
    for i in range(0, 12):
        if bits%2 == 1:
            digits[i]+=1
        bits = bits >> 1

gammaRate = 0
epsilonRate = 0
forwardDigits = digits[::-1]

for x in forwardDigits:
    if (x < 500):
        gammaRate = gammaRate | 0
        epsilonRate = epsilonRate | 1
    else:
        gammaRate = gammaRate | 1
        epsilonRate = epsilonRate | 0
    gammaRate = gammaRate << 1
    epsilonRate = epsilonRate << 1

gammaRate = gammaRate >> 1
epsilonRate = epsilonRate >> 1
powerCpnsumption = gammaRate * epsilonRate

print("Puzzle 1: ", powerCpnsumption)

oxygenRating = 0
co2Rating = 0
mostUsed = []
leastUsed = []
for line in lines:
    mostUsed.append(line.replace("\n", ""))
    leastUsed.append(line.replace("\n", ""))

while oxygenRating == 0:
    for i in range(0, 12):
        numOf1 = 0
        for j in mostUsed:
            if j[i] == "1":
                numOf1+=1
        x = 0
        if numOf1 < (len(mostUsed) / 2):
            while x < len(mostUsed):
                if mostUsed[x][i] == "1":
                    mostUsed.pop(x)
                    x-=1
                x+=1
        else:
            while x < len(mostUsed):
                if mostUsed[x][i] == "0":
                    mostUsed.pop(x)
                    x-=1
                x+=1
        if len(mostUsed) == 1:
            oxygenRating = mostUsed[0]
            break

while co2Rating == 0:
    for i in range(0, 12):
        numOf1 = 0
        for j in leastUsed:
            if j[i] == "1":
                numOf1+=1
        x = 0
        if numOf1 < (len(leastUsed) / 2):
            while x < len(leastUsed):
                if leastUsed[x][i] == "0":
                    leastUsed.pop(x)
                    x-=1
                x+=1
        else:
            while x < len(leastUsed):
                if leastUsed[x][i] == "1":
                    leastUsed.pop(x)
                    x-=1
                x+=1
        if len(leastUsed) == 1:
            co2Rating = leastUsed[0]
            break
            

ox = BinToDec(oxygenRating)
co2 = BinToDec(co2Rating)

lifeSupport = ox * co2

print("Puzzle 2: ", lifeSupport)