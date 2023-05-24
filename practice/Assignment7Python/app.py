from flask import Flask, request

app = Flask(__name__)

@app.route('/hello')
def hello():
    return 'Hello, World!'

@app.route('/calculator', methods=['POST'])
def calculator():
    data = request.get_json()
    num1 = data.get('num1')
    num2 = data.get('num2')
    operator = data.get('operator')

    if operator == '+':
        result = num1 + num2
    elif operator == '-':
        result = num1 - num2
    elif operator == '*':
        result = num1 * num2
    elif operator == '/':
        if num2 != 0:
            result = num1 / num2
        else:
            return 'Error: Division by zero is not allowed.'
    else:
        return 'Error: Invalid operator.'

    return str(result)

if __name__ == '__main__':
    app.run()
