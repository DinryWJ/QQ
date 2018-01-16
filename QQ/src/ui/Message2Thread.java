package ui;

import java.awt.ComponentOrientation;
import java.awt.TextField;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.TCPConnection;

public class Message2Thread extends Thread {
	private JTextArea textArea;
	



	public Message2Thread(JTextArea textArea) {
		// TODO Auto-generated constructor stub
		this.textArea = textArea;
	}


	@Override
    public void run() {
		while(true){
			String r = TCPConnection.getInstance().justWait();
			if(r!=null)	 {
				String[] x = r.split("&");
				//textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				//textArea.setText(textArea.getText()+"\n"+r);
				System.out.println(x.length);
				textArea.append("\n"+x[4]+"\n\t"+x[3]);
			}
		}
	}
}
