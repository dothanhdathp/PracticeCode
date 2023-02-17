import glob, os
import json
# Regex
import re

# Data to be written

#
canDumpData = '{ }'
jtemp = json.loads(canDumpData)

## Grep data from text in dbc file
def makeJsonFile(fileName):
    filename = str(fileName).replace(".dbc",".json")
    return filename

## Grep data from text in dbc file
def dbcToJsonConverter(dbc_file):
    # for each line in dbc_file
    source = open(dbc_file, 'r')
    Lines = source.readlines()
    message_name = "_"*50
    message_id = "_"*50
    FLAG_BO_ = False
    for line in Lines:
    # If not meet FLAG_SG_, this mean in term check add data
        if(FLAG_BO_ == True):
            tempdata = re.search('(?:\s(?:SG_)\s)(\w*)(?:\s)(?:(?::\s)|(?:m\d\s:\s|))(\d{1,})(?:\|)(\d{1,})(?:@)', line)
            if tempdata:
                value = {
                    "NAME": str(tempdata.group(1)),
                    "POS": int(tempdata.group(2)),
                    "LEN": int(tempdata.group(3))
                }
                jtemp[str(message_id)]["SIGNAL"].append(value)
            else:
            # END collect turn off FLAG
                FLAG_BO_ = False
                message_id = ""
                message_name = ""
        else:
            tempdata = re.search('(?:(?:BO_)\s)(\d{1,})(?:\s)(\w*)(?::.*)', line)
            if tempdata:
                FLAG_BO_ = True
                message_id = int(tempdata.group(1))
                message_name = str(tempdata.group(2))
                value = {
                    str(message_id): {
                        "MESSAGE_NAME": message_name,
                        "ID": message_id,
                        "SIGNAL":[]
                    }
                }
                jtemp.update(value)

def main():
    os.chdir("./")
    for file in glob.glob("*.dbc"):
        # Replace file extension from .dbc to .json
        json_file = str(file).replace(".dbc",".json")
        dbcToJsonConverter(file)
        with open(str(json_file), "w") as outputfile:
            outputfile.write(json.dumps(jtemp))

main()