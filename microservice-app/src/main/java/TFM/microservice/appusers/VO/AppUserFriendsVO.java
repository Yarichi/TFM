package TFM.microservice.appusers.VO;

import java.util.ArrayList;
import java.util.List;

public class AppUserFriendsVO {
	private List<FriendVO> friends;

	
	public AppUserFriendsVO() {
		super();
		this.friends = new ArrayList<FriendVO>();
	}

	public List<FriendVO> getFriends() {
		return friends;
	}

	public void setFriends(List<FriendVO> friends) {
		this.friends = friends;
	}
	
	public void addFriend(FriendVO friend) {
		System.out.println(friend.toString());
		this.friends.add(friend);
	}

	
	
	
}
