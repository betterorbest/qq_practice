/*
 *qq�ͻ��� ��½����  
 */
package com.qq.client.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.qq.client.model.QqClientUser;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

public class QqClientLogin extends JFrame implements ActionListener{
	//���山�������
	JLabel jbl1;
	
	//�����в������
	JTabbedPane jtp;
	JPanel jp2, jp3, jp4;
	JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1, jp2_jcb2;
	
	//�����ϲ������
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;
	
	public static void main(String[] args) {
		QqClientLogin qq = new QqClientLogin();
	}
	
	public QqClientLogin() {
		//������
		jbl1 = new JLabel(new ImageIcon("image/tou.gif"));
		
		//�����в�
		jp2 = new JPanel(new GridLayout(3, 3));
		jp2_jbl1 = new JLabel("QQ����", JLabel.CENTER);
		jp2_jbl2 = new JLabel("QQ����", JLabel.CENTER);
		jp2_jbl3 = new JLabel("��������", JLabel.CENTER);
		jp2_jbl3.setForeground(Color.red);
		jp2_jbl4 = new JLabel("�������뱣��", JLabel.CENTER);
		jp2_jb1 = new JButton(new ImageIcon("image/clear.gif"));
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1 = new JCheckBox("��˽��½");
		jp2_jcb2 = new JCheckBox("��ס����");
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		
		//�в�ѡ�
		jtp = new JTabbedPane();
		jtp.add("QQ����", jp2);
		jp3 = new JPanel();
		jtp.add("�ֻ�����", jp3);
		jp4 = new JPanel();
		jtp.add("�����ʼ�", jp4);
		
		//�ϲ�
		jp1 = new JPanel();
		jp1_jb1 = new JButton(new ImageIcon("image/denglu.gif"));
		jp1_jb1.addActionListener(this);
		jp1_jb2 = new JButton(new ImageIcon("image/quxiao.gif"));
		jp1_jb3 = new JButton(new ImageIcon("image/xiangdao.gif"));
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		
		//Ϊ����JFrame������
		this.add(jbl1, "North");
		this.add(jtp);
		this.add(jp1, "South");
		this.setSize(350, 240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == jp1_jb1) {
			QqClientUser qqClientUser = new QqClientUser();
			User u = new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			if(qqClientUser.checkUser(u)) {
				try {
					QqFriendList qqFriendList = new QqFriendList(u.getUserId());
					ManageQqFriendList.addQqFriendList(qqFriendList);
					
					ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread().getSocket().getOutputStream());
				
					Message m = new Message();
					m.setMesType(MessageType.message_get_online_friend);
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(this, "�û����������");
			}
		}
	}
	
}
