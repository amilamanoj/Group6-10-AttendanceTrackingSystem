#!/usr/bin/python

import qrcode
import webservice
import pifacecad
import sys
import time

cad = pifacecad.PiFaceCAD()


def tutorAuthentication():
    cad.lcd.clear()
    cad.lcd.write("Tutor Authentication")
    cad.lcd.set_cursor(0, 1)
    cad.lcd.write('Waiting for QR')
    result = qrcode.read().strip()
    if not result:
        cad.lcd.set_cursor(0, 1)
        cad.lcd.write("NO QR code found")
        sys.exit()
    cad.lcd.set_cursor(0, 1)
    cad.lcd.write(result)

    # call auth API for tutor here
    webservice.tutorauth(result)

    cad.lcd.set_cursor(0, 1)
    cad.lcd.write("TOKEN STORED")

    time.sleep(5)
    return


def mark_attendance():
    cad.lcd.clear()
    # prompt what to do when done marking attendance
    cad.lcd.write('Press 3 when done')
    while True:
        time.sleep(5)
        if cad.switches[3].value == 1:
            break
        cad.lcd.set_cursor(0, 1)
        cad.lcd.write('Waiting for QRcode')

        # Read QR code
        result = qrcode.read().strip()
        if not result:
            cad.lcd.clear()
            cad.lcd.write('No QRcode found. Exiting... ')
        cad.lcd.set_cursor(0, 1)
        cad.lcd.write(result)

        # call to backend to post attendance
        status = webservice.postattendance(result)
        print status

        cad.lcd.set_cursor(0, 1)
        if 200 == status:
            cad.lcd.write('Att SUCCESS')
        else:
            cad.lcd.write('Att FAILURE')
    return True


def mark_presented():
    cad.lcd.clear()
    # prompt what to do when done marking Presented status
    cad.lcd.write('Press 3 when done')
    while True:
        time.sleep(5)
        if cad.switches[3].value == 1:
            break
        cad.lcd.set_cursor(0, 1)
        cad.lcd.write('Waiting for QRcode')

        # Read QR code
        result = qrcode.read().strip()
        if not result:
            cad.lcd.clear()
            cad.lcd.write('No QRcode found. Exiting... ')
        cad.lcd.set_cursor(0, 1)
        cad.lcd.write(result)

        # call to backend to post presented
        status = webservice.postpresented(result)

        cad.lcd.set_cursor(0, 1)
        if 200 == status:
            cad.lcd.write('Prs SUCCESS')
        else:
            cad.lcd.write('Prs FAILURE')

    return True


def main():
    # FLAGs for tracking which option was selected by the tutor
    att = 0
    prs = 0
    marked_attendance = False
    marked_presented = False

    qrcode.startcam()
    # tutor auth
    tutorAuthentication()

    while True:
        cad.lcd.clear()
        cad.lcd.write('Press 1 attendance')
        cad.lcd.set_cursor(0, 1)
        cad.lcd.write('Press 2 presentation')

        while True:
            if cad.switches[1].value == 1:
                # reset the button
                cad.switches[1].value = 0
                att = 1
                break
            if cad.switches[2].value == 1:
                cad.switches[2].value = 0
                prs = 1
                break

        if att == 1:
            # set the value to 0
            att = 0
            print('Marking attendance')
            marked_attendance = mark_attendance()
            
        if prs == 1:
            # set the value to 0
            prs = 0
            print('Marking presented')
            marked_presented = mark_presented()

        if marked_presented and marked_attendance:
            cad.lcd.clear()
            cad.lcd.write('Exit?')
            cad.lcd.set_cursor(0, 1)
            cad.lcd.write('5 - Yes 4 - No')

            if cad.switches[5].value == 1:
                cad.lcd.clear()
                cad.lcd.write('Exiting...')
                qrcode.stopcam()
            else:
                # reset button
                cad.switches[4].value = 0


if __name__ == "__main__":
    main()
