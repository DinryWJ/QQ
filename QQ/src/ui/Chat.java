package ui;

import java.awt.ComponentOrientation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client.TCPConnection;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Chat {

	public JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat window = new Chat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @param id 
	 * @param id2 
	 * @param name 
	 * @param friendname 
	 */
	public Chat(int id, String name, int id2, String friendname) {
		initialize(id,name,id2,friendname);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param id 
	 * @param id2 
	 * @param name 
	 * @param friendname 
	 */
	private void initialize(int id, String name, int id2, String friendname) {
		
		frame = new JFrame();

		frame.setBounds(100, 100, 876, 629);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 858, 403);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setColumns(20);
		textArea.setLineWrap(true);
		textArea.setBounds(0, 34, 844, 369);
		panel.add(textArea);
		
		JLabel lblNewLabel = new JLabel("昵称："+friendname);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 181, 34);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 402, 858, 167);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("发送");
		btnNewButton.setBounds(781, 127, 63, 27);
		panel_1.add(btnNewButton);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(0, 9, 855, 105);
		panel_1.add(textField);
		textField.setColumns(10);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
				String date = sd.format(new Date());
				textArea.append("\n\t\t\t\t\t\t");
				textArea.append(date);
				textArea.append("\n\t\t\t\t\t\t");
				textArea.append(textField.getText());

				TCPConnection.getInstance().justSend("M&"+id+"&"+id2+"&"+textField.getText()+"&"+date);

				textField.setText("");
				
			}
		});
		
		Message2Thread  mt = new Message2Thread(textArea);
		mt.start();
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mt.interrupt();
			}
		});
	}
}
