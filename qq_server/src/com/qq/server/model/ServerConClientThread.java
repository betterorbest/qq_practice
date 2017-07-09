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
	boolean isRunning;
	public ServerConClientThread(Socket s) {
		//服务器与客户端的连接赋给s
		this.s = s;
		this.isRunning = true;
	}
	
	public void notifyOther(String myId, String messageType) {
		HashMap hm=ManageClientThread.hm;
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()) 
		{
			Message m=new Message();
			m.setMesType(messageType);
			m.setCon(myId);
			String onLineUserId=it.next().toString();
			try {
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getConClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		while(isRunning) {
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				
				if(m.getMesType().equals(MessageType.message_communication_message)) {
					ServerConClientThread scct = ManageClientThread.getConClientThread(m.getGetter());
					if(scct != null) {
						ObjectOutputStream oos = new ObjectOutputStream(scct.s.getOutputStream());
						oos.writeObject(m);
					}
					else
						System.out.println(m.getGetter() + " has exited");
					System.out.println(m.getSender() + " to " + m.getGetter() + "say: " + m.getCon());
				}else if(m.getMesType().equals(MessageType.message_get_online_friend)) {
					String res = ManageClientThread.getAllOnLineUserId();
					Message m1 = new Message();
					m1.setMesType(MessageType.message_return_online_friend);
					m1.setCon(res);
					m1.setGetter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m1);
				}else if(m.getMesType().equals(MessageType.message_login_out)) {
					this.isRunning = false;
					ManageClientThread.removeClientThread(m.getSender());
					notifyOther(m.getSender(), MessageType.message_login_out);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
