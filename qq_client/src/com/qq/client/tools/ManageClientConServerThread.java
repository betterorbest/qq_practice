package com.qq.client.tools;

import java.util.HashMap;

public class ManageClientConServerThread {
	
	
	private static HashMap hm = new HashMap<String, ClientConServerThread>();
	
	public static void addClientConServerThread(String qqId, ClientConServerThread ccst) {
		hm.put(qqId, ccst);
	}
	
	public static ClientConServerThread getClientConServerThread(String qqId) {
		return (ClientConServerThread)hm.get(qqId);
	}
}
