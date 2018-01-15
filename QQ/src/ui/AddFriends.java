package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client.TCPConnection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddFriends {
	public String username;
	public JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// AddFriends window = new AddFriends();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public AddFriends(String username) {
		initialize();
		this.username = username;
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 550, 300);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel label = new JLabel("搜索好友名字");
		panel.add(label);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setToolTipText("");
		panel.add(textField);
		textField.setColumns(30);
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		JButton btnNewButton = new JButton("搜索");
		btnNewButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String keys = textField.getText();
				String sh = TCPConnection.getInstance().sendAndWaitResponse("S&" + keys);
				if (sh.substring(0, 1).equals("1")) {
					String s = sh.substring(2);

					// cSearch c = new cSearch(keys);
					// String s = c.search();
					System.out.println("s:" + s);
					final String[] serchinf = s.split(";");
					if (serchinf.length > 0) {
						DefaultListModel<String> dlm = new DefaultListModel<String>();
						for (int i = 0; i < serchinf.length; i++) {
							dlm.addElement(serchinf[i]);
						}
						JList<String> list = new JList<String>(dlm);
						list.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								if (e.getButton() == MouseEvent.BUTTON3) {
									if (list.getSelectedValue() != null) {
										JPopupMenu popupMenu = new JPopupMenu();
										JButton btn1 = new JButton("加好友");
										btn1.addMouseListener(new MouseAdapter() {
											public void mouseClicked(MouseEvent e) {
												System.out.println(list.getSelectedValue());

												String c = TCPConnection.getInstance().sendAndWaitResponse(
														"A&" + username + "&" + list.getSelectedValue());

												// cAdd c = new cAdd(username,
												// list.getSelectedValue());
												System.out.println("标记：" + username + "\t" + list.getSelectedValue());
												boolean x = c.equals("1");

											}
										});
										// System.out.println(a);
										popupMenu.add(btn1);
										popupMenu.show(list, e.getX(), e.getY());
									}
								}
							}
						});
						scrollPane.setViewportView(list);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Error&没有相关用户！");
				}
			}
		});
		panel.add(btnNewButton);

	}

}
