package com.qq.client.tools;

import com.qq.client.view.QqFriendList;

public class ManageQqFriendList {
	private static QqFriendList qqFriendList;
	public static void addQqFriendList(QqFriendList qqfl) {
		qqFriendList = qqfl;
	}
	
	public static QqFriendList getQqFriendList() {
		return qqFriendList;
	}
	
}
