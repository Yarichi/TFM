const token = process.env.TOKEN;
var request = require("request");
const Bot = require('node-telegram-bot-api');
var querystring = require('querystring');
let bot;

let regexMessageMission = /^Mission with ID (.*) has been assigned to you, the user with alias (.*) will come by (.*) at (.*)$/;
let regexMessageMissionCompleted = /^Mission (.*) completed$/;
let regexMessageMissionIncompleted = /^Mission (.*) incompleted$/;

if(process.env.NODE_ENV === 'production') {
  bot = new Bot(token);
  bot.setWebHook(process.env.HEROKU_URL + bot.token);
}
else {
  bot = new Bot(token, { polling: true });
}

console.log('Bot server started in the ' + process.env.NODE_ENV + ' mode');

// bot.on('message', (msg) => {
//   const name = msg.from.first_name;
//   console.log(msg);
//   bot.sendMessage(msg.chat.id, 'Hello, ' + name + '!' + msg.chat.id).then(() => {
//     // reply sent!
//   });
//   if (msg.text == '/start'){
// 	bot.sendMessage(msg.chat.id, 'Guardando información de conexión').then(() => {
// 		console.log('Guardando información de conexión');
// 	});
// 	var options = {
// 	  url: 'http://localhost:8083/bot',
// 	  method: 'POST',
// 	  json: {
// 		  alias: msg.from.username,
// 		  chat_id: msg.chat.id
// 	  },
// 	  headers: {
// 		'Content-Type': 'application/json;charset=UTF-8'
// 	  }
// 	};
//   request(options, function(error, response, body) {
// 	  console.log(body);
// 	});
//   }
// });

bot.onText(/^\/start$/, function onStartText(msg, exec) {
	var options = {
	  url: 'http://localhost:8083/bot',
	  method: 'POST',
	  json: {
		  alias: msg.from.username,
		  chat_id: msg.chat.id
	  },
	  headers: {
		'Content-Type': 'application/json;charset=UTF-8'
	  }
	};
  request(options, function(error, response, body) {
	  console.log(body);
	});
  bot.sendMessage(msg.chat.id, 'Connection info stored');	
});

bot.onText(regexMessageMissionCompleted, function onMissionComplete(msg, exec) {
	var options = {
	  url: 'http://localhost:8082/routines/missions/'+exec[1]+'/completed',
	  method: 'POST',
	  json: {
		  alias: msg.from.username
	  },
	  headers: {
		'Content-Type': 'application/json;charset=UTF-8'
	  }
	};
  request(options, function(error, response, body) {
	  console.log(body);
  });
  
  const opts = {
    reply_markup: JSON.stringify({
      hide_keyboard: true
    })
  };
  bot.sendMessage(msg.chat.id, 'Mission completed reported', opts);
});


bot.onText(regexMessageMissionIncompleted, function onMissionIncomplete(msg, exec) {
	var options = {
	  url: 'http://localhost:8083/routines/missions/'+exec[1]+'/incompleted',
	  method: 'POST',
	  json: {
		  alias: msg.from.username
	  },
	  headers: {
		'Content-Type': 'application/json;charset=UTF-8'
	  }
	};
  request(options, function(error, response, body) {
	  console.log(body);
  });
  
  const opts = {
    reply_markup: JSON.stringify({
      hide_keyboard: true
    })
  };

  bot.sendMessage(msg.chat.id, 'Mission incompleted reported', opts);
});

module.exports = bot;
