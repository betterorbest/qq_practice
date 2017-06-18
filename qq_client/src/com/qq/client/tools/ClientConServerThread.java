package com.qq.client.tools;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;


public class ClientConServerThread extends Thread{
	private Socket socket;
	
	public Socket getSocket() {
		return socket;
	}

	public ClientConServerThread(Socket s) {
		this.socket = s;
	}
	
	public void run() {
		while(true) {
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				Message m = (Message)ois.readObject();
				
				System.out.println("服务器发来的消息: " + m.getSender() + " to " + m.getGetter() + ": " + m.getCon());
			
				if(m.getMesType().equals(MessageType.message_communication_message)) {
					QqChat qqChat = ManageQqChat.getQqChat(m.getGetter() + " " + m.getSender());
					qqChat.showMessage(m);
				}else if(m.getMesType().equals(MessageType.message_return_online_friend)) {
					String con = m.getCon();
					String[] friends = con.split(" ");
					String getter = m.getGetter();
					QqFriendList qqFriendList = ManageQqFriendList.getQqFriendList(getter);
					if(qqFriendList != null)
						qqFriendList.updateFriend(m);
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
}
