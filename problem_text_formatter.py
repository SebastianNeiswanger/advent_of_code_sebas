###
###Author: Sebastian Neiswanger
###Date: 12/8/2022
###I wanted an easy way to format the problem texts
###

import sys
import os

#Take in the file location as an argument
if len(sys.argv) != 2:
    print("There is not the right amount of command line arguments.")
else:
    filePath = sys.argv[1]
    #open previous file
    with open(filePath) as f:
        unformatted_lines = f.readlines()
    f.close()

    #delete previous file
    os.remove(filePath)

    #edit the lines to correct length (80 chars)
    formated_lines = []
    for i in range(len(unformatted_lines)):
        if len(unformatted_lines[i]) > 80:
            words = unformatted_lines[i].split(' ')
            newLine = ""
            for i in range(len(words)):
                if (len(newLine + words[i] + ' ')) > 80:
                    formated_lines.append(newLine + '\n')
                    newLine = ""
                newLine += words[i] + ' '
            if newLine != "":
                formated_lines.append(newLine)
        else:
            formated_lines.append(unformatted_lines[i])

    #write to new file
    formatted_file = open(filePath, "w")
    for line in formated_lines:
        formatted_file.write(line)
    formatted_file.close()