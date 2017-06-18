/*
 * 服务器管理控制界面
 */
package com.qq.server.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.qq.server.model.MyQqServer;

public class MyServerFrame extends JFrame implements ActionListener{

	JPanel jp1;
	JButton jb1, jb2;
	
	public MyServerFrame() {
		// TODO Auto-generated constructor stub
		jp1 = new JPanel();
		jb1 = new JButton("启动服务器");
		jb1.addActionListener(this);
		jb2 = new JButton("关闭服务器");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame myServerFrame = new MyServerFrame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jb1) {
			new MyQqServer();
			jb1.setEnabled(false);
		}
	}

}
