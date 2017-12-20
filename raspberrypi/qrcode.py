#!/usr/bin/python 
import os, signal, subprocess

def read():
    zbarcam=subprocess.Popen("zbarcam --raw --nodisplay /dev/video0", stdout = subprocess.PIPE, shell = True, preexec_fn=os.setsid)
    print "cam successfully started"
    while True:
        qrcodetext = zbarcam.stdout.readline()
        if qrcodetext != "":
            print "Success"
            
            break
    os.killpg(zbarcam.pid, signal.SIGTERM)
    print "cam successfully stopped"
    return qrcodetext
