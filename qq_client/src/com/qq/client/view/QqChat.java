/*
 * 好友聊天界面
 */
package com.qq.client.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qq.client.model.QqClientConServer;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqChat;
import com.qq.common.Message;
import com.qq.common.MessageType;

public class QqChat extends JFrame implements ActionListener, WindowListener{

	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;
	
	public QqChat(String ownerId, String friendId) {
		// TODO Auto-generated constructor stub
		this.ownerId = ownerId;
		this.friendId = friendId;
		jta = new JTextArea();
		jta.setEditable(false);
		jtf = new JTextField(15);
		jb = new JButton("发送");
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta, "Center");
		this.add(jp, "South");
		this.setIconImage(new ImageIcon("image/qq.gif").getImage());
		this.setTitle(ownerId + " 你正在和" + friendId + "聊天");
		this.setSize(300, 200);
		this.setVisible(true);
		this.addWindowListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void showMessage(Message m) {
		String info = m.getSender() + "对你说 : " + m.getCon() + "\r\n";
		this.jta.append(info);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqChat qqChat = new QqChat("1", "2");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb) {
			Message m = new Message();
			m.setMesType(MessageType.message_communication_message);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			
			jta.append("你对" + m.getGetter() + "说: " + jtf.getText() + "\r\n");
			jtf.setText("");
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread().getSocket().getOutputStream());
				oos.writeObject(m);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method
		System.out.println(ownerId + "'s qqChat exit");
		ManageQqChat.removeQqChat(ownerId + " " + friendId);
	}
	
}
