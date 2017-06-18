package com.qq.client.model;

import com.qq.common.User;

public class QqClientUser {

	public boolean checkUser(User u) {
		return new QqClientConServer().sendLoginInfoToServer(u);
		 
	}

}
