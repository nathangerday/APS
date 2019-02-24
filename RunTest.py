import os

for i in range(9):
    file = "APS0_"+str(i).zfill(2)+".aps"
    print("Running test : " + file)
    cmd = "java ToProlog < Test/" + file
    cmd += " | awk '{print $1 \".\"}' | swipl -s Typage/prog.pl -g main_stdin"
    os.system(cmd)