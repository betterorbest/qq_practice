/*
 * �ͻ������ӷ�����
 */
package com.qq.client.model;

import java.io.Closeable;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

public class QqClientConServer {

	public static Socket s = null;
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
			
			if(ms.getMesType().equals(MessageType.message_login_succeed)) {
				ClientConServerThread ccst = new ClientConServerThread(s);
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(ccst);
				b = true;
			}
			else {
				b = false;
			}
		} catch(ConnectException e) {
			
			System.out.println("�����������쳣");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("�ͻ��˵�½�쳣");
			
			e.printStackTrace();
			
		} finally {
			
		}
		
		
		return b;
		
	}
	
	

}
