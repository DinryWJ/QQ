package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import client.cRegist;

import java.awt.Font;

public class Regist {

	public JFrame frame;
	private JPanel panel;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Regist window = new Regist();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Regist() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 367);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(127, 255, 212));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("账    号");
		label.setFont(new Font("宋体", Font.BOLD, 15));
		label.setToolTipText("");
		label.setBounds(91, 106, 76, 21);
		panel.add(label);
		
		JLabel label_1 = new JLabel("密    码");
		label_1.setFont(new Font("宋体", Font.BOLD, 15));
		label_1.setBounds(91, 166, 76, 21);
		panel.add(label_1);
		
		JLabel label_3 = new JLabel("昵    称");
		label_3.setFont(new Font("宋体", Font.BOLD, 15));
		label_3.setBounds(91, 136, 76, 21);
		panel.add(label_3);
		
		JButton btnNewButton = new JButton("注册");
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				String username=textField.getText();
				String nickname=textField.getText();
				String password=passwordField.getText();
				cRegist r=new cRegist(username,password,nickname);
				String show=username+"\t"+nickname+"\t"+password;
				System.out.println(show);
				boolean x=r.regist();
				if(x){
					try{
					frame.setVisible(false);
					
					}catch(Exception e2){e2.printStackTrace();}
				}
			}
		});
		btnNewButton.setBounds(101, 194, 66, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.setBounds(185, 194, 66, 23);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
					try{
					frame.setVisible(false);
					}catch(Exception e2){e2.printStackTrace();}
				}
		});
		panel.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(177, 106, 93, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(175, 166, 93, 21);
		panel.add(passwordField);
		
		textField_1 = new JTextField();
		textField_1.setText("");
		textField_1.setBounds(177, 136, 93, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		
	}
}
