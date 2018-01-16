package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;


public class TCPConnection {
	private static final TCPConnection single = new TCPConnection();
	public static TCPConnection getInstance() {  
		return single;  
	}
	
	
	private Socket client;
	
	private BufferedReader br = null;
	private PrintStream ps = null;

	private TCPConnection() {
		try {
			client = new Socket("127.0.0.1", 8888);
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			ps = new PrintStream(client.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String sendAndWaitResponse(String msg) {
		ps.println(msg);
		String result = "";
		try {
			result = br.readLine();
		} catch (IOException e) {
			return null;
		}
		return result;
	}
	
	
	
	public void justSend(String msg) {
		ps.println(msg);
	}


	public String justWait() {
		// TODO Auto-generated method stub
		String result = "";
		try {
			result = br.readLine();
		} catch (IOException e) {
			return null;
		}
		return result;
	}


	public Socket getClient() {
		return client;
	}


	

}