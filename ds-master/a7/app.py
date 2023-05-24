import requests

def add_numbers(num1, num2):
    url = 'http://localhost:5000/add'  # Replace with the actual URL of the web service
    data = {
        'num1': num1,
        'num2': num2
    }
    response = requests.post(url, json=data)
    result = response.json()['result']
    return result

# Example usage
result = add_numbers(5, 10)
print(f"The result of adding 5 and 10 is: {result}")

