import glob, os
import json
# Regex
import re

def convertASCtoReadAble(file, jsonData):
   source = open(file, 'r')
   Lines = source.readlines()
   for line in Lines:
      tempdata = re.search('(?:\s*)(\d{1,}.\d{1,})(?:\s\d\s\s)(\w*)(?:\s{2,})(Rx|Tx)(?:\s*)((?:\w{1,2}\s{1,2})*)(?:.*)(?:ID\s=\s)(\d{1,5})', line)
      if tempdata:
         # time_data  = str(tempdata.group(1))
         # hex_id     = str(tempdata.group(2))
         # TxRx       = str(tempdata.group(3))
         data       = str(tempdata.group(4))
         id         = str(tempdata.group(5))
         print(jsonData[id]["MESSAGE_NAME"] + "  :  " + data)
      else:
         print(line)

def main():
   os.chdir("./")
   for json_file in glob.glob("*.json"):
      print(json_file)
   # jsonFileInput = input("Select Json File: ")
   jsonFileInput = "S210_IVN_Communication_Matrix_31054.json"
   with open(jsonFileInput,'r+') as file:
      jsonData = json.load(file)
   for asc_file in glob.glob("*.asc"):
      print(asc_file)
   # ascFileInput = input("Select ASC File: ")
   ascFileInput = "2022-10-17_11-11-37.asc"
   convertASCtoReadAble(ascFileInput, jsonData)

main()