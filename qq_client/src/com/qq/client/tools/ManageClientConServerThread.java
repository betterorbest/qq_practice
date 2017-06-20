package com.qq.client.tools;

import java.util.HashMap;

public class ManageClientConServerThread {
	
	private static ClientConServerThread clientConServerThread;

	public static void addClientConServerThread(ClientConServerThread ccst) {
		clientConServerThread = ccst;
	}
	
	public static ClientConServerThread getClientConServerThread() {
		return clientConServerThread;
	}
}
