package TFM.microservice.webusers.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import TFM.microservice.webusers.VO.MessageBotVO;
import TFM.microservice.webusers.VO.PermissionsInputVO;
import TFM.microservice.webusers.VO.RolesInputVO;
import TFM.microservice.webusers.VO.UserChatIdVO;
import TFM.microservice.webusers.VO.WebUserInputVO;
import TFM.microservice.webusers.repositories.PermissionsRepository;
import TFM.microservice.webusers.repositories.RolesRepository;
import TFM.microservice.webusers.repositories.WebUserChatIdRepository;
import TFM.microservice.webusers.repositories.WebUserRepository;

@Component
public class BotService {
	
	@Autowired
	private WebUserRepository repository;
	
	@Autowired
	private WebUserChatIdRepository repository_chat;

	public Boolean setChatId(MessageBotVO message) {
		WebUserInputVO user = repository.findOneByTelegramAlias(message.getAlias());
		if (user != null) {
			UserChatIdVO aux;
			if ((aux = repository_chat.findOneByAlias(message.getAlias())) != null) {
				aux.setChat_id(message.getChat_id());
				repository_chat.save(aux);
				return true;
			}
			else {
				UserChatIdVO chat = new UserChatIdVO(message.getAlias(), message.getChat_id());
				repository_chat.save(chat);
				return true;
			}
		}
		return false;
	}

}
