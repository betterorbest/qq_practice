/*
 * 客户端连接服务器
 */
package com.qq.client.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.User;

public class QqClientConServer {

	public Socket s;
//	public MyQqClientConServer() {
//		// TODO Auto-generated constructor stub
//		try {
//			Socket s = new Socket("127.0.0.1", 9999);
//			
//		}catch(Exception e) {
//			 e.printStackTrace();
//		}finally {
//			
//		}
//	}
	public boolean sendLoginInfoToServer(Object o) {
		boolean b = false;
		try {
			s = new Socket("127.0.0.1", 9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o); 
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			
			if(ms.getMesType().equals("1")) {
				ClientConServerThread ccst = new ClientConServerThread(s);
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), ccst);
				b = true;
			}
			else {
				b = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
		}
		
		return b;
		
	}
	

}
