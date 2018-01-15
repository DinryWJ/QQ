package ui;

import javax.swing.JOptionPane;

import client.TCPConnection;

public class MessageThread extends Thread {
	private String r;


	public MessageThread() {
		// TODO Auto-generated constructor stub
	}


	@Override
    public void run() {
		while(true){
			r = TCPConnection.getInstance().justWait();
			if(r!=null)JOptionPane.showMessageDialog(null, "新消息"+r);
		}
	}
}
