package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Checkbox;
import javax.swing.SwingConstants;

import client.cQuit;

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
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// MainList window = new MainList();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 * 
	 * @param person
	 */
	public MainList(String[] person) {
		initialize(person);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param friendlists
	 */
	private void initialize(String[] person) {
		String name = person[2];
		String password = person[3];
		String nickname = person[4];
		String friendsname = person[5];
		String friendsnickname = person[6];
		String[] fnamelists = friendsname.split(";");
		String[] fnicknamelists = friendsnickname.split(";");

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
					n = new cQuit(name).Quit();
				if (n) {
					frame.setVisible(false);
					new Login().frame.setVisible(true);
				}
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
				new AddFriends().frame.setVisible(true);
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
					Chat chat = new Chat(name, friendid);
					chat.frame.setVisible(true);

				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (list.getSelectedValue() != null) {
						JPopupMenu popupMenu = new JPopupMenu();
						String friend = list.getSelectedValue();
						int first = friend.indexOf("(");
						int last = friend.indexOf(")");
						String friendid = friend.substring(first + 1, last);
						JButton btn1 = new JButton("编号：" + friendid);
						JButton btn2 = new JButton("发送信息");
						JButton btn3 = new JButton("删除好友");
						//System.out.println(a);
						popupMenu.add(btn1);
						popupMenu.add(btn2);
						popupMenu.add(btn3);
						popupMenu.show(list, e.getX(), e.getY());
					}
				}
			}
		});

		panel_1.add(list, BorderLayout.CENTER);

	}
}
