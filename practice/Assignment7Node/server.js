const express = require('express');
const app = express();
const port = 3000;

app.set('view engine', 'ejs');
app.use(express.urlencoded({ extended: true }));

// Routes
/* This code sets up a route for the root URL ('/') using the HTTP GET method. When a user navigates to
the root URL, the server will respond by rendering the 'index' template using the EJS view engine.
The second argument passed to the 'render' method is an object containing data to be passed to the
template. In this case, it passes an initial value of 'result' as null. */
app.get('/', (req, res) => {
    res.render('index', { result: null }); // Pass an initial value of 'result' as null
  });

/* This code sets up a route for the root URL ('/') using the HTTP POST method. When a user submits a
form with two numbers and an operation, the server will respond by calculating the result of the
operation and rendering the 'index' template using the EJS view engine. The calculated 'result' is
passed to the template as an object property. The code uses the 'body-parser' middleware to parse
the form data and extract the values of 'num1', 'num2', and 'operation' from the request body. It
then performs the appropriate arithmetic operation based on the value of 'operation' and assigns the
result to the 'result' variable. Finally, it renders the 'index' template with the calculated
'result' passed as an object property. */
app.post('/', (req, res) => {
  const num1 = parseInt(req.body.num1);
  const num2 = parseInt(req.body.num2);
  const operation = req.body.operation;
  let result;

  switch (operation) {
    case 'add':
      result = num1 + num2;
      break;
    case 'subtract':
      result = num1 - num2;
      break;
    case 'multiply':
      result = num1 * num2;
      break;
    case 'divide':
      result = num1 / num2;
      break;
    default:
      result = 'Invalid operation';
  }

  res.render('index', { result }); // Pass the calculated 'result' to the template
});

app.listen(3000, () => {
  console.log(`Server running on http://localhost:3000`);
});
