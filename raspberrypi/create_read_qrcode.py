#!/usr/bin/python

import qrcode

while (True):
    print "1. qrcode creation"
    print "2. qrcode read"
    print "3. exit"

    select=int(raw_input("Your choice : "))
    if select == 1:
        qrcode.create()
    elif select == 2:
        result = qrcode.read().strip()
        print result
    elif seelct == 3:
        print "exiting the program"
        break
