#!/usr/bin/python
import os, signal, subprocess
import requests
from datetime import datetime

global url

#update the deployment url here
url = 'https://radiant-land-185414.appspot.com/rest/students/attendances/update/'


def read():
    zbarcam=subprocess.Popen("zbarcam --raw --nodisplay /dev/video0", stdout=subprocess.PIPE, shell=True, preexec_fn=os.setsid)
    print "cam successfully started"
    # wait 2 minutes to scan the qr code else exit
    while True:
        qrcodetext = zbarcam.stdout.readline()
        if qrcodetext != "":
            break
    os.killpg(zbarcam.pid, signal.SIGTERM)
    print "cam successfully stopped"
    return qrcodetext


def tutorauth(data):
    global url
    #update the link of attendance server here
    url = url+data
    headers = {'content-type': 'application/json'}
    response = requests.post(url, headers=headers)
    return response.status_code


def postattendance(data):
    global url
    #update the link of attendance server here
    url = url+data
    headers = {'content-type': 'application/json'}
    response = requests.post(url, headers=headers)
    return response.status_code


def postpresented(data):
    global url
    # update the link of attendance server here
    url = url+data
    headers = {'content-type': 'application/json'}
    response = requests.post(url, headers=headers)
    return response.status_code