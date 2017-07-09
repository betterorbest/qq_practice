package com.qq.server.model;

import java.util.HashMap;
import java.util.Iterator;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class ManageClientThread {
	static HashMap hm = new HashMap<String, ServerConClientThread>();
	
	public static void addClientThread(String uid, ServerConClientThread clientThread) {
		hm.put(uid, clientThread);
	}
	
	public static ServerConClientThread getConClientThread(String uid) {
		return (ServerConClientThread)hm.get(uid);
	}
	
	public static void removeClientThread(String uid) {
		hm.remove(uid);
	}
	
	public static boolean hasClientThread(String uid) {
		return hm.get(uid) != null;
	}
	
	public static String getAllOnLineUserId() {
		Iterator it = hm.keySet().iterator();
		String res = "";
		while(it.hasNext()) {
			res += it.next().toString() + " ";
		}
		
		return res; 
	}
	
}
