import requests
import json

url = 'http://localhost:5000/calculator'
data = {
    'num1': 5,
    'num2': 3,
    'operator': '+'
}
response = requests.post(url, json=data)
print("Response from web service:", response.text)
