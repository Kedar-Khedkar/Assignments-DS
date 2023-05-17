import requests

url = 'http://localhost:5000/hello'
response = requests.get(url)
print("Response from web service:", response.text)