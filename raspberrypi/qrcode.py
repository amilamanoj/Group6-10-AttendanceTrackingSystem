#!/usr/bin/python
import os, signal, subprocess
import requests
from datetime import datetime

global host
global session

#update the deployment url here
host = 'https://radiant-land-185414.appspot.com'


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
    global session
    session = data


def postattendance(data):
    global host
    global session
    attendanceUrl = host + '/rest/students/attendances/update'
    headers = {'content-type': 'application/json'}
    cookieName,cookieValue = session.split('=')
    sessionCookie = {cookieName: cookieValue}
    response = requests.post(attendanceUrl, headers=headers, cookies=sessionCookie, json={'token': data,'attended': 'true', 'presented':'false'})
    return response.status_code


def postpresented(data):
    global host
    global session
    attendanceUrl = host + '/rest/students/attendances/update'
    headers = {'content-type': 'application/json'}
    cookieName,cookieValue = session.split('=')
    sessionCookie = {cookieName: cookieValue}
    response = requests.post(attendanceUrl, headers=headers, cookies=sessionCookie, json={'token': data,'attended': 'true', 'presented':'true'})
    return response.status_code
