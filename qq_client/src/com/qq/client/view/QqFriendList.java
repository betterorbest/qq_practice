/*
 * 好友列表
 */
package com.qq.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import com.qq.client.model.QqClientConServer;
import com.qq.client.tools.ManageQqChat;
import com.qq.common.Message;

public class QqFriendList extends JFrame implements ActionListener, MouseListener, WindowListener{

	//卡片布局
	//第一张卡片
	JPanel jpfrd1, jpfrd2, jpfrd3;
	JButton jpfrd_jb1, jpfrd_jb2, jpfrd_jb3;
	JScrollPane jsp1;
	
	//第二张卡片
	JPanel jpstrg1, jpstrg2, jpstrg3;
	JButton jpstrg_jb1, jpstrg_jb2, jpstrg_jb3;
	JScrollPane jsp2;
	
	CardLayout cl;
	
	JLabel[] jlbs1;
	
	String ownerId;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqFriendList q = new QqFriendList("1");
	}

	public QqFriendList(String ownerId){
		//第一张卡片
		this.ownerId = ownerId;
		jpfrd_jb1 = new JButton("我的好友");
		jpfrd_jb2 = new JButton("陌生人");
		jpfrd_jb3 = new JButton("黑名单");
		jpfrd_jb2.addActionListener(this);
		jpfrd1 = new JPanel(new BorderLayout());
		
		jpfrd2 = new JPanel(new GridLayout(50, 1, 4, 4));
		jlbs1 = new JLabel[50];
		for(int  i = 0; i < jlbs1.length; ++i) {
			jlbs1[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			jlbs1[i].setEnabled(false);
			if(jlbs1[i].getText().equals(this.ownerId))
				jlbs1[i].setEnabled(true);
			jpfrd2.add(jlbs1[i]);
			jlbs1[i].addMouseListener(this);
		}
		
		jpfrd3 = new JPanel(new GridLayout(2, 1)); 
		jpfrd3.add(jpfrd_jb2);
		jpfrd3.add(jpfrd_jb3);
		
		jsp1 = new JScrollPane(jpfrd2);
		
		jpfrd1.add(jpfrd_jb1, "North");
		jpfrd1.add(jsp1, "Center");
		jpfrd1.add(jpfrd3, "South");
		
		//第二张卡片
		jpstrg_jb1 = new JButton("我的好友");
		jpstrg_jb1.addActionListener(this);
		jpstrg_jb2 = new JButton("陌生人");
		jpstrg_jb3 = new JButton("黑名单");
		jpstrg1 = new JPanel(new BorderLayout());
		
		jpstrg2 = new JPanel(new GridLayout(20, 1, 4, 4));
		JLabel[] jbls2 = new JLabel[20];
		for(int  i = 0; i < jbls2.length; ++i) {
			jbls2[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			jbls2[i].setEnabled(false);
			if(jbls2[i].getText().equals(this.ownerId))
				jbls2[i].setEnabled(true);
			jpstrg2.add(jbls2[i]);
			jbls2[i].addMouseListener(this);
		}
		
		jpstrg3 = new JPanel(new GridLayout(2, 1)); 
		jpstrg3.add(jpstrg_jb1);
		jpstrg3.add(jpstrg_jb2);
		
		jsp2 = new JScrollPane(jpstrg2);
		
		jpstrg1.add(jpstrg_jb3, "South");
		jpstrg1.add(jsp2, "Center");
		jpstrg1.add(jpstrg3, "North");
		
		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jpfrd1, "1");
		this.add(jpstrg1, "2");
		
		this.setTitle(ownerId);
		this.setSize(140, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		this.addWindowListener(this);
	
	}

	public void updateFriend(Message m) {
		String[] onlineFriend = m.getCon().split(" ");
		
		for(int i = 0; i < onlineFriend.length; ++i) {
			jlbs1[Integer.parseInt(onlineFriend[i]) - 1].setEnabled(true);
		}
	}
	
	public void updateOutFriend(Message m) {
		String[] onlineFriend = m.getCon().split(" ");
		
		for(int i = 0; i < onlineFriend.length; ++i) {
			jlbs1[Integer.parseInt(onlineFriend[i]) - 1].setEnabled(false);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jpfrd_jb2) {
			cl.show(this.getContentPane(), "2");
		}else if(e.getSource() == jpstrg_jb1) {
			cl.show(this.getContentPane(),  "1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2) {
			String friendNo = ((JLabel)e.getSource()).getText();
			QqChat qqChat = new QqChat(this.ownerId, friendNo);
			
			ManageQqChat.addQqChat(this.ownerId + " " + friendNo , qqChat);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("friend list exit");
		new QqClientConServer().sendLoginOutInfoToServer(this.ownerId);
		ManageQqChat.closeAllQqChat();
		System.exit(0);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
