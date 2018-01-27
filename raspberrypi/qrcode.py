#!/usr/bin/python
import os, signal, subprocess


global zbarcam


def startcam():
    global zbarcam
    zbarcam = subprocess.Popen("zbarcam --raw --nodisplay /dev/video0", stdout=subprocess.PIPE, shell=True,
                               preexec_fn=os.setsid)
    print "cam successfully started"


def stopcam():
    global zbarcam
    os.killpg(zbarcam.pid, signal.SIGTERM)
    print "cam successfully stopped"


def read():
    global zbarcam
    # wait 2 minutes to scan the qr code else exit
    while True:
        qrcodetext = zbarcam.stdout.readline()
        if qrcodetext != "":
            print qrcodetext
            break
    return qrcodetext
