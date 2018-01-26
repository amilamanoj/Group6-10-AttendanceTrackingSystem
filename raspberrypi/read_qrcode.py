#!/usr/bin/python

import qrcode
import pifacecad
import sys
import time

cad = pifacecad.PiFaceCAD()

def tutorAuthentication():
    cad.lcd.clear()
    cad.lcd.write("Tutor Authentication")
    cad.lcd.set_cursor(1, 0)
    cad.lcd.write('Waiting for QR')
    result = qrcode.read().strip()
    if not result:
        cad.lcd.set_cursor(1, 0)
        cad.lcd.write("NO QR code found")
        sys.exit()
    cad.lcd.set_cursor(1, 0)
    cad.lcd.write(result)

    # call auth API for tutor here
    status = qrcode.tutorauth(result)

    cad.lcd.set_cursor(1, 0)
    if '200' == status:
        cad.lcd.write("AUTH SUCCESS")
        return
    else:
        cad.lcd.write("AUTH FAILURE.exiting...")
        sys.exit()

def main():
    # tutor auth
    tutorAuthentication()

    while True:
        cad.lcd.clear()
        #prompt the tutor to mark attendance or presented status of student
        cad.lcd.write('Press 1 attendance')
        #switch to next line
        cad.lcd.set_cursor(1, 0)
        cad.lcd.write('Press 2 presentation')

        #wait for tutor input
        if cad.switches[1].value == 1:
            cad.lcd.clear()
            # prompt what to do when done marking attendance
            cad.lcd.write('Press 3 when done')
            while True:
                if cad.switches[3].value == 1:
                    break
                cad.lcd.set_cursor(1, 0)
                cad.lcd.write('Waiting for QRcode')

                #Read QR code
                result = qrcode.read().strip()
                if not result:
                    cad.lcd.clear()
                    cad.lcd.write('No QRcode found. Exiting... ')
                cad.lcd.set_cursor(1, 0)
                cad.lcd.write(result)

                # call to backend to post attendance
                status = qrcode.postattendance(result)

                cad.lcd.set_cursor(1, 0)
                if '200' == status:
                    cad.lcd.write('SUCCESS')
                else:
                    cad.lcd.write('FAILURE')
                #sleep 2 seconds after prompting user about the SUCCESS/ FAILURE message
                time.sleep(2)

        if cad.switches[2].value == 1:
            cad.lcd.clear()
            # prompt what to do when done marking Presented status
            cad.lcd.write('Press 3 when done')
            while True:
                if cad.switches[3].value == 1:
                    break
                cad.lcd.set_cursor(1, 0)
                cad.lcd.write('Waiting for QRcode')

                # Read QR code
                result = qrcode.read().strip()
                if not result:
                    cad.lcd.clear()
                    cad.lcd.write('No QRcode found. Exiting... ')
                cad.lcd.set_cursor(1, 0)
                cad.lcd.write(result)

                # call to backend to post presented
                status = qrcode.postpresented(result)

                cad.lcd.set_cursor(1, 0)
                if '200' == status:
                    cad.lcd.write('SUCCESS')
                else:
                    cad.lcd.write('FAILURE')
                time.sleep(2)


if __name__ == "__main__":
    main()




