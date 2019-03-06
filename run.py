import os
import subprocess as s
import sys

if(len(sys.argv) != 2):
    print("Erreur format => python3 run.py <file>")
    exit(1)


file = sys.argv[1]

# ================= Generate ToProlog ================= # 
try:
    parsed = str(s.check_output(["java", "-cp", "bin/", "ToProlog", file], stderr=s.STDOUT ))
except:
    print("File probably not found or can't be opened : " + file)
    exit(1)

if "error" in str(parsed).lower():
    print("Error when parsing")
    exit(1)

# ================= Check types ================= # 
parsed = parsed[2:len(parsed)-3]

try:
    parsed = str(s.check_output(["swipl", "-s", "Typage/prog.pl", parsed ], stderr=s.STDOUT ))
except:
    print("Error on type check")
    exit(1)


# ================= Eval ================= # 
print(str(s.check_output(["java", "-cp", "bin/", "Eval", file], stderr=s.STDOUT ).decode("utf-8")))
