/*
 * qq服务器，监听客户端连接
 */
package com.qq.server.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

public class MyQqServer {

	public MyQqServer() {
		// TODO Auto-generated constructor stub
		try {
			System.out.println("服务器在9999监听");
			ServerSocket ss = new ServerSocket(9999);
		
			while(true) {
				Socket s = ss.accept();
				
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User)ois.readObject();
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("1") && !ManageClientThread.hasClientThread(u.getUserId())) {
					m.setMesType("1");
					oos.writeObject(m);	
					
					ServerConClientThread serverConClientThread = new ServerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), serverConClientThread);
					serverConClientThread.start();
					serverConClientThread.notifyOther(u.getUserId(), MessageType.message_return_online_friend);
				}else {
					m.setMesType("2");
					oos.writeObject(m);	
					s.close();
				} 
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
