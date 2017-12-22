#!/usr/bin/python 
import os, signal, subprocess
import json
import requests
import datetime

def read():
    #note teh time at which the qrcode read starts
    qrcodetext = ''
    now = datetime.datetime.now()
    zbarcam=subprocess.Popen("zbarcam --raw --nodisplay /dev/video0", stdout=subprocess.PIPE, shell=True, preexec_fn=os.setsid)
    print "cam successfully started"
    while True:
        qrcodetext = zbarcam.stdout.readline()
        if qrcodetext != "":
            print "Success" 
            break
        if str(datetime.datetime.now()) is str(now + datetime.timedelta(minutes=2)):
            print "No qrcode scanned in last 2 minutes. Exiting..."
    os.killpg(zbarcam.pid, signal.SIGTERM)
    print "cam successfully stopped"
    return qrcodetext


def postattendance(data):
    url = 'https://radiant-land-185414.appspot.com/rest/students/attendances/update/'+data
    headers = {'content-type': 'application/json'}
    response = requests.post(url, headers=headers)
    return response
