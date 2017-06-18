package com.qq.server.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.Message;
import com.qq.common.MessageType;

public class ServerConClientThread extends Thread{
	Socket s;
	public ServerConClientThread(Socket s) {
		//服务器与客户端的连接赋给s
		this.s = s;
		
	}
	
	public void notifyOther(String myId) {
		HashMap hm = ManageClientThread.hm;
		Iterator it = hm.keySet().iterator();
		while(it.hasNext()) {
			Message m = new Message();
			m.setCon(myId);
			m.setMesType(MessageType.message_return_online_friend);
			String onlineFriend = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getConClientThread(onlineFriend).s.getOutputStream());
				m.setGetter(onlineFriend);
				oos.writeObject(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		while(true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				
				if(m.getMesType().equals(MessageType.message_communication_message)) {
					ServerConClientThread scct = ManageClientThread.getConClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(scct.s.getOutputStream());
					oos.writeObject(m);
					System.out.println(m.getSender() + " to " + m.getGetter() + "say: " + m.getCon());
				}else if(m.getMesType().equals(MessageType.message_get_online_friend)) {
					String res = ManageClientThread.getAllOnLineUserId();
					Message m1 = new Message();
					m1.setMesType(MessageType.message_return_online_friend);
					m1.setCon(res);
					m1.setGetter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m1);
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
