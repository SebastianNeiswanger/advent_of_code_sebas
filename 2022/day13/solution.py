###
### Author: Sebastian Neiswanger
### Date: 12/18/2022
### Solution to day 13 of Advent of Code 2022
###

def createList(list):
    partialPacket = []
    while list != '':
        if list[0] == ',':
            list = list[1:]
        if list[0] != '[':
            numberString = ""
            while list != '' and list[0] != ',':
                numberString += list[0]
                list = list[1:]
            partialPacket.append(int(numberString))
        else:
            recursiveLists = 0
            endNextList = 0
            for i in range(1, len(list)):
                if list[i] == ']':
                    if recursiveLists == 0:
                        endNextList = i
                        break
                    else:
                        recursiveLists -= 1
                elif list[i] == '[':
                    recursiveLists += 1
            partialPacket.append(createList(list[list.find('[') + 1:endNextList]))
            list = list[:list.find('[')] + list[endNextList + 1:]
    return partialPacket

#compare lists
def compareLists(packet1, packet2):
    #return 0 if out of order, 1 if we need to continue looking, 2 if we know it is in order
    for i in range(0, min(len(packet1), len(packet2))):
        if isinstance(packet1[i], type(packet2[i])) and isinstance(packet1[i], int):
            if packet2[i] < packet1[i]:
                return 0
            elif packet1[i] < packet2[i]:
                return 2
        elif isinstance(packet1[i], type(packet2[i])) and isinstance(packet1[i], list):
            subCompare = compareLists(packet1[i], packet2[i])
            if not subCompare:
                return 0
            elif subCompare == 2:
                return 2
        elif isinstance(packet1[i], list):
            subCompare = compareLists(packet1[i], [packet2[i]])
            if not subCompare:
                return 0
            elif subCompare == 2:
                return 2
        elif isinstance(packet2[i], list):
            subCompare = compareLists([packet1[i]], packet2[i])
            if not subCompare:
                return 0
            elif subCompare == 2:
                return 2
    if len(packet1) < len(packet2):
        return 2
    elif len(packet1) > len(packet2):
        return 0
    return 1

#quicksort lists
def quickSortLists(packets: list):
    if len(packets) == 1:
        return packets
    partition = packets.pop()
    leftSide = []
    rightSide = []
    for packet in packets:
        if compareLists(partition, packet):
            rightSide.append(packet)
        else:
            leftSide.append(packet)
    combined = []
    if len(leftSide):
        combined.extend(quickSortLists(leftSide))
    combined.append(partition)
    if len(rightSide):
        combined.extend(quickSortLists(rightSide))
    return combined

#read in input
lines = []
with open('input.txt') as f:
    lines = f.readlines()

packets = []

#Turn input into packets
for line in lines:
    if line != "\n":
        packets.append(createList(line[line.find('[') + 1:line.rfind(']')]))

#Part 1
#compare pairs of packets
rightOrder = 0
for i in range(0, int(len(packets)), 2):
    if compareLists(packets[i], packets[i+1]):
        rightOrder += ((i/2) + 1)

print("Solution 1:", int(rightOrder), "pairs of packets in the right order")

#Part 2
#Add divider packets and put the packets in the correct order
#Then locate divider packets and multiply their indicies
divider1 = [[2]]
divider2 = [[6]]
packets.append(divider1)
packets.append(divider2)
packets = quickSortLists(packets)

distressSignal = (packets.index(divider1)+1) * (packets.index(divider2)+1)
print("Solution 2:", distressSignal, "is the distress signal recieved from the packets")