// install nodejs, express 
const port = 8000;
const express = require('express'),
    app = express(),
    server = app.listen(port, () => console.log(`Listening on port ${port}`));

app.use(express.json());

app.get('/', (req, res) => {
  res.status(200);
  res.send('Hello world!');
  res.end();
});

app.post('/', (req, res) => {
  res.status(201);
  res.send('Hello POST!');
  res.end();
})
