import os
import subprocess as s

for i in range(18):
    # file = "APS0_"+str(i).zfill(2)+".aps"
    file = "prog"+str(i).zfill(3)+".aps"
    print("Running test : " + file + " ... ", end='')
    try:
        parsed = str(s.check_output(["java", "-cp", "bin/", "aps.parser.ToProlog", "Test/"+file], stderr=s.STDOUT ))
    except:
        print("Error on parsing (or file not found) : " + file)
        continue

    if "error" in str(parsed).lower():
        print("Error when parsing")
        continue


    parsed = parsed[2:len(parsed)-3]

    try:
        parsed = str(s.check_output(["swipl", "-s", "Typage/prog.pl", parsed ], stderr=s.STDOUT ))
        print("OK", flush=True)
    except:
        print("Error on type check")
        continue

for i in range(21):
    file = "Test1/prog1"+str(i).zfill(2)+".aps"
    print("Running test : " + file + " ... ", end='')
    try:
        parsed = str(s.check_output(["java", "-cp", "bin/", "aps.parser.ToProlog", file], stderr=s.STDOUT ))
    except:
        print("Error on parsing (or file not found) : " + file)
        continue

    if "error" in str(parsed).lower():
        print("Error when parsing")
        continue


    parsed = parsed[2:len(parsed)-3]

    try:
        parsed = str(s.check_output(["swipl", "-s", "Typage/prog.pl", parsed ], stderr=s.STDOUT ))
        print("OK", flush=True)
    except:
        print("Error on type check")
        continue

for i in range(8):
    file = "Test2/prog2"+str(i).zfill(2)+".aps"
    print("Running test : " + file + " ... ", end='')
    try:
        parsed = str(s.check_output(["java", "-cp", "bin/", "aps.parser.ToProlog", file], stderr=s.STDOUT ))
    except:
        print("Error on parsing, this file does not respect the syntax (or file not found) : " + file)
        continue

    if "error" in str(parsed).lower():
        print("Error when parsing")
        continue


    parsed = parsed[2:len(parsed)-3]

    try:
        parsed = str(s.check_output(["swipl", "-s", "Typage/prog.pl", parsed ], stderr=s.STDOUT ))
        print("OK", flush=True)
    except:
        print("Error on type check")
        continue