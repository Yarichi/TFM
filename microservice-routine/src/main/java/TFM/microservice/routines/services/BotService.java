package TFM.microservice.routines.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import TFM.microservice.routines.VO.UserChatIdVO;
import TFM.microservice.routines.constants.URLConstants;
import TFM.microservice.routines.repositories.WebUserChatIdRepository;

@Component
public class BotService {
	@Value("${bot.url}")
	private String bot_url;
	@Value("${chat.url}")
	private String chat_url;
	@Autowired
	private WebUserChatIdRepository repository_chat;

	public Boolean sendMessage(String message, String mission_id, String alias) throws Exception {
		String aux_url = this.bot_url + URLConstants.URL_BOT_MESSAGE;
		URL chat_urll = new URL(this.chat_url + String.format("/webUser/%s/chatId", alias));
		HttpURLConnection con = (HttpURLConnection) chat_urll.openConnection();
		UserChatIdVO chat_id = null;
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		
		con.disconnect();
		Integer status = con.getResponseCode();
		if(status== 200) {
			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(content.toString()).getAsJsonObject();
			Gson gson = new Gson();
			chat_id = gson.fromJson(json, UserChatIdVO.class);
		}

		String body =  String.format("{\"chat_id\": \"%s\",\"message\":\"%s\",\"mission\":\"%s\"}", chat_id.getChat_id(), message, mission_id);
		URL url = new URL(aux_url);
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type","application/json"); 
		byte[] outputInBytes = body.getBytes("UTF-8");
		OutputStream os = con.getOutputStream();
		os.write( outputInBytes );    
		os.close();
		status = con.getResponseCode();
		
		con.disconnect();
		return status == 200;
	}

}
