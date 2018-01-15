package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Checkbox;
import javax.swing.SwingConstants;

import org.omg.CORBA.FREE_MEM;

import client.TCPConnection;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;

public class MainList {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
//	 EventQueue.invokeLater(new Runnable() {
//	 public void run() {
//	 try {
//	 MainList window = new MainList(person);
//	 window.frame.setVisible(true);
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 }
//	 }
//	 });
//	 }

	/**
	 * Create the application.
	 * 
	 * @param person
	 */
	public MainList(String person) {
		initialize(person);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param
	 */
	private void initialize(String pn) {

		String[] person = pn.split("&");
		for(int i=0;i<person.length;i++){
			System.out.print(person[i]+" ");
		}
		System.out.println();
		String userId=person[0];
		int id = Integer.parseInt(userId);
		String name = person[1];
		String password = person[2];
		String nickname = person[3];
		String friendsname = person[4];
		String friendsnickname = person[5];
		String[] fnamelists = friendsname.split(";");
		String[] fnicknamelists = friendsnickname.split(";");
		
//		System.out.println(id+" "+name+" "+password+" "+nickname+" "+friendsname+" "+friendsnickname);
//		
//		System.out.println(fnamelists[0]+" "+fnamelists[1]);
//		System.out.println(fnicknamelists[0]+" "+fnicknamelists[1]);
		frame = new JFrame();
		frame.setBounds(100, 100, 315, 547);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		Checkbox checkbox = new Checkbox("online");
		checkbox.setState(true);
		panel.add(checkbox, BorderLayout.WEST);

		JButton button = new JButton("切换账号");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int m = JOptionPane.showConfirmDialog(null, "是否退出并切换账号", "切换账号", JOptionPane.YES_NO_OPTION);
				boolean n = false;				
				if (m == 0)
					TCPConnection.getInstance().sendAndWaitResponse("Q&"+name);	
					frame.setVisible(false);
					new Login().frame.setVisible(true);
				}
		});
		panel.add(button, BorderLayout.EAST);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel(nickname + "/");
		panel_3.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(name);
		panel_3.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("添加新好友");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddFriends(name).frame.setVisible(true);
			}
		});
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("好友请求");
		panel_2.add(btnNewButton_1);

		DefaultListModel<String> dlm = new DefaultListModel<String>();
		for (int i = 0; i < fnicknamelists.length; i++) {
			dlm.addElement(fnicknamelists[i] + "(" + fnamelists[i] + ")");
		}

		JList<String> list = new JList<String>(dlm);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
					String friend = list.getSelectedValue();
					int first = friend.indexOf("(");
					int last = friend.indexOf(")");
					String friendid = friend.substring(first + 1, last);
					int id2 = Integer.parseInt(friendid);
					String friendname = friend.substring(0,first);
					Chat chat = new Chat(id,name, id2,friendname);
					chat.frame.setVisible(true);

				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (list.getSelectedValue() != null) {
						JPopupMenu popupMenu = new JPopupMenu();
						String friend = list.getSelectedValue();
						int first = friend.indexOf("(");
						int last = friend.indexOf(")");
						String friendid = friend.substring(first + 1, last);
						int id2 = Integer.parseInt(friendid);
						String friendname = friend.substring(0,first);
						JLabel label = new JLabel("编号：" + friendid);						
						JButton btn1 = new JButton("发送信息");
						JButton btn2 = new JButton("删除好友");
						btn1.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								Chat chat = new Chat(id,name, id2,friendname);
								chat.frame.setVisible(true);
							}
						});
						//System.out.println(a);
						popupMenu.add(label);
						popupMenu.add(btn1);
						popupMenu.add(btn2);
						popupMenu.show(list, e.getX(), e.getY());
					}
				}
			}
		});

		panel_1.add(list, BorderLayout.CENTER);

	}
}
