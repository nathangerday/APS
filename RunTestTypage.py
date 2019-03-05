import os
import subprocess as s

for i in range(18):
    # file = "APS0_"+str(i).zfill(2)+".aps"
    file = "prog"+str(i).zfill(3)+".aps"
    print("Running test : " + file + " ... ", end='')
    try:
        parsed = str(s.check_output(["java", "-cp", "bin/", "ToProlog", "Test/"+file], stderr=s.STDOUT ))
    except:
        print("File probably not found or can't be opened : " + file)
        continue

    if "error" in str(parsed).lower():
        print("Error when parsing")
        continue


    parsed = parsed[2:len(parsed)-3]

    try:
        parsed = str(s.check_output(["swipl", "-s", "Typage/prog.pl", parsed ], stderr=s.STDOUT ))
        print("OK")
    except:
        print("Error on type check")
        continue