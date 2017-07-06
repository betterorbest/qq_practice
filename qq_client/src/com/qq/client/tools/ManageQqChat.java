package com.qq.client.tools;

import java.awt.Window;
import java.util.HashMap;

import com.qq.client.view.QqChat;

public class ManageQqChat {
	private static HashMap hm = new HashMap<String, QqChat>();
	public static void addQqChat(String loginIdAndFriendId, QqChat qqChat) {
		// 键值是自身Id + “ ” + 朋友 Id
		hm.put(loginIdAndFriendId, qqChat);
	}
	
	public static QqChat getQqChat(String loginIdAndFriendId) {
		return (QqChat) hm.get(loginIdAndFriendId);
	}
	
	public static void removeQqChat(String loginIdAndFriendId) {
		hm.remove(loginIdAndFriendId);
	}
	
	public static void closeAllQqChat() {
		for (Object qqChat : hm.values()) {
			((QqChat)qqChat).dispose();
		}
	}
}
