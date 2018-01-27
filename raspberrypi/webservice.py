import requests

global host
global session

#update the deployment url here
host = 'https://radiant-land-185414.appspot.com'

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
