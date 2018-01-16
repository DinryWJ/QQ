package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import client.TCPConnection;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

	public JFrame frame;
	private JPanel panel;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 367);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(127, 255, 212));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBackground(Color.YELLOW);
		textField.setBounds(149, 134, 165, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(149, 174, 165, 30);
		panel.add(passwordField);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = textField.getText();
				String password = passwordField.getText();
				String r = TCPConnection.getInstance().sendAndWaitResponse("L&"+name+"&"+password);
				if(r.substring(0,1).equals("1")){
					String r2 = TCPConnection.getInstance().sendAndWaitResponse("G&"+name+"&"+password);
					String person="";
					if(r2.length()>2)
						person = r2.substring(2);
					frame.setVisible(false);
					MainList window = new MainList(person);
					window.frame.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "账号或密码错误或该账号已被登录");
				}
			}
		});
		button.setForeground(Color.BLACK);
		button.setBackground(Color.CYAN);
		button.setBounds(149, 214, 165, 30);
		panel.add(button);
		
		JLabel lblNewLabel = new JLabel("\u6CE8\u518C\u7528\u6237");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Regist().frame.setVisible(true);
			}
		});
		lblNewLabel.setBounds(324, 133, 62, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u627E\u56DE\u5BC6\u7801");
		lblNewLabel_1.setBounds(324, 173, 62, 30);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u8D26\u53F7");
		label.setBounds(100, 134, 39, 30);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(100, 174, 39, 25);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u5373\u65F6\u804A\u5929\u7CFB\u7EDF");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setToolTipText("40px\r\n");
		label_2.setBounds(184, 61, 95, 38);
		panel.add(label_2);
	}
}
