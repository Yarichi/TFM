const express = require('express');
const bodyParser = require('body-parser');
const packageInfo = require('./package.json');


const app = express();
app.use(bodyParser.json());

app.get('/', function (req, res) {
  res.json({ version: packageInfo.version });
});

var server = app.listen("8084", "0.0.0.0", () => {
  const host = server.address().address;
  const port = server.address().port;
  console.log('Web server started at http://%s:%s', host, port);
});

module.exports = (bot) => {
  app.post('/message', (req, res) => {
	res.setHeader('Content-Type', 'application/json')
	const opts = {
    reply_markup: JSON.stringify({
      keyboard: [
        ['Mission ' + req.body.mission + ' completed'],
        ['Mission ' + req.body.mission + ' incompleted']
      ]
    })
  };
	console.log("El mensaje %s", req.body.message);
    bot.sendMessage(req.body.chat_id, req.body.message, opts);
    res.sendStatus(200);
  });
};
