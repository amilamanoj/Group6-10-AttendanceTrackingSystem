#!/usr/bin/python

import qrcode
import pifacecad
import sys

cad = pifacecad.PiFaceCAD()

def main():
    while True:
        cad.lcd.clear()
        cad.lcd.write('Waiting for QRcode')
        #Read QR code
        result = qrcode.read().strip()
        if not result:
            cad.lcd.write('No QRcode found. Exiting... ')
            sys.exit()
        cad.lcd.clear()
        cad.lcd.write(result)

        '''
        TO-DO before calling the REST API to update specific student's attendence
        prompt the tutor to enter if the student has presented.
    
        if a student has presented before then prompting tutor repeatedly upcoming
        weeks should be avoided.
        '''
        #call to backend
        status = qrcode.postattendance(result)
        cad.lcd.clear() 
        if '200' in status:
            cad.lcd.write('SUCCESS')
        else:
            cad.lcd.write('FAILURE')
        break



if __name__ == "__main__":
    main()




